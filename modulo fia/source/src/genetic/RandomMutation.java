package genetic;

public class RandomMutation implements Mutation {

    @Override
    public void mutate(SpecGene gene, Object mutation) {
        int index = randomInt(0,gene.getGene().size());
        mutateByIndex(gene, index,mutation);
    }

    private void mutateByIndex(SpecGene gene, int index, Object mutation) {
        gene.getGene().set(index, mutation);

    }

    public static int randomInt(int Min, int Max){
        return (int) (Math.random()*(Max-Min))+Min;
    }
}
