package genetic;

public interface Selection {

    Population select(Population population, int maxPopulationSize);
    Population select(Population population);

}
