package it.unisa.di.smartblog.review;

import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.user.User;

import java.sql.SQLException;
import java.util.List;

public class ReviewManager {
    static{
        dao = new ReviewDao();
    }

    public boolean createReview(int totalScore, int performance, int display, int camera, int battery, String text, Spec spec, User user) throws ReviewMismatchException, SQLException {
        if(spec==null || user==null) throw new ReviewMismatchException("Spec or User cannot be null");
        if(totalScore<0 || totalScore>5 || performance<0 || performance>5 || display<0 || display>5 || camera<0 || camera>5 || battery<0 || battery>5) throw new ReviewMismatchException("Fields must be between 0 and 5, inclusive");
        Review review = new Review(totalScore, battery, performance, display, camera, text, spec, user);
        return dao.saveReview(review);
    }

    public List<Review> searchReviewsByUser(int id) throws SQLException{
        return dao.getByUser(id);
    }

    public List<Review> searchReviewsBySpec(int id) throws SQLException{
        return dao.getBySpecId(id);
    }

    public List<Review> searchPendingReviews() throws SQLException{
        return dao.getPending();
    }

    public Review searchReviewInfo(int id) throws SQLException{
        return dao.getById(id);
    }

    public boolean approvation(int id, boolean approved) throws SQLException{
        return dao.approveReview(id, approved);
    }

    private static ReviewDao dao;
}
