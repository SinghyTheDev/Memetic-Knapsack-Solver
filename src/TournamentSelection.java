import java.util.Random;

public class TournamentSelection {

    int populationSize;

    public TournamentSelection(int populationSize) {
        this.populationSize = populationSize;
    }

    /**
     * tournament selection operator is performed on solutions. Tour size is half the number of total solutions,
     * rounded upwards (for instance 3 solutions would have tour size of 2).
     *
     * @param solutions 2d array of current solutions profit and weight.
     * @return tournament selected solution is returned.
     */
    public String applyTournamentSelection(String[][] solutions) {
        int tourSize = (int) Math.ceil((double) solutions.length / 2);
        Random r = new Random();
        int random = r.nextInt(solutions.length);
        String[] selection = solutions[random];

        for (int i = 0; i < tourSize - 1; i++) {
            int rand = r.nextInt(solutions.length);
            if (Double.parseDouble(selection[0]) < Double.parseDouble(solutions[rand][0])) {
                selection = solutions[rand];
            }
        }
        return selection[2];
    }
}
