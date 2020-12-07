package genetic;

import scraper.Spec;

import java.util.ArrayList;

public class FitnessHelperSpec implements FitnessHelper {

    private double performance;
    private double camera;
    private double display;

    public FitnessHelperSpec(double performance, double camera, double display){
        this.performance = performance;
        this.camera = camera;
        this.display = display;
    }

    @Override
    public double computeFit(SpecGene gene) {
        double fit = 0;

        final int powFactor = 2;
        final int multiplyFactor = 2;

        for(Object s: gene.getGene()) {
            Spec spec = (Spec) s;

            double performanceF = Math.abs(performance - spec.getPerformance());
            double cameraF = Math.abs(camera - spec.getCamera());
            double displayF = Math.abs(display - spec.getDisplay());


            if (performanceF > 1) fit += Math.pow(performanceF, powFactor);
            else fit += performanceF * multiplyFactor;

            if (cameraF > 1) fit += Math.pow(cameraF, powFactor);
            else fit += cameraF * multiplyFactor;

            if (displayF > 1) fit += Math.pow(displayF, powFactor);
            else fit += displayF * multiplyFactor;
        }

        return fit;
    }

}
