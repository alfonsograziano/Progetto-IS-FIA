package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/review/pending")
public class ReviewControl extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewManager rm = new ReviewManager();
        try {
            List<Review> reviews = rm.searchPendingReviews();
            request.setAttribute("response", reviews);

        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("response", new Error("Wrong request"));
        }
    }
}
