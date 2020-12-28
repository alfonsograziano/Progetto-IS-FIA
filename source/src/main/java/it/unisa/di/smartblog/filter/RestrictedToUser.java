package it.unisa.di.smartblog.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import it.unisa.di.smartblog.security.JWTHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;

//Aggiungere qui le servlet su cui devo eseguire il filtro
@WebFilter(servletNames = {
        "ProfileControl", "DoraControl", "AddReviewControl"
})
public class RestrictedToUser implements Filter {

    private JWTHandler jwt;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String auth = httpRequest.getHeader("Authorization");
        System.out.println(auth);
        if(auth != null){
            String token = null;
            try{
                token = auth.split("Bearer ")[1];
            }catch(Exception e){
                errorResponse(resp);
            }
            String email = null;
            try{
                System.out.println("Ci arrivo...");
                email = jwt.decode(token);
                httpRequest.setAttribute("email", email);
                chain.doFilter(req, resp);
            }catch(Exception e){
                errorResponse(resp);
            }
        }else{
            errorResponse(resp);
        }
    }


    public void errorResponse(ServletResponse resp) throws IOException {
        System.out.println("Utente non autorizzato...");
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = httpResponse.getWriter();
        out.print("User unhautorized...");
        out.flush();
    }


    public void init(FilterConfig config) throws ServletException {
        jwt = new JWTHandler();
    }

}
