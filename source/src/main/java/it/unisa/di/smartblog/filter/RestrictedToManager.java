package it.unisa.di.smartblog.filter;

import it.unisa.di.smartblog.security.JWTHandler;
import it.unisa.di.smartblog.user.User;
import it.unisa.di.smartblog.user.UserManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Aggiungere qui le servlet su cui devo eseguire il filtro
@WebFilter(servletNames = {
        "CreateSpecControl", "DeleteSpecControl",
})
public class RestrictedToManager implements Filter {

    private JWTHandler jwt;

    public void destroy() {
    }

    /*
    @WebFilter(
    urlPatterns = "/admin/*",
    filterName = "AdminFilter",
    description = "Filter all admin URLs")
     */

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String auth = httpRequest.getHeader("Authorization");
        if(auth != null){
            String token = auth.split("Bearer ")[1];
            System.out.println("token => "+ auth);
            String email = null;
            try{
                email = jwt.decode(token);
                UserManager um = new UserManager();
                User user = um.getUserInfoByEmail(email);
                if(um.isManager(user)!=null){

                    httpRequest.setAttribute("email", email);
                    chain.doFilter(req, resp);
                }
            }catch(Exception e){
                System.out.println("Signature non valida...");
            }
        }else{
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = httpResponse.getWriter();
            out.print("User unhautorized...");
            out.flush();
        }
    }

    public void init(FilterConfig config) throws ServletException {
        jwt = new JWTHandler();
    }

}
