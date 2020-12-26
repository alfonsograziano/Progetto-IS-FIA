package it.unisa.di.smartblog.test.control;

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

    }

    public void testGetById() {

    }

    public static Test suite(){

        return new TestSuite(ReviewDaoTest.class);

    }




    public void tearDown(){

        System.out.println("TEAR DOWN");

    }

    private ReviewDao rd;


}
