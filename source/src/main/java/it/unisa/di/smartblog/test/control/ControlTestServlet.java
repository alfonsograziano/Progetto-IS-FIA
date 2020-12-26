package it.unisa.di.smartblog.test.control;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.review.ReviewDaoTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test/control")
public class ControlTestServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Prima della chiamata di suite");
        TestRunner.run(suite());

    }



    public static Test suite(){
        System.out.println("Nella chiamata di suite");
        TestSuite suite = new TestSuite();
        suite.addTest(ReviewDaoTest.suite());
        return suite;

    }

    static ReviewDao rd;

}
