package it.unisa.di.smartblog.review;

import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.user.User;

public class Review {
    public Review(){};

    public Review(int totalScore, int battery, int performance, int display, int camera, String text, Spec spec, User user) {
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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", totalScore=" + totalScore +
                ", battery=" + battery +
                ", performance=" + performance +
                ", display=" + display +
                ", camera=" + camera +
                ", text='" + text + '\'' +
                ", spec=" + spec +
                ", user=" + user +
                '}';
    }

    private int id;
    private String state;
    private int totalScore;
    private int battery;
    private int performance;
    private int display;
    private int camera;
    private String text;
    private Spec spec;
    private User user;
}