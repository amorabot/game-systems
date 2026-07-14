package weightselector.selectors;

import java.util.Random;

public class RouletteMethod {


    public static int pickOne(double[] probabilities){
        return pickOne(probabilities,new Random());
    }
    public static int pickOne(double[] probabilities, Random rng){
        if (probabilities == null || rng == null){
            throw new NullPointerException();
        }
        if (probabilities.length == 0){
            throw new IllegalArgumentException("Probability array must not be empty");
        }
        final double roll = rng.nextDouble();
        double cumulativeProb = 0d;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProb += probabilities[i];
            if (cumulativeProb>=roll){
                return i;
            }
        }
        //Invalid roll/probability range
        return -1;
    }
}
