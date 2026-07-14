package weighted;

import xio.selectors.AliasMethod;
import xio.selectors.RouletteMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Fruits> fruits = new ArrayList<>();
        fruits.add(Fruits.DRAGONFRUIT);
        fruits.add(Fruits.PEAR);
        fruits.add(Fruits.AVOCADO);
        fruits.add(Fruits.APPLE);
        fruits.add(Fruits.BORANGO);
        fruits.add(Fruits.JACA);

        aliasMethodSimulation(fruits,100000);
        rouletteSimulation(fruits,100000);
    }

    private static void aliasMethodSimulation(List<Fruits> fruits, int attempts){
        System.out.println("--------ALIAS---------");
        WeightedSelection selection = new WeightedSelection(fruits);
        Map<String, Integer> distribution = new HashMap<>();
        for (Fruits fruit : fruits) {
            System.out.println(fruit.name() + "'s weight ( " + fruit.getWeight() + " )");
            System.out.println("-----------------");
        }
        System.out.println("Total weight: " + selection.getTotalWeight());

        for (int i = 0; i < attempts; i++) {
            AliasMethod aliasedSelection = new AliasMethod(selection.getNormalizedWeights());
            Fruits fruit = fruits.get(aliasedSelection.pickOne());
            if (!distribution.containsKey(fruit.name())){
                distribution.put(fruit.name(),1);
                continue;
            }
            distribution.replace(fruit.name(),distribution.get(fruit.name())+1);
        }

        distribution.forEach(
                (fruit, rolls) -> {
                    System.out.println(fruit+" rolled: " + rolls + " times.");
                }
        );
    }

    private static void rouletteSimulation(List<Fruits> fruits, int attempts){
        System.out.println("--------ROULETTE---------");
        WeightedSelection selection = new WeightedSelection(fruits);
        Map<String, Integer> distribution = new HashMap<>();
        for (Fruits fruit : fruits) {
            System.out.println(fruit.name() + "'s weight ( " + fruit.getWeight() + " )");
            System.out.println("-----------------");
        }
        System.out.println("Total weight: " + selection.getTotalWeight());


        for (int i = 0; i < attempts; i++) {
            int fruitIndex = RouletteMethod.pickOne(selection.getNormalizedWeights());
            Fruits fruit = fruits.get(fruitIndex);

            if (!distribution.containsKey(fruit.name())){
                distribution.put(fruit.name(),1);
                continue;
            }
            distribution.replace(fruit.name(),distribution.get(fruit.name())+1);
        }

        distribution.forEach(
                (fruit, rolls) -> {
                    System.out.println(fruit+" rolled: " + rolls + " times.");
                }
        );
    }
}
