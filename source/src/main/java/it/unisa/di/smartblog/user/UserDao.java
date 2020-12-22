package it.unisa.di.smartblog.user;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    static{
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("smartblog");

        } catch(NamingException e){
            e.printStackTrace();
        }
    }

    public synchronized User getByEmail(String email) throws SQLException, EmptyEmailException {
        if(email==null || email.equals("")) throw new EmptyEmailException("Email cannot be empty");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM user WHERE email = ?";
        User user = new User();

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        rs.next();

        user.setId(rs.getInt("id"));
        if(rs.getInt("active")==1) user.setActive(true);
        else user.setActive(false);
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));

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

        return user;
    }

    public synchronized boolean saveUser(User user) throws SQLException, UserMismatchException{
        if(user==null) throw new UserMismatchException("User cannot be null");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO user (active, email, username, password) VALUES (1, ?, ?, ?)";

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

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

        return true;
    }

    public synchronized Manager getManager(User user) throws UserMismatchException, SQLException{
        if(user==null) throw new UserMismatchException("User cannot be null");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM manager where manager.id = ?";

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();

        rs.next();
        Manager manager = new Manager(user.getUsername(), user.getPassword(), user.getEmail(), user.getReviews(), rs.getString("phoneNumber"));

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

        return manager;
    }

    public synchronized Reviewer getReviewer(User user) throws UserMismatchException, SQLException{
        if(user==null) throw new UserMismatchException("User cannot be null");

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "SELECT * FROM reviewer where reviewer.id = ?";

        conn = ds.getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery();

        rs.next();
        Reviewer reviewer = new Reviewer(user.getUsername(), user.getPassword(), user.getEmail(), user.getReviews(), rs.getString("phoneNumber"), rs.getString("rank"));

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

        return reviewer;
    }

    private static DataSource ds;
}