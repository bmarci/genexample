import java.util.ArrayList;
import java.util.Collections;

public class Main  {
    static final int POPULATION = 1250;
    static final char[] goal = "thisisentirelyperfection".toCharArray();
    static final int GOAL_LENGTH = goal.length;
    static final double MUTATION_RATE = 0.01;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<DNA> dnas = new ArrayList<>();
        int generationCount = 1;
        for (int i = 0; i < POPULATION; i++)  {
            DNA dna = generateDNA();
            dna.calculateFitness(goal);
            dnas.add(dna);
        }
        Collections.sort(dnas);
        System.out.println("Generation 1: ");
        System.out.println(dnas);
        System.out.println("==============================================================");

        while( dnas.get(0).fitness < 0.99 ) {
            ArrayList<DNA> nextgen = new ArrayList<>();
            nextgen.add(0, dnas.get(0));
            generationCount++;
            for (int i = 1; i < POPULATION; i++) {
                DNA dna = dnas.get(i - 1).createChild(dnas.get(i));
                dna.mutate(MUTATION_RATE);
                dna.calculateFitness(goal);
                nextgen.add(i, dna);
            }
            Collections.sort(nextgen);
            System.out.println("Generation " + generationCount + ": ");
            System.out.println(nextgen);
            System.out.println("Generation " + generationCount + " =====" + nextgen.get(0) + "===========");
            dnas = (ArrayList<DNA>) nextgen.clone();
            //Thread.sleep(200);
        }
    }

    public static DNA generateDNA() {
        char[] genes = new char[GOAL_LENGTH];
        for(int i = 0; i < GOAL_LENGTH; i++) {
            genes[i] = DNA.getRandomChar();
        }
        return new DNA(genes);
    }

}
