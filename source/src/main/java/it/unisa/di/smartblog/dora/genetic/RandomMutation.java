package it.unisa.di.smartblog.dora.genetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RandomMutation implements Mutation {

    @Override
    public SpecGene mutate(SpecGene gene, Object mutation) {
        ArrayList geneC = (ArrayList) gene.getGene().clone();
        geneC.set(0, mutation);

        Set verificationSet = new HashSet<>(geneC);
        if(geneC.size() > verificationSet.size()){
            return null;
        }

        return new SpecGene(geneC);
    }

}
