package weightselector.selectors;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
//https://www.keithschwarz.com/darts-dice-coins/

/* Important notes regarding this implementation of the Vose's algo.:
 There are two sources of inaccuracy that we need to deal with before moving on:

    1.The computation to determine whether or not a probability belongs in the Small or Large worklist
    may be inaccurate. Specifically, it may be possible that scaling up the probabilities by a factor of
    n has caused probabilities equal to 1n to end up being slightly less than 1 (thus ending up in the Small
    list rather than the Large list).
    2.The computation that subtracts the appropriate probability mass from a larger probability is not
    numerically stable and may introduce significant rounding errors. This may end up putting a probability
    that should be in the Large list into the Small list instead.

    The combination of these two factors means that we may end up with the algorithm accidentally putting all of
    the probabilities into the Small worklist instead of the Large worklist. As a result, the algorithm may end up
    failing because it expects the Large worklist to be nonempty when the Small worklist is nonempty.

 We also need to reiterate the 3 invariants for this algorithm:
    1. The elements of the "small" worklist are all less than 1.
    2. The elements of the "large" worklist are all at least 1.
    3. The sum of the elements in the worklists is always equal to the total number of elements.
*/

public class AliasMethod {

    private final Random rng;
    private final double[] prob;
    private final int[] alias;

    public AliasMethod(double[] probabilities){
        this(probabilities, new Random());
    }
    public AliasMethod(double[] probabilities, Random rng){
        if (probabilities == null || rng == null){
            throw new NullPointerException();
        }
        if (probabilities.length == 0){
            throw new IllegalArgumentException("Probability array must not be empty");
        }
        final int setSize = probabilities.length;

        /*
        Creating the prob-alias table. The 'prob' list will typically contain the lesser-than-one probability (could be the sliced remainder or a smaller (<1)
        prob from the get-go) matching their initial position on the 'probability' list, that doesnt need to be sorted. The 'alias' will contain the matching
        slice of another probability that fills the original gap. After all the slicing and aliasing, the remaining probabilities should be 1 and wont have aliases
        since the "coin flip" for those indexes always land on them.
        For the other indexes, if the coin toss doesnt land on the 'prob' range, the chosen index is the alias for that index.
        */
        this.prob = new double[setSize];
        this.alias = new int[setSize];
        this.rng = rng;
        final double avg = 1.0 / setSize; //Average prob, used to normalize/scale the rest of them and serve as the 'middle line' for small-large checks

        /*
        This steps are specific to Vose's implementation of the method. It uses two worklists to separate and stack small (<1) probabilities and large (>=1) prob.
        This approach can use different data structures for handling these worklists. This implementation will use Deques (but can be done with arrays or stacks,
        for instance). Also, these worklist will only store the pointer/reference to the original probability
        */
        Deque<Integer> small = new ArrayDeque<>();
        Deque<Integer> large = new ArrayDeque<>();

        //Populating the worklists (probabilities not normalized yet)
        for (int i = 0; i < setSize; ++i) {
            /* If the probability is below the average probability, then we add
             * it to the small list; otherwise we add it to the large list.
             */
            if (probabilities[i] >= avg)
                large.add(i);
            else
                small.add(i);
        }

        //Once we have the size worklists sorted, lets 'fill' each slot with its probability and alias, exhausting the worklists by pairing smalls and larges
        while (!small.isEmpty() && !large.isEmpty()){
            int currSmallIndex = small.removeLast();
            int currLargeIndex = large.removeLast();
            //The probabilities are not scaled yet, we do that here, individually.
            //Fill the prob array
            prob[currSmallIndex] = probabilities[currSmallIndex] * setSize;
            //Fill the alias array
            alias[currSmallIndex] = currLargeIndex;

            //Decrease the total stored probability of the current "Large" by the appropriate amount
            double newLarge = (probabilities[currLargeIndex] + probabilities[currSmallIndex]) - avg;
            probabilities[currLargeIndex] = newLarge;
            /*
             (L + S) is the combined probability of both. We want the remainder after we alocated (1-S) from L
             So the initial intuition is to have the remainder be L - (1 - S). But ordering like (L + S) - 1 is
             more stable in general. Also, we can also visualize this as: L stacked onto S and removing one (the
             height of the 'slot'). Since we originally haven't scaled the probabilities, all of that is divided by N
            */
            /*
            Now the large probability has been removed from the stack and a new (possibly small) probability was
            created. Lets see where it fits so we can continue coupling them together
            */
            if (newLarge>=avg){
                large.add(currLargeIndex);
            }else{
                small.add(currLargeIndex);
            }
            //Now the correct worklist has a new pointer to this new value, lets continue
        }
        /*
        At this point, either small OR large is exhausted. But, since they can't be coupled together anymore and, if
        the list with any elements left is 'small', it would violate the 3rd invariant (the (normalized) sum of the
        elements in the worklists must be equal to the number of elements -> 3 items = 3 total sum).
        Then we can be sure the sure the relevant list is the 'large' one, since the remaining items should equal to 1

        Mathematically this should be the case, but since there may be floating-point inaccuracies, we need to "convert"
        the 'large' list by forcing every value to exactly 1.0. The same inaccuracies may apply to the 'small' list but
        in another way:
        When 'creating' the new probability to add to the worklists by subtracting the larger prob., the calculation might
        place a 1 slightly below that, putting it in the 'small' worklist. Because of that, we need to "correct" both lists.
        */
        while(!large.isEmpty()){
            prob[large.removeLast()] = 1.0;
        }
        while(!small.isEmpty()){
            prob[small.removeLast()] = 1.0;
        }
        //Now, the initialization is complete!
    }

    public int pickOne(){
        int column = rng.nextInt(prob.length);
        boolean coinTossSuccess = (rng.nextDouble() < prob[column]);
        if (coinTossSuccess){
            return column;
        }
        return alias[column];
    }
}