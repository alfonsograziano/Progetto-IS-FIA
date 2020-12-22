package it.unisa.di.smartblog.user;

import it.unisa.di.smartblog.review.Review;

import java.util.List;

public class Reviewer extends User{
    public Reviewer(){
        super();
    }

    public Reviewer(String username, String password, String email, List<Review> reviews, String phoneNumber, String rank) {
        super(username, password, email, reviews);
        this.phoneNumber = phoneNumber;
        this.rank = rank;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    private String phoneNumber;
    private String rank;
}
