import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Initialisation {
    int capacity;
    double[][] items;
    private final int totalItems;
    private final String[] solutionBitRepresentations;
    private final double[][] solutions;

    public Initialisation(Scanner contents, int noOfRandomSolutions, int noOfGreedySolutions) {
        this.totalItems = Integer.parseInt(contents.next());
        this.capacity = Integer.parseInt(contents.next());
        this.items = new double[totalItems][2];
        this.solutions = new double[noOfRandomSolutions + noOfGreedySolutions][2];
        this.solutionBitRepresentations = new String[solutions.length];
        readFile(contents);
        initialiseEmptyRepresentations();
        initialiseRandomSolutions(noOfRandomSolutions);
        initialiseGreedySolutions(noOfGreedySolutions);
    }

    /**
     * Reads instance problem files and stores these in a 2d array in format {{profit,weight}...}
     *
     * @param contents contents of file not including first two strings n and W.
     */
    private void readFile(Scanner contents) {
        String itemAttribute;
        items = new double[totalItems][2];
        for (int i = 0; i < totalItems; i++) {
            for (int j = 0; j < 2; j++) {
                itemAttribute = contents.next();
                items[i][j] = Double.parseDouble(itemAttribute);
            }
        }
    }

    /**
     * Initialises random feasible solutions and stores these in an array.
     *
     * @param numOfSolutions how many random solutions will be used
     */
    private void initialiseRandomSolutions(int numOfSolutions) {
        boolean feasible;
        for (int i = 0; i < numOfSolutions; i++) {
            Random r = new Random();
            feasible = false;
            while (!feasible) {
                for (int j = 0; j < totalItems; j++) {
                    double random = r.nextDouble();
                    if (random < 0.5) {
                        solutions[i][0] += items[j][0];
                        solutions[i][1] += items[j][1];
                        solutionBitRepresentations[i] = solutionBitRepresentations[i] + 1;
                    } else {
                        solutionBitRepresentations[i] = solutionBitRepresentations[i] + 0;
                    }
                }

                if (solutions[i][1] <= capacity) {
                    feasible = true;
                } else {
                    solutions[i][0] = 0;
                    solutions[i][1] = 0;
                    solutionBitRepresentations[i] = "";
                }
            }
        }
    }

    /**
     * Initialises feasible greedy heuristic #3 solutions (profit per unit weight) and stores these in an array.
     *
     * @param numOfSolutions how many random solutions will be used
     */
    private void initialiseGreedySolutions(int numOfSolutions) {
        double[][] sortedItems;
        sortedItems = Arrays.copyOf(items, items.length);
        for (int i = 1; i < totalItems; i++) {
            double tempProfitPerWeight = sortedItems[i][0] / sortedItems[i][1];
            double[] tempItems = sortedItems[i];
            int j = i - 1;
            while (j >= 0 && tempProfitPerWeight > sortedItems[j][0] / sortedItems[j][1]) {
                sortedItems[(j + 1)] = sortedItems[j];
                j--;
            }
            sortedItems[(j + 1)] = tempItems;
        }

        int startingIndex = solutions.length - numOfSolutions;
        for (int i = startingIndex; i < solutions.length; i++) {
            for (int j = 0; j < totalItems; j++) {
                if (solutions[i][1] + items[j][1] > capacity) {
                    solutionBitRepresentations[i] = solutionBitRepresentations[i] + 0;
                } else if (solutions[i][1] + items[j][1] <= capacity) {
                    solutions[i][0] += items[j][0];
                    solutions[i][1] += items[j][1];
                    solutionBitRepresentations[i] = solutionBitRepresentations[i] + 1;
                }
            }
        }
    }

    /**
     * Enters empty elements into bit representation arrays to avoid null error when concatenating 0/1's.
     */
    private void initialiseEmptyRepresentations() {
        for (int i = 0; i < solutions.length; i++) {
            solutionBitRepresentations[i] = "";
        }
    }

    /**
     * @return returns combined array of initial greedy and random solutions.
     */
    public String[][] getInitialSolutions() {
        String[][] initialSolutions = new String[solutions.length][3];
        for(int i = 0; i < solutions.length; i++){
            initialSolutions[i][0] = String.valueOf(solutions[i][0]);
            initialSolutions[i][1] = String.valueOf(solutions[i][1]);
            initialSolutions[i][2] = solutionBitRepresentations[i];
        }
        return initialSolutions;
    }
}