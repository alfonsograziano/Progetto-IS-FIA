package genetic;

import java.util.ArrayList;

public interface Crossover {
    public ArrayList<SpecGene> cross(SpecGene g1, SpecGene g2);
}
