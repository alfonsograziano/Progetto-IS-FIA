package it.unisa.di.smartblog.user;

import it.unisa.di.smartblog.review.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    public User(){
        this.reviews = new ArrayList<>();
    };

    public User(String username, String password, String email) {
        this.active = true;
        this.username = username;
        this.password = password;
        this.email = email;
        this.reviews = new ArrayList<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && isActive() == user.isActive() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getReviews(), user.getReviews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getUsername(), getPassword(), getEmail(), getReviews());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", active=" + active +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", reviews=" + reviews +
                '}';
    }

    private int id;
    private boolean active;
    private String username;
    private String password;
    private String email;
    private List<Review> reviews;
}