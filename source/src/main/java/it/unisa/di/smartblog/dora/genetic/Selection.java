package it.unisa.di.smartblog.dora.genetic;

public interface Selection {
    Population select(Population population, int maxPopulationSize);
}
