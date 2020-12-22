package it.unisa.di.smartblog.filter;

import com.google.gson.Gson;
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

        response.setContentType("application/json");

        String res = _gson.toJson(obj);
        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

}
