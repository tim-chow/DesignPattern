interface Target {
    public void targetMethod();
}

class Adaptee {
    public void specMethod() {
        System.out.println("specMethod in Adaptee");
    }
}

class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void targetMethod() {
        adaptee.specMethod();
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        adapter.targetMethod();
    }
}

