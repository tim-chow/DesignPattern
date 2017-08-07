interface Subject {
    void doSomething();
}

class ConcreteSubject implements Subject {
    public void doSomething() {
        System.out.println("do something");
    }
}

class Proxy implements Subject {
    private Subject target;

    public Proxy(Subject target) {
        this.target = target;
    }

    public void doSomething() {
        System.out.println("before");
        target.doSomething();
        System.out.println("after");
    }
}

class StaticProxy {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new ConcreteSubject());
        proxy.doSomething();
    }
}

