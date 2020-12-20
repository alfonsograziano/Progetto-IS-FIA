package  it.unisa.di.smartblog.spec;

import it.unisa.di.smartblog.user.Reviewer;

public class Spec {

    public Spec() {
        fit = 0.0;
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

    public void setReviewerId(Reviewer reviewer) {
        this.reviewer = reviewer;
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
                "id=" + id +
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
                '}';
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
    private double normalizedBattery;
}
