package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.review.ReviewMismatchException;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.user.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoTest extends TestCase {

    protected void setUp() throws Exception{

        rd = new ReviewDao();

    }

    public void testGetById() {

        try {

            Review r = rd.getById(3);
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
            System.out.println("testGetById() passed!");

        }catch (SQLException e){

            fail("testGetById() not passed!");

        }

    }

    public void testGetByIdException(){

        try {

            Review r = rd.getById(1);
            fail("testGetByIdException() not passed!");

        } catch (SQLException e){

            System.out.println("testGetByIdException() passed!");

        }

    }

    public void testGetBySpecId() {

        try {

            List<Review> r = rd.getBySpecId(2041);
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

            System.out.println("testGetBySpecId() passed!");

        }catch (SQLException e){

            fail("testGetBySpecId() not passed!");

        }

    }

    public void testGetBySpecIdException() throws SQLException{

        List<Review> r = rd.getBySpecId(-5);

        if(r.size() != 0) {

            fail("testGetBySpecIdException() not passed!");

        } else {

            System.out.println("testGetBySpecIdException() passed!");

        }

    }

    public void testGetByUser(){

        try {

            List<Review> r = rd.getByUser(3);
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

            System.out.println(r);
            System.out.println("\n\n\n\n\n" + oracle);

            assertTrue(r.contains(oracle.get(0)));
            assertTrue(r.contains(oracle.get(1)));

            System.out.println("testGetByUser() passed!");

        }catch (SQLException e){

            fail("testGetByUser() not passed!");

        }

    }

    public void testGetByUserException() throws SQLException{

        List<Review> r = rd.getByUser(-1);
        if(r.size() != 0){

            fail("testGetByUserException() not passed!");

        } else {

            System.out.println("testGetByUserException() passed!");

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

        System.out.println(r);
        System.out.println(reviews);

        if(!reviews.contains(r)){

            fail("testGetPending() not passed!");

        }


        System.out.println("testGetPending() passed!");

    }

    public void testSaveReview(){

        Spec s = new Spec();
        s.setId(2045);
        User u = new User();
        u.setId(3);
        Review reviewToInsert = new Review(4, 4, 3, 3, 3, "Review di prova", s, u);
        reviewToInsert.setState("pending");
        try{
            rd.saveReview(reviewToInsert);

            List<Review> reviews = rd.getByUser(3);
            Review max = reviews.get(0);
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
            System.out.println("testSaveReview() passed!");
            rd.deleteReview(max.getId());

        }catch (ReviewMismatchException e){
            fail("testSaveReview() not passed!");
        }catch (SQLException e){
            fail("testSaveReview() not passed!");
        }

    }

    public void testSaveReviewException() throws SQLException{

        try{

            rd.saveReview(null);
            fail("testSaveReviewException() not passed!");

        } catch (ReviewMismatchException e){
            System.out.println("testSaveReviewException() passed!");
        }

    }


    public void testDeleteReview() throws ReviewMismatchException{

        try {

            Review review = new Review();
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
            Review max = reviews.get(0);
            for (Review rev : reviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            System.out.println("Massimo: " + max);
            rd.deleteReview(max.getId());
            rd.getById(max.getId());
            fail("testDeleteReview() not passed!");

        } catch (SQLException e){
            System.out.println("testDeleteReview() passed!");
        }

    }

    public void testApproveReview() throws ReviewMismatchException{

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

            rd.saveReview(review);  //Inserisco una Review di prova nel db
            List<Review> pendingReviews = rd.getPending();
            Review max = pendingReviews.get(0);

            //Cerco la review appena inserita per ottenere l'id
            for (Review rev : pendingReviews) {
                if (rev.getId() > max.getId()) {
                    max = rev;
                }
            }
            System.out.println("PENDINGGG: " + pendingReviews);
            rd.approveReview(max.getId(), true);
            assertEquals(rd.getById(max.getId()).getState(), "approved");
            System.out.println("testApproveReview() (review approved) passed!");
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
            System.out.println("testApproveReview() (review rejected) passed!");
            rd.deleteReview(max.getId());   //Pulisco il db

            //Cerco la review appena inserita per ottenere l'id

            System.out.println("Done!");

        } catch (SQLException e){

            fail("testApproveReview() not passed!");

        }

    }

    public static Test suite(){

        return new TestSuite(ReviewDaoTest.class);

    }




    public void tearDown(){

        System.out.println("TEAR DOWN");

    }

    private ReviewDao rd;


}
