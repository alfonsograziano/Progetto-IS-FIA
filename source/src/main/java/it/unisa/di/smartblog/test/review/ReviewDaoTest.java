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

            System.out.println("testGetBySpecId() passed!");

        }catch (SQLException e){

            fail("testGetBySpecId() not passed!");

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
        Review r = new Review(4, 4, 3, 3, 3, "Review di prova", s, u);
        r.setState("pending");
        try{
            rd.saveReview(r);
            System.out.println("FUNZIONEEE");
            System.out.println("GET BY SPEC ID " + rd.getByUser(3));
            if(!rd.getBySpecId(3).contains(r)){
                fail("testSaveReview() not passed!");
            } else {
                System.out.println("testSaveReview() passed!");
            }
        }catch (ReviewMismatchException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
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
