package  it.unisa.di.smartblog.spec;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.user.Reviewer;

import java.util.List;
import java.util.Objects;

public class Spec {

    public Spec() {
        this.fit = 0.0;
    }

    public Spec(String name, String date, String image, String so, String cpu, String chipset, String gpu, String ram, String memory, String screenSize, int battery, double price) {
        this.name = name;
        this.date = date;
        this.image = image;
        this.so = so;
        this.cpu = cpu;
        this.chipset = chipset;
        this.gpu = gpu;
        this.ram = ram;
        this.memory = memory;
        this.screenSize = screenSize;
        this.battery = battery;
        this.price = price;
    }

    public double getFitValue (){
        return this.fit;
    }

    public void setFitValue(double fit){
        this.fit = fit;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
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

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public double getNormalizedBattery() {
        return normalizedBattery;
    }

    public void setNormalizedBattery(double normalizedBattery) {
        this.normalizedBattery =  Math.floor(normalizedBattery * 100) / 100;
    }

    @Override
    public String toString() {
        return "Spec{" +
                "fit=" + fit +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                ", so='" + so + '\'' +
                ", cpu='" + cpu + '\'' +
                ", chipset='" + chipset + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram='" + ram + '\'' +
                ", memory='" + memory + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", battery=" + battery +
                ", display=" + display +
                ", camera=" + camera +
                ", performance=" + performance +
                ", price=" + price +
                ", reviewer=" + reviewer +
                ", reviews=" + reviews +
                ", normalizedBattery=" + normalizedBattery +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spec)) return false;
        Spec spec = (Spec) o;
        return Double.compare(spec.fit, fit) == 0 && getId() == spec.getId() && getBattery() == spec.getBattery() && Double.compare(spec.getDisplay(), getDisplay()) == 0 && Double.compare(spec.getCamera(), getCamera()) == 0 && Double.compare(spec.getPerformance(), getPerformance()) == 0 && Double.compare(spec.getPrice(), getPrice()) == 0 && Double.compare(spec.getNormalizedBattery(), getNormalizedBattery()) == 0 && Objects.equals(getName(), spec.getName()) && Objects.equals(getDate(), spec.getDate()) && Objects.equals(getImage(), spec.getImage()) && Objects.equals(getSo(), spec.getSo()) && Objects.equals(getCpu(), spec.getCpu()) && Objects.equals(getChipset(), spec.getChipset()) && Objects.equals(getGpu(), spec.getGpu()) && Objects.equals(getRam(), spec.getRam()) && Objects.equals(getMemory(), spec.getMemory()) && Objects.equals(getScreenSize(), spec.getScreenSize()) && Objects.equals(getReviewer(), spec.getReviewer()) && Objects.equals(getReviews(), spec.getReviews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fit, getId(), getName(), getDate(), getImage(), getSo(), getCpu(), getChipset(), getGpu(), getRam(), getMemory(), getScreenSize(), getBattery(), getDisplay(), getCamera(), getPerformance(), getPrice(), getReviewer(), getReviews(), getNormalizedBattery());
    }

    private double fit;
    private int id;
    private String name;
    private String date;
    private String image;
    private String so;
    private String cpu;
    private String chipset;
    private String gpu;
    private String ram;
    private String memory;
    private String screenSize;
    private int battery;
    private double display;
    private double camera;
    private double performance;
    private double price;
    private Reviewer reviewer;
    private List<Review> reviews;
    private double normalizedBattery;
}
