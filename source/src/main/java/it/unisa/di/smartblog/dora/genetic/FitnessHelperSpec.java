package it.unisa.di.smartblog.dora.genetic;


import it.unisa.di.smartblog.spec.Spec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FitnessHelperSpec implements FitnessHelper {

    private double performance;
    private double camera;
    private double display;
    private double battery;
    private Date now;

    private int minBattery, maxBattery;

    private final int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * 12 + date1.getMonth();
        int m2 = date2.getYear() * 12 + date2.getMonth();
        return m2 - m1 + 1;
    }

    public FitnessHelperSpec(double performance, double camera, double display, Date now, double battery, int minBattery, int maxBattery){
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
        for(Object s: gene.getGene())  fit += computeSpecFit((Spec) s);
        return fit/gene.getGene().size();
    }


    public double computeVariance(SpecGene gene) {
        double fit = 0;
        double powedFit = 0;
        for(Object s: gene.getGene()) {
            double localFit = computeSpecFit((Spec) s);
            fit += localFit;
            powedFit += Math.pow(localFit,2);
        }
        return powedFit-Math.pow(fit/gene.getGene().size(),2);
    }

    private double expand(double in) {
        double out;
        if(in < 2.67) out = in*1.2;
        else out = Math.pow((in/2),4);
        return out;
    }

    private double aging(String specDate, Date now) {
        double aging = 0;
        try {
            Date date1=new SimpleDateFormat("yyyy/MM").parse(specDate);
            double months = getMonthsDifference(date1,now);
            aging = Math.pow((months/25),4);
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
