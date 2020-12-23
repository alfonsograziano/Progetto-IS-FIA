package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.user.*;

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
    static{
        um = new UserManager();
        rm = new ReviewManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =  request.getAttribute("email").toString();

        User user = null;
        try {
            user = um.getUserInfoByEmail(email);
            List<Review> reviews = rm.searchReviewsByUser(user.getId());
            System.out.println(reviews);
            for(Review r: reviews) user.addReview(r);

            Manager m1 = um.isManager(user);
            Reviewer r1 = um.isReviewer(user);

            if(m1 != null){
                request.setAttribute("response", m1);
            }else if(r1 != null){
                request.setAttribute("response", r1);
            }else{
                request.setAttribute("response", user);
            }

        }  catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("response", new Error("Error..."));
        } catch (CredentialsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("response", new Error(e.getMessage()));
        }


    }

    private static ReviewManager rm;
    private static UserManager um;
}
