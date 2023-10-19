import java.util.Random;

public class RandomMutation {

    /**
     * Applies random mutation to a solution.
     *
     * @param solution solution which will be mutated.
     * @param items    2d array for every possible item with its weight and profit. This is passed to the function so
     *                 that it knows what to minus/add to solutionsProfitWeight when a mutation occurs.
     */

    public void performRandomMutation(String[] solution, double[][] items, double IOM) {
        StringBuilder bits = new StringBuilder(solution[2]);
        Random r = new Random();
        for (int i = 0; i < noOfOperations(IOM); i++) {
            for (int h = 0; h < solution[2].length(); h++) {
                if (r.nextDouble() < 1.0 / solution[2].length()) {
                    int c = Integer.parseInt(String.valueOf(bits.charAt(h)));
                    if (c == 1) {
                        bits.setCharAt(h, '0');
                        solution[0] = String.valueOf(Double.parseDouble(solution[0]) - items[h][0]);
                        solution[1] = String.valueOf(Double.parseDouble(solution[1]) - items[h][1]);
                    } else if (c == 0) {
                        bits.setCharAt(h, '1');
                        solution[0] = String.valueOf(Double.parseDouble(solution[0]) + items[h][0]);
                        solution[1] = String.valueOf(Double.parseDouble(solution[1]) + items[h][1]);
                    }
                }
            }
        }
        solution[2] = String.valueOf(bits);
    }

    /**
     * Calculates how many times random mutation will be invoked.
     *
     * @param IOM how intense the mutation will be.
     * @return number of times random mutation will be invoked.
     */

    private static int noOfOperations(double IOM) {
        if (IOM >= 0.0 && IOM < 0.2) {
            return 1;
        } else if (IOM >= 0.2 && IOM < 0.4) {
            return 2;
        } else if (IOM >= 0.4 && IOM < 0.6) {
            return 3;
        } else if (IOM >= 0.6 && IOM < 0.8) {
            return 4;
        } else if (IOM >= 0.8 && IOM < 1.0) {
            return 5;
        } else {
            return 6;
        }
    }
}