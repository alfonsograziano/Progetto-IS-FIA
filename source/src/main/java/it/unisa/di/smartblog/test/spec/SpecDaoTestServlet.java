package it.unisa.di.smartblog.test.spec;

import it.unisa.di.smartblog.spec.SpecDao;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SpecDaoTestServlet")
public class SpecDaoTestServlet extends HttpServlet{

    static{
        sd = new SpecDao();
    }

    private static final long serialVersionUID = 1L;

    public SpecDaoTestServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestRunner.run(suite());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static Test suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(SpecDaoTest.suite());
        return suite;
    }

    static SpecDao sd;
}
