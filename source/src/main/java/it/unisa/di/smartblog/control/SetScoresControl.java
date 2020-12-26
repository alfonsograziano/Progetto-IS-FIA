package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.filter.Message;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.user.CredentialsException;
import it.unisa.di.smartblog.user.Reviewer;
import it.unisa.di.smartblog.user.User;
import it.unisa.di.smartblog.user.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SetScoresControl",
        value="/api/spec/setscores")
public class SetScoresControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager um = new UserManager();
        SpecsManager sm = new SpecsManager();

        double performance = Double.parseDouble(request.getParameter("performance"));
        double display = Double.parseDouble(request.getParameter("display"));
        double camera = Double.parseDouble(request.getParameter("camera"));
        int specId = Integer.parseInt(request.getParameter("specId"));

        String email = request.getAttribute("email").toString();
        try {
            User r1 = um.getUserInfoByEmail(email);
            sm.setScores(r1.getId(), specId,performance, display, camera);
            request.setAttribute("response", new Message("Scores updated!"));

        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            request.setAttribute("response", new Error("Error..."));
        } catch (CredentialsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            request.setAttribute("response", new Error(e.getMessage()));
        }
    }

}
