package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoTest extends TestCase {

    protected void setUp() throws Exception{
        System.out.println("Nel setup del test");
        rd = new ReviewDao();


    }

    public void testGetById() throws SQLException {

        System.out.println("Prima di chiamare il metodo");
        Review r = rd.getById(1);
        System.out.println(r);
        System.out.println("Dopo aver chiamato il metodo");
        assertEquals(r.getBattery(), 5);

    }

    public void testGetBySpecId() throws SQLException{

        List<Review> r = rd.getBySpecId(2164);
        System.out.println(r.size());
        assertEquals(r.get(0).getUser().getId(), 2);

    }

    public static Test suite(){

        return new TestSuite(ReviewDaoTest.class);

    }

    private ReviewDao rd;


}
