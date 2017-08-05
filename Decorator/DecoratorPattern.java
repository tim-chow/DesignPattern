interface Component {
    void operation();
}

class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("operation in ConcreteComponent");
    }
}

abstract class LogDecrator {
    private Component component;

    public LogDecrator(Component component) {
        if (component == null) 
            throw new RuntimeException("component == null");
        this.component = component;
    }

    public void operation() {
        beforeOperation();
        component.operation();
        afterOperation();
    }

    public abstract void beforeOperation();
    public abstract void afterOperation();
}

class ConreteLogDecorator extends LogDecrator {
    public ConreteLogDecorator(Component component) {
        super(component);
    }

    public void beforeOperation() {
        System.out.println("before operation");
    }

    public void afterOperation() {
        System.out.println("after operation");
    }
}

class DecoratorPattern {
    public static void main(String[] args) {
        Component component =  new ConcreteComponent();
        LogDecrator logDecorator = new ConreteLogDecorator(component);
        logDecorator.operation();
    }
}

