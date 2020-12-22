package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.user.EmptyEmailException;
import it.unisa.di.smartblog.user.User;
import it.unisa.di.smartblog.user.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(
        name="ProfileControl",
        value="/api/profile"
        )
public class ProfileControl extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =  request.getAttribute("email").toString();
        UserManager um = new UserManager();
        ReviewManager rm = new ReviewManager();

        User user = null;
        try {
            user = um.getUserInfoByEmail(email);
            List<Review> reviews = rm.searchReviewsByUser(user.getId());
            System.out.println(reviews);
            for(Review r: reviews) user.addReview(r);

            request.setAttribute("response", user);
        } catch (EmptyEmailException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("response", new Error("Error..."));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("response", new Error("Error..."));
        }




    }
}
