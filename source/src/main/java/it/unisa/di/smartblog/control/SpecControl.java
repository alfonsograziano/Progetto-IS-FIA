package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.user.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="SpecControl",
        value="/api/spec")
public class SpecControl extends HttpServlet {
    static{
        sm = new SpecsManager();
        rm = new ReviewManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            //System.out.println("Nella servlet");
            Spec res =  sm.searchById(id);
            List<Review> reviews = rm.searchReviewsBySpec(res.getId());
            res.setReviews(reviews);
            //System.out.println(reviews);

            request.setAttribute("response",res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Spec not found..."));
        }

    }

    private static SpecsManager sm;
    private static ReviewManager rm;
}
