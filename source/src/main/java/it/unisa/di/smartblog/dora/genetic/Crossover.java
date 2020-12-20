package it.unisa.di.smartblog.dora.genetic;

public interface Crossover {
    public SpecGene cross(SpecGene g1, SpecGene g2);
}
