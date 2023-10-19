public class DavisBitHillClimbing {

    /**
     * Applies feasible DBHC to a solution.
     *
     * @param solution     the solution which DBHC will be applied to.
     * @param items        all possible items and their profit/weight so that the method knows what to add/minus when
     *                     a bit is flipped.
     * @param capacity     capacity of knapsack so that method knows when to stop before infeasibility.
     */

    void performDBHC(String[] solution, double[][] items, double capacity, double DOS) {
        String[] permutation = new String[solution[2].length()];
        for (int i = 0; i < solution[2].length(); i++) {
            permutation[i] = String.valueOf(i);
        }
        for (int i = 1; i < solution[2].length(); i++) {
            int h = (int) (Math.random() * (i + 1));
            String temp = permutation[i];
            permutation[i] = permutation[h];
            permutation[h] = temp;
        }
        for (int i = 0; i < noOfOperations(DOS); i++) {
            double previousProfit = Double.parseDouble(solution[0]);
            double newProfit = Double.parseDouble(solution[0]);
            double newWeight = Double.parseDouble(solution[1]);
            String newBits = solution[2];
            for (int h = 0; h < solution[2].length(); h++) {
                StringBuilder bits = new StringBuilder(newBits);
                int c = Integer.parseInt(String.valueOf(bits.charAt(h)));
                if (c == 0) {
                    if (newWeight + items[h][1] <= capacity) {
                        bits.setCharAt(h, '1');
                        newProfit += items[h][0];
                        newWeight += items[h][1];
                        newBits = String.valueOf(bits);
                    }
                }
            }
            if (previousProfit > Double.parseDouble(solution[0])) {
                break;
            }
            solution[0] = String.valueOf(newProfit);
            solution[1] = String.valueOf(newWeight);
            solution[2] = newBits;
        }
    }

    /**
     * Calculates how many times random mutation will be invoked.
     *
     * @param DOS how much depth the search will have.
     * @return number of complete passes that will be invoked on a solution.
     */
    private int noOfOperations(double DOS) {
        if (DOS >= 0.0 && DOS < 0.2) {
            return 1;
        } else if (DOS >= 0.2 && DOS < 0.4) {
            return 2;
        } else if (DOS >= 0.4 && DOS < 0.6) {
            return 3;
        } else if (DOS >= 0.6 && DOS < 0.8) {
            return 4;
        } else if (DOS >= 0.8 && DOS < 1.0) {
            return 5;
        } else {
            return 6;
        }
    }
}
