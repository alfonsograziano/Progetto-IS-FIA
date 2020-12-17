package genetic;

import java.util.ArrayList;

public interface Crossover {
    public SpecGene cross(SpecGene g1, SpecGene g2);
}
