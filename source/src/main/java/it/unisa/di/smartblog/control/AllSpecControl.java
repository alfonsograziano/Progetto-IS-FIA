package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(
        name="AllSpecControl",
        value="/api/spec/all")
public class AllSpecControl extends HttpServlet {
    static{
        sm = new SpecsManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Spec> res = sm.searchAll();
            request.setAttribute("response",res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Spec not found..."));
        }

    }

    private static SpecsManager sm;
}
