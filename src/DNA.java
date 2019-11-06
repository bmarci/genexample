import java.util.ArrayList;
import java.util.Arrays;

public class DNA implements Comparable<DNA>{

    static final ArrayList<Character> characters = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));

    char[] genes;

    double fitness;

    public DNA(final char[] genes) {
        this.genes = genes;
    }

    public DNA createChild(final DNA otherDNA) {
        double relativeFitness = fitness / (fitness + otherDNA.fitness);
        char[] childGenes = new char[genes.length];
        for (int i = 0; i < genes.length; i++) {
            childGenes[i] = Math.random() > relativeFitness ? otherDNA.genes[i] : genes[i];
        }
        return new DNA(childGenes);
    }

    public void mutate(final double mutationRate) {
        for (int i = 0; i < genes.length; i++) {
            if ( Math.random() < mutationRate ) {
                genes[i] = getRandomChar();
            }
        }
    }

    public void calculateFitness(char[] target) {
        int score = 0;
        for (int i = 0; i < target.length; i++) {
            if (genes[i] == target[i]) {
                score++;
            }
        }
        fitness = (double) score / (double)target.length;
    }

    public static char getRandomChar() {
        return characters.get((int)(Math.random() * characters.size()));
    }

    @Override
    public int compareTo(final DNA dna) {
        return fitness == dna.fitness ? 0 : fitness > dna.fitness ? -1 : 1;
    }

    @Override
    public String toString() {
        return "DNA{" +
                "genes=" + Arrays.toString(genes) +
                ", fitness=" + fitness +
                "}\n";
    }
}
