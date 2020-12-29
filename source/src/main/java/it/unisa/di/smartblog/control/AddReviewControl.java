package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.filter.Message;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.review.ReviewMismatchException;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.user.CredentialsException;
import it.unisa.di.smartblog.user.User;
import it.unisa.di.smartblog.user.UserManager;

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
    static{
        rm = new ReviewManager();
        sm = new SpecsManager();
        um = new UserManager();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalScore = Integer.parseInt(request.getParameter("totalScore"));
        int performance = Integer.parseInt(request.getParameter("performance"));
        int display = Integer.parseInt(request.getParameter("display"));
        int camera = Integer.parseInt(request.getParameter("camera"));
        int battery = Integer.parseInt(request.getParameter("battery"));
        int specId = Integer.parseInt(request.getParameter("specId"));
        String text = request.getParameter("text");

        try {
            Spec spec = sm.searchById(specId);
            User user = um.getUserInfoByEmail((String)request.getAttribute("email"));
            rm.createReview(totalScore, performance, display, camera, battery, text, spec, user);
            request.setAttribute("response", new Message("Review saved"));
        } catch (ReviewMismatchException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            request.setAttribute("response", new Error(e.getMessage()));
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Cannot add review, please check params"));
        } catch (CredentialsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            request.setAttribute("response", new Error(e.getMessage()));
        }
    }

    private static ReviewManager rm;
    private static SpecsManager sm;
    private static UserManager um;
}
