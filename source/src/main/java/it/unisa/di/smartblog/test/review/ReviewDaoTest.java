package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewDao;
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

public class ReviewDaoTest extends TestCase {

    protected void setUp() throws Exception{

        rd = new ReviewDao();

    }

    public void testGetById() {
        boolean flag=false;
        Review r = null, oracle = null;

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

            r = rd.getById(3);
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

            fail("testGetById() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, r);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetByIdException(){

        try {

            Review r = rd.getById(1);
            fail("testGetByIdException() not passed!");

        } catch (SQLException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testGetBySpecId() {
        boolean flag=false;
        List<Review> r=null, oracle=null;

        try {

            r = rd.getBySpecId(2041);
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

            fail("testGetBySpecId() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, r);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetBySpecIdException() throws SQLException{

        List<Review> r = rd.getBySpecId(-5);

        if(r.size() != 0) {

            fail("testGetBySpecIdException() not passed!");

        } else {

            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

        }

    }

    public void testGetByUser(){
        List<Review> r=null, oracle=null;
        boolean flag=false;

        try {

            r = rd.getByUser(3);
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

            System.out.println(r);
            System.out.println("\n\n\n\n\n" + oracle);

            assertTrue(r.contains(oracle.get(0)));
            assertTrue(r.contains(oracle.get(1)));

            flag=true;

        }catch (SQLException e){

            fail("testGetByUser() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, r);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetByUserException() throws SQLException{

        List<Review> r = rd.getByUser(-1);
        if(r.size() != 0){

            fail("testGetByUserException() not passed!");

        } else {

            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

        }

    }

    public void testGetPending() throws SQLException{

        List<Review> reviews = rd.getPending();
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

    public void testSaveReview(){
        boolean flag=false;
        Review max = null;

        Spec s = new Spec();
        s.setId(2045);
        User u = new User();
        u.setId(3);
        Review reviewToInsert = new Review(4, 4, 3, 3, 3, "Review di prova", s, u);
        reviewToInsert.setState("pending");

        try{
            rd.saveReview(reviewToInsert);

            List<Review> reviews = rd.getByUser(3);
            max = reviews.get(0);
            for(Review rev : reviews){
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
            rd.deleteReview(max.getId());

        }catch (ReviewMismatchException e){
            fail("testSaveReview() not passed!");
        }catch (SQLException e){
            fail("testSaveReview() not passed!");
        } finally {
            TestWriter.printTest(pw, reviewToInsert, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testSaveReviewException() throws SQLException{

        try{

            rd.saveReview(null);
            fail("testSaveReviewException() not passed!");

        } catch (ReviewMismatchException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }


    public void testDeleteReview() throws ReviewMismatchException{
        Review review=null, max=null;

        try {

            review = new Review();
            review.setTotalScore(5);
            review.setBattery(5);
            review.setCamera(5);
            review.setDisplay(5);
            review.setText("Da eliminare");
            review.setPerformance(5);

            Spec s = new Spec();
            s.setId(2045);
            User u = new User();
            u.setId(3);

            review.setUser(u);
            review.setSpec(s);

            rd.saveReview(review);

            List<Review> reviews = rd.getByUser(3);
            max = reviews.get(0);
            for (Review rev : reviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }

            rd.deleteReview(max.getId());
            rd.getById(max.getId());
            fail("testDeleteReview() not passed!");

        } catch (SQLException e){
            TestWriter.printTest(pw, review, max);
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testApproveReview() throws ReviewMismatchException{
        boolean flag=false;
        Review review=null, max=null;

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

            rd.saveReview(review);  //Inserisco una Review di prova nel db
            List<Review> pendingReviews = rd.getPending();
            max = pendingReviews.get(0);

            //Cerco la review appena inserita per ottenere l'id
            for (Review rev : pendingReviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            System.out.println("PENDING: " + pendingReviews);
            rd.approveReview(max.getId(), true);
            assertEquals(rd.getById(max.getId()).getState(), "approved");
            pw.println("\ttestApproveReview() (review approved) passed!");
            rd.deleteReview(max.getId());   //Pulisco il db

            //TESTO IL RIFIUTO DELLA REVIEW
            rd.saveReview(review);
            pendingReviews = rd.getPending();
            max = pendingReviews.get(0);
            for (Review rev : pendingReviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            rd.approveReview(max.getId(), false);
            assertEquals(rd.getById(max.getId()).getState(), "rejected");
            pw.println("\ttestApproveReview() (review rejected) passed!");
            rd.deleteReview(max.getId());   //Pulisco il db

            //Cerco la review appena inserita per ottenere l'id

            flag=true;

        } catch (SQLException e){

            fail("testApproveReview() not passed!");

        } finally {
            TestWriter.printTest(pw, review, max);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(ReviewDaoTest.class);

    }

    private ReviewDao rd;
    private static PrintWriter pw;
}
