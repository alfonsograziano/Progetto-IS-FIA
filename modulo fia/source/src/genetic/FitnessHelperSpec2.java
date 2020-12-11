package genetic;

import scraper.Spec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FitnessHelperSpec2 implements FitnessHelper {

    private double performance;
    private double camera;
    private double display;
    private double battery;
    private Date now;

    private int minBattery, maxBattery;

    public static final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * 12 + date1.getMonth();
        int m2 = date2.getYear() * 12 + date2.getMonth();
        return m2 - m1 + 1;
    }

    public FitnessHelperSpec2(double performance, double camera, double display, Date now, double battery, int minBattery, int maxBattery){
        this.performance = performance;
        this.camera = camera;
        this.display = display;
        this.now = now;
        this.minBattery = minBattery;
        this.maxBattery = maxBattery;
        this.battery = battery;
    }

    @Override
    public double computeFit(SpecGene gene) {
        double fit = 0;

        for(Object s: gene.getGene()) {
            fit += computeSpecFit((Spec) s);
        }

        return fit;
    }

    public double expand(double in) {
        double out = in;
        //performance 7 => 4 => scarto = 3
        //performance 7 => 6.8 => scarto = 0.2 => 0.04

        //out = ((in+5)/5)*5;
/*
        if(out < 1){
            out = out*2;
        }else{
            out = Math.pow(out, 4);
        }
*/
        out = Math.pow(out, 2);

        return out;
    }

    public double aging(String specDate, Date now) {
        //Converto la stringa in data
        double aging = 0;
        try {
            Date date1=new SimpleDateFormat("yyyy/MM").parse(specDate);

            double months = getMonthsDifference(date1,now);
            //aging =
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return aging;
    }



    public double computeSpecFit(Spec spec) {
        double fit = 0;

        double performanceF = Math.abs(performance - spec.getPerformance());
        double cameraF = Math.abs(camera - spec.getCamera());
        double displayF = Math.abs(display - spec.getDisplay());

        //Normalizzo il valore della batteria in decimi
        spec.setNormalizedBattery(spec.getBattery()/(maxBattery/10.0));
        double batteryF = Math.abs(battery - spec.getNormalizedBattery());

        fit+= expand(performanceF);
        fit+= expand(cameraF);
        fit+= expand(displayF);
        fit+= expand(batteryF);
        fit+=aging(spec.getDate(), this.now);

        spec.setFitValue(fit);
        return fit;
    }

}