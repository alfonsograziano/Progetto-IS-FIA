package it.unisa.di.smartblog.filter;

import it.unisa.di.smartblog.security.JWTHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.security.Key;

@WebFilter("/*")
public class RestrictedToUser implements Filter {

    private JWTHandler jwt;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println(jwt.createJWT("id", "issuer", "subject"));

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        jwt = new JWTHandler();
    }

}
