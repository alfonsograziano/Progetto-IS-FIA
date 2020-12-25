package it.unisa.di.smartblog.test.control;

import hthurow.tomcatjndi.TomcatJNDI;
import it.unisa.di.smartblog.control.SearchControl;
import it.unisa.di.smartblog.test.review.ReviewDaoTest;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.when;

public class ServletTest extends TestCase {


    @Test
    public void testSearch() throws IOException, ServletException {
        System.out.println("Call...");
        StaticContainers.request.getParameterValues("s")[0] = "iphone";

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);


        SearchControl searchControl =new SearchControl();
        searchControl.doGet(StaticContainers.request, StaticContainers.response);
        String result = sw.getBuffer().toString().trim();
        System.out.println("Result " + result);
        assertNotNull(result);

    }

    public static junit.framework.Test suite(){
        return new TestSuite(ServletTest.class);
    }

}
