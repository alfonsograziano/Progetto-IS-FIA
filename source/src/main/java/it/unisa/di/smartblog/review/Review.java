package it.unisa.di.smartblog.review;

import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.user.User;

public class Review {
    public Review(){};

    public Review(String state, double totalScore, double battery, double performance, double display, double camera, String text, Spec spec, User user) {
        this.state = state;
        this.totalScore = totalScore;
        this.battery = battery;
        this.performance = performance;
        this.display = display;
        this.camera = camera;
        this.text = text;
        this.spec = spec;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getDisplay() {
        return display;
    }

    public void setDisplay(double display) {
        this.display = display;
    }

    public double getCamera() {
        return camera;
    }

    public void setCamera(double camera) {
        this.camera = camera;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int id;
    private String state;
    private double totalScore;
    private double battery;
    private double performance;
    private double display;
    private double camera;
    private String text;
    private Spec spec;
    private User user;
}