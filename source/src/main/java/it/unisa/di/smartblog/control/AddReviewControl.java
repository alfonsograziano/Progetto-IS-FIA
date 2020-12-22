package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.filter.Message;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.review.ReviewMismatchException;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="AddReviewControl",
        value="/api/review/add")
public class AddReviewControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewManager rm = new ReviewManager();
        SpecsManager sm = new SpecsManager();
        //UserManager rm = new ReviewManager();

        int totalScore = Integer.parseInt(request.getParameter("totalScore"));
        int performance = Integer.parseInt(request.getParameter("performance"));
        int display = Integer.parseInt(request.getParameter("display"));
        int camera = Integer.parseInt(request.getParameter("camera"));
        int battery = Integer.parseInt(request.getParameter("battery"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int specId = Integer.parseInt(request.getParameter("specId"));

        String text = request.getParameter("text");

        Spec spec = null;
        try {
            spec = sm.searchById(specId);
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Cannot find user, please check params"));
            return;
        }

        //TODO: Quando l'user manage è pronto aggiungi anche qui la call al manage
        User user = new User();
        user.setId(userId);

        try {
            rm.createReview(totalScore, performance, display, camera, battery, text, spec, user);
            request.setAttribute("response", new Message("Review saved"));
        } catch (ReviewMismatchException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            request.setAttribute("response", new Error("Review parameters wrong"));
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Cannot add review, please check params"));
        }
    }

}
