interface Strategy {
    public void executeAlgorithm();
}

class ConcreteStrategy1 implements Strategy {
    public void executeAlgorithm() {
        System.out.println("ConcreteStrategy1");
    }
}

class ConcreteStrategy2 implements Strategy {
    public void executeAlgorithm() {
        System.out.println("ConcreteStrategy2");
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) { 
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        strategy.executeAlgorithm();
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        Strategy strategy1 = new ConcreteStrategy1();
        Strategy strategy2 = new ConcreteStrategy2();

        Context context = new Context(strategy1);
        context.execute();

        context.setStrategy(strategy2);
        context.execute();
    }
}

