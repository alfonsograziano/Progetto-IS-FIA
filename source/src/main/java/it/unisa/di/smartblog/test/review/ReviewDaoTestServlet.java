package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.user.UserManager;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReviewDaoTestServlet")
public class ReviewDaoTestServlet extends HttpServlet{

    static{

        rd = new ReviewDao();

    }

    private static final long serialVersionUID = 1L;

    public ReviewDaoTestServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Prima della chiamata di suite");
        TestRunner.run(suite());

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static Test suite(){
        System.out.println("Nella chiamata di suite");
        TestSuite suite = new TestSuite();
        suite.addTest(ReviewDaoTest.suite());
        return suite;

    }

    static ReviewDao rd;

}
