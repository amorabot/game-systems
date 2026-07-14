package weighted;

public enum Fruits {
    DRAGONFRUIT(1),
    MANGO(3),
    AVOCADO(5),
    BANANA(20),
    PEAR(10),
    BORANGO(2),
    JACA(50),
    APPLE(15);

    private final int weight;

    Fruits(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
