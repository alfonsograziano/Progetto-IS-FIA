package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.filter.Message;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="ReviewInspectionControl",
        value="/api/changeReviewStatus")
public class ReviewInspectionControl extends HttpServlet {
    static {
        rm = new ReviewManager();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        boolean approved = Boolean.parseBoolean(request.getParameter("approved"));

        try {
            rm.approvation(id, approved);
            request.setAttribute("response", new Message("Review status changed"));
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Review not found"));
        }
    }

    private static ReviewManager rm;
}
