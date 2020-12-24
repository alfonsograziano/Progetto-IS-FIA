package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.review.ReviewMismatchException;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewManagerTest extends TestCase {

    protected void setUp() throws Exception{

        rm = new ReviewManager();

    }

    public void testCreateReview() throws SQLException{

        try {

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            Review reviewToInsert = new Review(5, 5, 5, 5, 5, "Review di prova", s, u);
            reviewToInsert.setState("pending");
            rm.createReview(5, 5, 5, 5, 5, "Review di prova", s, u);
            List<Review> reviewList = rm.searchReviewsByUser(3);
            Review max = reviewList.get(0);
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
            System.out.println("testCreateReview() passed!");
            rm.deleteReview(max.getId());

        }catch (ReviewMismatchException e){

            fail("testCreateReview() not passed!");

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

            System.out.println("testCreateReviewException() (null spec) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = null;
            rm.createReview(5, 5, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (null user) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (null user) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(6, 5, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid totalScore) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (invalid totalScore) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 6, 5, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid performance) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (invalid performance) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 6, 5, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid display) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (invalid display) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 6, 5, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid camera) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (invalid camera) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 6, "Review di prova", s, u);
            fail("testCreateReviewException() (invalid battery) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (invalid battery) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 5, "", s, u);
            fail("testCreateReviewException() (empty text) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (empty text) passed!");

        }

        try{

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);
            rm.createReview(5, 5, 5, 5, 5, null, s, u);
            fail("testCreateReviewException() (null text) not passed!");

        } catch (ReviewMismatchException e){

            System.out.println("testCreateReviewException() (null text) passed!");

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

            System.out.println("testCreateReviewException() (text length exceeded) passed!");

        }

    }

    public void testSearchReviewsByUser(){

        try {

            List<Review> r = rm.searchReviewsByUser(3);
            List<Review> oracle = new ArrayList<>();

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

            System.out.println("testSearchReviewsByUser() passed!");

        }catch (SQLException e){

            fail("testSearchReviewsByUser() not passed!");

        }

    }

    public void testSearchReviewsByUserException() throws SQLException{

        List<Review> r = rm.searchReviewsByUser(-1);
        List<Review> oracle = new ArrayList<>();
        if(oracle.size() != 0){
            fail("testSearchReviewsByUserException() not passed!");
        }
        System.out.println("testSearchReviewsByUserException() passed!");

    }

    public void testSearchReviewsBySpec() throws SQLException{

        try {

            List<Review> r = rm.searchReviewsBySpec(2041);
            List<Review> oracle = new ArrayList<>();
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

            System.out.println("NELLA FUNZIONE CHE FALLISCE");
            oracle.add(r1);
            oracle.add(r2);

            System.out.println(r);
            System.out.println("\n\n\n\n\n" + oracle);

            assertTrue(r.contains(oracle.get(0)));
            assertTrue(r.contains(oracle.get(1)));

            System.out.println("testSearchReviewsBySpec() passed!");

        }catch (SQLException e){

            fail("testSearchReviewsBySpec() not passed!");

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

        System.out.println(r);
        System.out.println(reviews);

        if(!reviews.contains(r)){

            fail("testGetPending() not passed!");

        }


        System.out.println("testGetPending() passed!");

    }

    public void testSearchReviewInfo() throws SQLException{

        try {

            Review r = rm.searchReviewInfo(3);
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
            System.out.println("testSearchReviewInfo() passed!");

        }catch (SQLException e){

            fail("testSearchReviewInfo() not passed!");

        }


    }

    public void testApprovation(){

        try{

            Review review = new Review();   //Creo una Review di prova
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
            Review max = pendingReviews.get(0);

            //Cerco la review appena inserita per ottenere l'id
            for (Review rev : pendingReviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            System.out.println("PENDINGGG: " + pendingReviews);
            rm.approvation(max.getId(), true);
            assertEquals(rm.searchReviewInfo(max.getId()).getState(), "approved");
            System.out.println("testApprovation() (review approved) passed!");
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
            System.out.println("testApprovation() (review approved) passed!");
            rm.deleteReview(max.getId());   //Pulisco il db

        } catch (SQLException e){

            fail("testApproveReview() not passed!");

        } catch (ReviewMismatchException e){

            fail("testApproveReview() not passed");

        }

    }


    public static Test suite(){

        return new TestSuite(ReviewManagerTest.class);

    }

    private static ReviewManager rm;

}
