package it.unisa.di.smartblog.test.user;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.spec.SpecManagerTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserManagerTestServlet")
public class UserManagerTestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserManagerTestServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TestRunner.run(suite());

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static Test suite(){

        TestSuite suite = new TestSuite();
        suite.addTest(UserManagerTest.suite());
        return suite;

    }


}
