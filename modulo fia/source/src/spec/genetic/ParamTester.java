package spec.genetic;

import spec.Spec;

import java.util.ArrayList;

public class ParamTester {


    public static void main(String[] args) throws Exception {
        testMutation();
    }

    public static void testMutation(){
        Mutation m = new RandomMutation();

        ArrayList<Spec> specs = new ArrayList<>();

        specs.add(new Spec(1));
        specs.add(new Spec(2));
        specs.add(new Spec(3));
        specs.add(new Spec(4));
        specs.add(new Spec(5));
        specs.add(new Spec(6));

        SpecGene gene = new SpecGene(specs);

        Spec mutation = new Spec(7);
        System.out.println("Null mutation => " +m.mutate(gene,new Spec(5)));

        System.out.println(m.mutate(gene,mutation));

    }


}
