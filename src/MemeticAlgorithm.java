import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MemeticAlgorithm {
    private final Configuration config;
    private final TournamentSelection selector;
    private final Crossover crosser;
    private final RandomMutation mutator;
    private final DavisBitHillClimbing climber;
    private final StrongElitismReplacement replacer;
    private final Random rand = new Random();
    private double capacity;
    private double[][] items;
    private String[][] initialSolutions;

    public MemeticAlgorithm() throws FileNotFoundException {
        this.config = new Configuration();
        setInitialSolutions();
        this.selector = new TournamentSelection(items.length);
        this.crosser = new Crossover();
        this.mutator = new RandomMutation();
        this.climber = new DavisBitHillClimbing();
        this.replacer = new StrongElitismReplacement(capacity);
    }

    /**
     * This method initialises the initial solutions using settings from the Configuration class.
     */
    private void setInitialSolutions() throws FileNotFoundException {
        File file = config.PROBLEM_INSTANCE;
        Scanner contents = new Scanner(file);
        Initialisation initial = new Initialisation(contents, config.NUM_OF_RANDOM_SOLUTIONS, config.NUM_OF_GREEDY3_SOLUTIONS);
        initialSolutions = initial.getInitialSolutions();
        items = initial.items;
        capacity = initial.capacity;
    }

    /**
     * This method performs the Memetic algorithms main loop.
     */
    public void startMemeticLoop() throws IOException {
        for (int h = 0; h < config.NUM_OF_TRIALS; h++) {
//            2d arrays for solutions is created with format {{profit, weight, bit representation}, ...}
            String[][] solutions = initialSolutions;
            String[][] bestAndWorstPairs = new String[config.numberOf][2];

            for (int j = 0; j < config.NUM_OF_GENERATIONS; j++) {
                String p1Bits;
                String p2Bits;
                String[] c1 = {"0", "0", "0"};
                String[] c2 = {"0", "0", "0"};
                double[] deltaEval = new double[2];

//              Tournament selection
                p1Bits = selector.applyTournamentSelection(solutions);
                p2Bits = selector.applyTournamentSelection(solutions);

//              Crossover
                int intersectionPoint = rand.nextInt(solutions.length);
                c1[2] = crosser.crossoverBits(p1Bits, p2Bits, intersectionPoint);
                c2[2] = crosser.crossoverBits(p2Bits, p1Bits, intersectionPoint);
                calculateProfitAndWeight(c1);
                calculateProfitAndWeight(c2);
                deltaEval[0] = Double.parseDouble(c1[0]);
                deltaEval[1] = Double.parseDouble(c2[0]);

//              Random mutation
                mutator.performRandomMutation(c1, items, config.IOM);
                mutator.performRandomMutation(c2, items, config.IOM);
                deltaEvaluation(deltaEval, c1, c2);

//              Local Search
                climber.performDBHC(c1, items, capacity, config.DOS);
                climber.performDBHC(c2, items, capacity, config.DOS);
                deltaEvaluation(deltaEval, c1, c2);

//              Strong elitism replacement
                if (deltaEval[0] > 0 || deltaEval[1] > 0) {
                    solutions = replacer.performReplacement(solutions, c1, c2);
                }

//              Store generations best/worst objective value, when trial finishes these can be written onto text file
                if (j < config.numberOf) {
                    bestAndWorstPairs[j][0] = String.valueOf(solutions[0][0]);
                    bestAndWorstPairs[j][1] = String.valueOf(solutions[3][0]);
                }
            }
            trialOutput(h+1, solutions[0][2], solutions[0][0], bestAndWorstPairs);
        }
    }

    /**
     * creates a new file and writes a finished trial's data to it.
     *
     * @param trialNum            trial number.
     * @param solutionBits        best solutions bit representation.
     * @param profit              best profit.
     * @param objectiveValuePairs pairs of best and worst objective values from each generation.
     */
    private void trialOutput(int trialNum, String solutionBits, String profit, String[][] objectiveValuePairs) throws IOException {
        String problemName = String.valueOf(config.PROBLEM_INSTANCE);
        problemName = problemName.substring(problemName.lastIndexOf("\\") + 1);
        problemName = problemName.substring(0, problemName.indexOf("."));

        File file = new File("trialOutputs/" + problemName + "_" + trialNum + "_output.txt");
        FileWriter writer = new FileWriter(file, true);
        writer.write("Trial#" + trialNum + ": \n");
        writer.write(profit + "\n");
        writer.write(solutionBits + "\n");
        for (String[] pair : objectiveValuePairs) {
            writer.write(pair[0] + " " + pair[1] + "\n");
        }
        writer.close();
    }

    /**
     * calculates profit and weight using a solutions binary representation. Only used for crossover as delta
     * evaluation was used for other operators.
     *
     * @param solution representation from which profit and weight will be calculated.
     */
    private void calculateProfitAndWeight(String[] solution) {
        for (int i = 0; i < solution[2].length(); i++) {
            int c = Integer.parseInt(String.valueOf(solution[2].charAt(i)));
            if (c == 1) {
                solution[0] = String.valueOf(Double.parseDouble(solution[0]) + items[i][0]);
                solution[1] = String.valueOf(Double.parseDouble(solution[1]) + items[i][1]);
            }
        }
    }

    /**
     * calculates delta difference between new solution and old solution.
     *
     * @param deltaEval previous delta values
     * @param c1 first solution to be delta evaluated
     * @param c2 second solution to be delta evaluated
     */
    private void deltaEvaluation(double[] deltaEval, String[] c1, String[] c2) {
        deltaEval[0] = Double.parseDouble(c1[0]) - deltaEval[0];
        deltaEval[1] = Double.parseDouble(c2[0]) - deltaEval[1];
    }
}