package it.unisa.di.smartblog.test.user;
import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.review.ReviewDaoTest;
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

@WebServlet("/UserDaoTestServlet")
public class UserDaoTestServlet extends HttpServlet{

    static{

        rd = new ReviewDao();

    }

    private static final long serialVersionUID = 1L;

    public UserDaoTestServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TestRunner.run(suite());

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static Test suite(){

        TestSuite suite = new TestSuite();
        suite.addTest(UserDaoTest.suite());
        return suite;

    }

    static ReviewDao rd;

}
