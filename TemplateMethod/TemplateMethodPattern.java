abstract class AbstractClass {
    public void defaultStep() {
        System.out.println("defaultStep in AbstractClass");
    }

    public abstract void step1();
    public abstract void step2();

    public final void templateMethod() {
        defaultStep();
        step1();
        step2();
    }
}

class ConcreteClass1 extends AbstractClass {
    public void defaultStep() {
        System.out.println("defaultStep in ConcreteClass1");
    }

    public void step1() {
        System.out.println("step1 in ConcreteClass1");
    }

    public void step2() {
        System.out.println("step2 in ConcreteClass1");
    }
}

class ConcreteClass2 extends AbstractClass {
    public void step1() {
        System.out.println("step1 in ConcreteClass2");
    }

    public void step2() {
        System.out.println("step2 in ConcreteClass2");
    }
}


public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass template = new ConcreteClass1();
        template.templateMethod();
        System.out.println("=========");
        template = new ConcreteClass2();
        template.templateMethod();
    }
}

