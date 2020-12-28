package it.unisa.di.smartblog.test.user;

import it.unisa.di.smartblog.test.TestListener;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserManagerTestServlet")
public class UserManagerTestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserManagerTestServlet() {
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
        suite.addTest(UserManagerTest.suite(pw));
        return suite;

    }


}
