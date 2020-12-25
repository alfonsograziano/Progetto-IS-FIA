package it.unisa.di.smartblog.test.spec;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.review.ReviewManagerTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SpecManagerTestServlet")
public class SpecManagerTestServlet extends HttpServlet {

    static{

        rd = new ReviewDao();

    }

    private static final long serialVersionUID = 1L;

    public SpecManagerTestServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TestRunner.run(suite());

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static Test suite(){

        TestSuite suite = new TestSuite();
        suite.addTest(SpecManagerTest.suite());
        return suite;

    }

    static ReviewDao rd;

}