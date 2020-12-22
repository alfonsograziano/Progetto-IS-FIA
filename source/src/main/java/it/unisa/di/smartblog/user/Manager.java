package it.unisa.di.smartblog.user;

import it.unisa.di.smartblog.review.Review;

import java.util.List;

public class Manager extends User{

    public Manager(){
        super();
    }

    public Manager(String username, String password, String email, List<Review> reviews, String phoneNumber) {
        super(username, password, email, reviews);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;
}
