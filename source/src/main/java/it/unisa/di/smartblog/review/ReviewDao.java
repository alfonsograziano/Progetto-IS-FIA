package it.unisa.di.smartblog.review;

import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.user.User;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    static{
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("smartblog");

        } catch(NamingException e){
            e.printStackTrace();
        }
    }

    public synchronized Review getById(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM review INNER JOIN USER ON review.userId=user.id INNER JOIN spec ON review.specId=spec.id WHERE review.id=?;";
        Review review = new Review();

        try {

            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            review.setId(rs.getInt(1));
            review.setTotalScore(rs.getInt("totalScore"));
            review.setPerformance(rs.getInt("performance"));
            review.setDisplay(rs.getInt("display"));
            review.setBattery(rs.getInt("battery"));
            review.setCamera(rs.getInt("camera"));
            review.setText(rs.getString("text"));
            review.setState(rs.getString("state"));

            User user = new User();
            user.setId(rs.getInt("userId"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            review.setUser(user);

            Spec spec = new Spec();
            spec.setId(rs.getInt("specId"));
            spec.setName(rs.getString("name"));
            review.setSpec(spec);

        } catch(Exception e) {
            throw e;
        } finally {

            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return review;
    }

    public synchronized List<Review> getBySpecId(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM review INNER JOIN user ON review.userId=user.id INNER JOIN spec ON review.specId=spec.id WHERE spec.id=? AND review.state='approved'";
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setTotalScore(rs.getInt("totalScore"));
                review.setPerformance(rs.getInt("performance"));
                review.setDisplay(rs.getInt("display"));
                review.setBattery(rs.getInt("battery"));
                review.setCamera(rs.getInt("camera"));
                review.setText(rs.getString("text"));
                review.setState(rs.getString("state"));

                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setActive(true);
                review.setUser(user);

                reviews.add(review);
            }
        } catch (SQLException sql){
            throw sql;

        } finally {
            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }


        return reviews;
    }

    public synchronized List<Review> getByUser(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM review INNER JOIN user ON review.userId=user.id INNER JOIN spec ON review.specId=spec.id WHERE user.id=?";
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setTotalScore(rs.getInt("totalScore"));
                review.setPerformance(rs.getInt("performance"));
                review.setDisplay(rs.getInt("display"));
                review.setBattery(rs.getInt("battery"));
                review.setCamera(rs.getInt("camera"));
                review.setText(rs.getString("text"));
                review.setState(rs.getString("state"));

                Spec spec = new Spec();
                spec.setId(rs.getInt("specId"));
                spec.setName(rs.getString("name"));
                review.setSpec(spec);

                reviews.add(review);
            }
        } catch (SQLException sql){
            throw sql;

        } finally {
            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return reviews;
    }

    public synchronized List<Review> getPending() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM review INNER JOIN user ON review.userId=user.id INNER JOIN spec ON review.specId=spec.id WHERE review.state='pending'";
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setTotalScore(rs.getInt("totalScore"));
                review.setPerformance(rs.getInt("performance"));
                review.setDisplay(rs.getInt("display"));
                review.setBattery(rs.getInt("battery"));
                review.setCamera(rs.getInt("camera"));
                review.setText(rs.getString("text"));
                review.setState(rs.getString("state"));

                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setActive(true);
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                review.setUser(user);

                Spec spec = new Spec();
                spec.setId(rs.getInt("specId"));
                spec.setName(rs.getString("name"));
                review.setSpec(spec);

                reviews.add(review);
            }
        } catch (SQLException sql){
            throw sql;

        } finally {
            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return reviews;
    }

    public synchronized boolean saveReview(Review review) throws ReviewMismatchException, SQLException{
        if(review==null) throw new ReviewMismatchException("Review cannot be null");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO review (totalScore, performance, display, battery, camera, text, state, userId, specId) VALUES (?, ?, ?, ?, ?, ?, 'pending', ?, ?)";

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, review.getTotalScore());
            ps.setInt(2, review.getPerformance());
            ps.setInt(3, review.getDisplay());
            ps.setInt(4, review.getBattery());
            ps.setInt(5, review.getCamera());
            ps.setString(6, review.getText());
            ps.setInt(7, review.getUser().getId());
            ps.setInt(8, review.getSpec().getId());
            ps.executeUpdate();
        } catch (SQLException sql){
            throw sql;

        } finally {
            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public synchronized boolean deleteReview(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM review WHERE review.id = ?";

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sql){
            throw sql;

        } finally {
            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public synchronized boolean approveReview(int id, boolean approved) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE review SET state = ? WHERE review.id = ?";

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            if(approved) ps.setString(1, "approved");
            else ps.setString(1, "rejected");
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException sql){
            throw sql;

        } finally {
            try {
                conn.close();

            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                ps.close();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private static DataSource ds;
}
