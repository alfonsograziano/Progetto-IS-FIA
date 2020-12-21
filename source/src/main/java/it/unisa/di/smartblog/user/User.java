package it.unisa.di.smartblog.user;

import it.unisa.di.smartblog.review.Review;
import java.util.List;

public class User {

    public User(){};

    public User(String username, String password, String email) {
        this.active = true;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, List<Review> reviews) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.reviews = reviews;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public boolean addReview(Review review){
        return reviews.add(review);
    }

    private int id;
    private boolean active;
    private String username;
    private String password;
    private String email;
    private List<Review> reviews;
}
