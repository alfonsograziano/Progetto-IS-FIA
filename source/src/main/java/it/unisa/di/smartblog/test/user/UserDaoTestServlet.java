package it.unisa.di.smartblog.test.user;
import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.TestListener;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;
import java.io.IOException;
import java.io.PrintWriter;
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
        suite.addTest(UserDaoTest.suite(pw));
        return suite;

    }

    static ReviewDao rd;

}
