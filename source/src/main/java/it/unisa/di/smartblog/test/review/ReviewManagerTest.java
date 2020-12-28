package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.review.ReviewMismatchException;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.test.TestWriter;
import it.unisa.di.smartblog.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewManagerTest extends TestCase {

    protected void setUp() throws Exception{

        rm = new ReviewManager();

    }

    public void testCreateReview() throws SQLException{

        boolean flag=false;
        Review reviewToInsert=null, max =null;

        try {

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            reviewToInsert = new Review(5, 5, 5, 5, 5, "Review di prova", s, u);
            reviewToInsert.setState("pending");
            rm.createReview(5, 5, 5, 5, 5, "Review di prova", s, u);
            List<Review> reviewList = rm.searchReviewsByUser(3);
            max = reviewList.get(0);

            for(Review rev : reviewList){
                if(rev.getId() > max.getId()){
                    max = rev;
                }
            }
            assertEquals(max.getTotalScore(), reviewToInsert.getTotalScore());
            assertEquals(max.getDisplay(), reviewToInsert.getDisplay());
            assertEquals(max.getPerformance(), reviewToInsert.getPerformance());
            assertEquals(max.getBattery(), reviewToInsert.getBattery());
            assertEquals(max.getCamera(), reviewToInsert.getCamera());
            assertEquals(max.getState(), reviewToInsert.getState());
            assertEquals(max.getText(), reviewToInsert.getText());
            flag=true;
            rm.deleteReview(max.getId());

        }catch (ReviewMismatchException e){

            fail("testCreateReview() not passed!");

        } finally {
            TestWriter.printTest(pw, reviewToInsert, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    public void testCreateReviewException() throws SQLException{

        try{

            Spec s = null;
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (null spec) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = null;
            rm.createReview(5, 5, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (null user) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(6, 5, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid totalScore) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 6, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid performance) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 6, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid display) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 6, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid camera) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 6, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid battery) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 5, "", s, u);
            fail("testCreateReviewException() (empty text) not passed!");

        } catch (ReviewMismatchException e){

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 5, null, s, u);
            fail("testCreateReviewException() (null text) not passed!");

        } catch (ReviewMismatchException e){

        }

        String longText = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 5, longText, s, u);
            fail("testCreateReviewException() (text length exceeded) not passed!");

        } catch (ReviewMismatchException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testSearchReviewsByUser(){
        boolean flag=false;
        List<Review> oracle=null, r=null;

        try {

            r = rm.searchReviewsByUser(3);
            oracle = new ArrayList<>();

            Spec s1 = new Spec();
            s1.setId(2044);
            s1.setName("Huawei Mate 40 Pro");

            Spec s2 = new Spec();
            s2.setId(2041);
            s2.setName("Redmi Note 9 4G");

            Review r1 = new Review(2, 4, 3, 2, 1, "Vintage", s2, null);
            r1.setId(4);
            r1.setState("approved");

            Review r2 = new Review(5, 4, 5, 4, 4, "Ero scettico ma le prestazione di questo Huawei mi hanno fatto ricredere!", s1, null);
            r2.setId(3);
            r2.setState("pending");

            oracle.add(r2);
            oracle.add(r1);

            assertTrue(r.contains(oracle.get(0)));
            assertTrue(r.contains(oracle.get(1)));

            flag=true;

        }catch (SQLException e){

            fail("testSearchReviewsByUser() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, r);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testSearchReviewsByUserException() throws SQLException{

        List<Review> r = rm.searchReviewsByUser(-1);
        List<Review> oracle = new ArrayList<>();
        if(oracle.size() != 0){
            fail("testSearchReviewsByUserException() not passed!");
        }
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testSearchReviewsBySpec() throws SQLException{
        boolean flag=false;
        List<Review> r=null, oracle=null;

        try {

            r = rm.searchReviewsBySpec(2041);
            oracle = new ArrayList<>();
            User u1 = new User("antonio", null, "antonio@sisonoio.com");
            u1.setId(3);
            User u2 = new User("mario", null, "mario@rossi.com");
            u2.setId(4);
            Review r1 = new Review(2, 4, 3, 2, 1, "Vintage", null, u1);
            r1.setId(4);
            r1.setState("approved");
            Review r2 = new Review(4, 3, 5, 5, 2, "Un best buy", null, u2);
            r2.setId(5);
            r2.setState("approved");

            oracle.add(r1);
            oracle.add(r2);

            assertTrue(r.contains(oracle.get(0)));
            assertTrue(r.contains(oracle.get(1)));

            flag=true;

        }catch (SQLException e){

            fail("testSearchReviewsBySpec() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, r);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testSearchPendingReviews() throws SQLException{

        List<Review> reviews = rm.searchPendingReviews();
        Spec s = new Spec();
        s.setId(2044);
        s.setName("Huawei Mate 40 Pro");

        User u = new User();
        u.setId(3);
        u.setActive(true);
        u.setEmail("antonio@sisonoio.com");
        u.setUsername("antonio");

        Review r = new Review(5, 4, 5, 4, 4, "Ero scettico ma le prestazione di questo Huawei mi hanno fatto ricredere!", s, u);
        r.setId(3);
        r.setState("pending");

        if(!reviews.contains(r)){

            fail("testGetPending() not passed!");

        }

        TestWriter.printTest(pw, r, reviews.get(reviews.indexOf(r)));
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testSearchReviewInfo() throws SQLException{
        boolean flag=false;
        Review r = null, oracle=null;

        oracle = new Review();
        oracle.setId(3);
        oracle.setBattery(4);
        oracle.setCamera(4);
        oracle.setPerformance(5);
        oracle.setDisplay(4);
        oracle.setTotalScore(5);
        oracle.setText("Ero scettico ma le prestazione di questo Huawei mi hanno fatto ricredere!");
        oracle.setState("pending");
        User user = new User();
        user.setId(3);
        user.setEmail("antonio@sisonoio.com");
        user.setUsername("antonio");
        oracle.setUser(user);
        Spec spec = new Spec();
        spec.setId(2044);
        spec.setName("Huawei Mate 40 Pro");
        oracle.setSpec(spec);

        try {

            r = rm.searchReviewInfo(3);
            assertEquals(r.getId(), 3);
            assertEquals(r.getBattery(), 4);
            assertEquals(r.getCamera(), 4);
            assertEquals(r.getPerformance(), 5);
            assertEquals(r.getDisplay(), 4);
            assertEquals(r.getTotalScore(), 5);
            assertEquals(r.getText(), "Ero scettico ma le prestazione di questo Huawei mi hanno fatto ricredere!");
            assertEquals(r.getState(), "pending");
            assertEquals(r.getUser().getId(), 3);
            assertEquals(r.getUser().getEmail(), "antonio@sisonoio.com");
            assertEquals(r.getUser().getUsername(), "antonio");
            assertEquals(r.getSpec().getId(), 2044);
            assertEquals(r.getSpec().getName(), "Huawei Mate 40 Pro");
            flag=true;

        }catch (SQLException e){

            fail("testSearchReviewInfo() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, r);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }



    }

    public void testApprovation(){
        Review review=null, max=null;
        boolean flag=false;

        try{

            review = new Review();   //Creo una Review di prova
            review.setTotalScore(5);
            review.setBattery(5);
            review.setCamera(5);
            review.setDisplay(5);
            review.setText("Da approvare");
            review.setPerformance(5);

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);

            review.setUser(u);
            review.setSpec(s);

            rm.createReview(review.getTotalScore(), review.getPerformance(), review.getDisplay(), review.getCamera(), review.getBattery(), review.getText(), s, u);  //Inserisco la Review di prova nel db
            List<Review> pendingReviews = rm.searchPendingReviews();
            max = pendingReviews.get(0);

            //Cerco la review appena inserita per ottenere l'id
            for (Review rev : pendingReviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            System.out.println("PENDING: " + pendingReviews);
            rm.approvation(max.getId(), true);
            assertEquals(rm.searchReviewInfo(max.getId()).getState(), "approved");
            pw.println("testApprovation() (review approved) passed!");
            rm.deleteReview(max.getId());   //Pulisco il db

            //TESTO IL RIFIUTO DELLA REVIEW
            review = new Review();   //Creo una Review di prova
            review.setTotalScore(5);
            review.setBattery(5);
            review.setCamera(5);
            review.setDisplay(5);
            review.setText("Da approvare");
            review.setPerformance(5);

            s = new Spec();
            s.setId(2045);
            u = new User();
            u.setId(3);

            review.setUser(u);
            review.setSpec(s);

            rm.createReview(review.getTotalScore(), review.getPerformance(), review.getDisplay(), review.getCamera(), review.getBattery(), review.getText(), s, u);  //Inserisco la Review di prova nel db
            pendingReviews = rm.searchPendingReviews();
            max = pendingReviews.get(0);

            //Cerco la review appena inserita per ottenere l'id
            for (Review rev : pendingReviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            System.out.println("PENDINGGG: " + pendingReviews);
            rm.approvation(max.getId(), false);
            assertEquals(rm.searchReviewInfo(max.getId()).getState(), "rejected");
            pw.println("testApprovation() (review approved) passed!");
            rm.deleteReview(max.getId());   //Pulisco il db

            flag=true;

        } catch (SQLException e){

            fail("testApproveReview() not passed!");

        } catch (ReviewMismatchException e){

            fail("testApproveReview() not passed");

        } finally {
            TestWriter.printTest(pw, review, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }


    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(ReviewManagerTest.class);

    }

    private static ReviewManager rm;
    private static PrintWriter pw;
}
