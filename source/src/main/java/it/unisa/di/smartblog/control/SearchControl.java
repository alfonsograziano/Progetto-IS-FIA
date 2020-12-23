package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.spec.EmptyFieldException;
import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.user.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="SearchControl",
        value="/api/search")
public class SearchControl extends HttpServlet {
    static{
        sm = new SpecsManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("s");
        try {
            request.setAttribute("response", sm.searchByName(search));
        } catch (EmptyFieldException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static SpecsManager sm;
}
