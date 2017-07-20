class SubSystem1 {
    public void method1() {
        System.out.println("this is method1 of SubSystem1");
    }
}

class SubSystem2 {
    public void method2() {
        System.out.println("this is method2 of SubSystem2");
    }
}

class SubSystem3 {
    public void method3() {
        System.out.println("this is method3 of SubSystem3");
    }
}

class Facade {
    private SubSystem1 subSystem1;
    private SubSystem2 subSystem2;
    private SubSystem3 subSystem3;
    
    private volatile Facade facade;
    private Facade() {
        subSystem1 = new SubSystem1();
        subSystem2 = new SubSystem2();
        subSystem3 = new SubSystem3();
    }

    public synchronized Facade getInstance() {
        if (facade == null)
            facade = new Facade();
        return facade;
    }

    public void method1() {
        subSystem1.method1();
    }

    public void method2() {
        subSystem2.method2();
    }

    public void method3() {
        subSystem3.method3();
    }

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method2();
        facade.method1();
    }
}

