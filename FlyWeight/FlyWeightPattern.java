import java.util.*;

interface Fruit {
    int calcAmount(int weight);
}

class FruitImpl implements Fruit {
    private String name;
    private int unitPrice;

    public FruitImpl(String name, int unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public int calcAmount(int weight) {
        return unitPrice * weight;
    }

    public String toString() {
        return "FruitImpl{name=" + name + ", unitPrice=" + unitPrice + "}";
    }
}

class FruitFactory {
    private final static Map<String, Integer> NAME2PRICE;

    static {
        NAME2PRICE = new HashMap<String, Integer>();
        NAME2PRICE.put("apple", 20);
        NAME2PRICE.put("banana", 21);
    }

    private static FruitFactory fruitFactory;
    private Map<String, Fruit> map = new HashMap<>();

    private FruitFactory() {
    }

    public static synchronized FruitFactory getInstance() {
        if (fruitFactory == null)
            fruitFactory = new FruitFactory();
        return fruitFactory;
    }

    public synchronized Fruit factory(String name) {
        if (!map.containsKey(name)) {
            map.put(name, new FruitImpl(name, NAME2PRICE.get(name)));
        }

        return map.get(name);
    }
}

class FlyWeightPattern {
    public static void main(String[] args) {
        FruitFactory fruitFactory = FruitFactory.getInstance();
        Fruit apple = fruitFactory.factory("apple");
        Fruit apple1 = fruitFactory.factory("apple");
        System.out.println(apple == apple1);
        System.out.println(apple);
        Fruit banana = fruitFactory.factory("banana");
        System.out.println(banana.calcAmount(3));
    }
}

