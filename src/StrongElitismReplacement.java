public class StrongElitismReplacement {
    double capacity;

    public StrongElitismReplacement(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Uses sortPopulation() to sort the population in order of most profitable to least profitable,
     * then adds these sorted solutions to the solutions array in the same sorted order.
     *
     * @param population the whole population.
     * @param c1 child one.
     * @param c2 child two.
     * @return sorted solutions.
     */
    String[][] performReplacement(String[][] population, String[] c1, String[] c2) {
        String[][] best = population;
        if (Double.parseDouble(c1[1]) <= capacity && Double.parseDouble(c2[1]) <= capacity) {
            best = new String[population.length + 2][3];
            System.arraycopy(population, 0, best, 0, population.length);
            best[best.length - 1] = c1;
            best[best.length - 2] = c2;
        }
        else if (Double.parseDouble(c1[1]) <= capacity && Double.parseDouble(c2[1]) > capacity) {
            best = new String[population.length + 1][3];
            System.arraycopy(population, 0, best, 0, population.length);
            best[best.length - 1] = c1;
        }
        else if (Double.parseDouble(c1[1]) > capacity && Double.parseDouble(c2[1]) <= capacity) {
            best = new String[population.length + 1][3];
            System.arraycopy(population, 0, best, 0, population.length);
            best[best.length - 1] = c2;
        }

        sortPopulation(best);
        String[][] solutions = new String[population.length][2];
        for (int i = 0; i < population.length; i++) {
            solutions[i] = best[best.length - i - 1];
        }
        return solutions;
    }

    /**
     * Sorts population in ascending order. Every time an element in population is moved, the same
     * is done in populationBits as their indexes correspond to the same solution.
     *
     * @param population the whole population.
     */
    private void sortPopulation(String[][] population) {
        String[] temp;
        for (int i = 0; i < population.length; i++) {
            for (int h = i + 1; h < population.length; h++) {
                if (Double.parseDouble(population[i][0]) > Double.parseDouble(population[h][0])) {
                    temp = population[i];
                    population[i] = population[h];
                    population[h] = temp;
                }
            }
        }
    }
}
