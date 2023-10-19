import java.util.Random;

public class Crossover {

    /**
     * Applies one point crossover from two solutions.
     *
     * @param left  first solution to be combined.
     * @param right second solution to be combined.
     * @param intersection point of split.
     * @return combined solution
     */
    public String crossoverBits(String left, String right, int intersection) {
        Random r = new Random();
        if (r.nextDouble() > 0.5) {
            return left.substring(0, intersection) + right.substring(intersection);
        } else {
            return left;
        }
    }
}