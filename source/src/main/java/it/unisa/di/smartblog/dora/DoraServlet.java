package it.unisa.di.smartblog.dora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dora")
public class DoraServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoraManager dm = new DoraManager();
        try {
            request.setAttribute("response",dm.findSpectByParams(6,7,8,1,800));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
