package scraper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Spec {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String date;
    private String so;
    private String cpu;
    private String chipset;
    private String gpu;
    private String ram;
    private String memory;
    private String screenSize;
    private String image;

    private double price;

    private double display;
    private double camera;
    private double performance;
    private int battery;

    private double fit;


    public Spec() {
        fit = 0.0;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "Spec{" +

                "name='" + name + '\'' +
                ", display=" + display +
                ", camera=" + camera +
                ", performance=" + performance +
                ", data=" + date +
                ", id=" + id +
                ", price=" + price +

                '}';
    }


    public double getFitValue (){
        return this.fit;
    }

    public void setFitValue(double fit){
        this.fit = fit;
    }

    public double getFit(double peformance, double camera, double display){
        double fit = 0;

        final int powFactor = 4;
        final int multiplyFactor = 2;

        double performanceF = Math.abs(peformance-this.getPerformance());
        double cameraF = Math.abs(camera-this.getCamera());
        double displayF = Math.abs(display-this.getDisplay());


        if(performanceF > 1) fit += Math.pow(performanceF,powFactor);
        else fit += performanceF*multiplyFactor;

        if(cameraF > 1) fit += Math.pow(cameraF,powFactor);
        else fit += cameraF*multiplyFactor;

        if(displayF > 1) fit += Math.pow(displayF,powFactor);
        else fit += displayF*multiplyFactor;

        String specDate = this.getDate();
        if(specDate != null){
            Date date1;
            //Converto la stringa in data
            try {
                date1=new SimpleDateFormat("yyyy/MM").parse(specDate);

                double months = getMonthsDifference(date1,new Date())/3.00;

                // 9mesi => 9/3 = 3 => 3^pow 2 => 9

                fit += Math.pow(months, powFactor);

                //System.out.println(this.now + " | " + date1 +" => "+months);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            //System.out.println("Cannot find date => " + spec);
        }


        return fit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * 12 + date1.getMonth();
        int m2 = date2.getYear() * 12 + date2.getMonth();
        return m2 - m1 + 1;
    }
}
