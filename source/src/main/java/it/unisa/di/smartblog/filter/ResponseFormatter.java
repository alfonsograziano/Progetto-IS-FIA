package it.unisa.di.smartblog.filter;

import com.google.gson.Gson;
import it.unisa.di.smartblog.test.control.ReviewControlTest;
import it.unisa.di.smartblog.test.review.ReviewDaoTestServlet;
import it.unisa.di.smartblog.test.review.ReviewManagerTestServlet;
import it.unisa.di.smartblog.test.spec.SpecDaoTestServlet;
import it.unisa.di.smartblog.test.spec.SpecManagerTest;
import it.unisa.di.smartblog.test.spec.SpecManagerTestServlet;
import it.unisa.di.smartblog.test.user.UserDaoTestServlet;
import it.unisa.di.smartblog.test.user.UserManagerTestServlet;
import it.unisa.di.smartblog.user.UserManager;
import org.junit.experimental.categories.Categories;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class ResponseFormatter implements Filter {

    private Gson _gson = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       //Codice prima dell'esecuzione della servlet
        chain.doFilter(req, resp);
        //Codice dopo l'esecuzione della servlet

        sendAsJson(resp, req.getAttribute("response"));
    }

    public void init(FilterConfig config) throws ServletException {
        _gson = new Gson();
    }

    //as JSON response
    private void sendAsJson(
            ServletResponse response,
            Object obj) throws IOException {
        //System.out.println("Filtro eseguito...");

        response.setContentType("application/json");
        String res = _gson.toJson(obj);
        //System.out.println("Response"+res);
        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

}
