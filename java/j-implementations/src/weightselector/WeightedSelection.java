package weightselector;

import java.util.Arrays;
import java.util.List;

public class WeightedSelection {

    private String[] keys;
    private Integer[] weights;

    public WeightedSelection(List<Fruits> fruitSelection){
        final int elements = fruitSelection.size();
        this.keys = new String[elements];
        this.weights = new Integer[elements];
        for (int i = 0; i < elements; i++) {
            Fruits currFruit = fruitSelection.get(i);
            keys[i] = currFruit.name();
            weights[i] = currFruit.getWeight();
        }
    }

    public int getTotalWeight(){
        int acc = 0;
        for (Integer weight : weights){
            acc+=weight;
        }
        return acc;
    }

    public double[] getNormalizedWeights(){
        final int totalWeight = getTotalWeight();
        return (Arrays.stream(weights.clone()).mapToDouble(
                (currWeight) -> {
                    return ((double)currWeight/totalWeight);
                }
        )).toArray();
    }
}
