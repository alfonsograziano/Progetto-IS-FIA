package it.unisa.di.smartblog.test.review;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.TestListener;
import it.unisa.di.smartblog.user.UserManager;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.runner.JUnitCore;

import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter pw = response.getWriter();

        JUnitCore runner = new JUnitCore();
        runner.addListener(new TestListener(pw));
        runner.run(suite(pw));

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static Test suite(PrintWriter pw){
        TestSuite suite = new TestSuite();
        suite.addTest(ReviewDaoTest.suite(pw));
        return suite;

    }

    static ReviewDao rd;

}
