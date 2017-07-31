interface Car {
    void say();
}

abstract class AudiCar implements Car {
}

class AudiA4 extends AudiCar {
    public void say() {
        System.out.println("I am Audi A4");
    }
}

class AudiQ5 extends AudiCar {
    public void say() {
        System.out.println("I am Audi Q5");
    }
}

abstract class BMWCar implements Car {
}

class BMWX1 extends BMWCar {
    public void say() {
        System.out.println("I am BMW X1");
    }
}

class BMW3X extends BMWCar {
    public void say() {
        System.out.println("I am BMW 3X");
    }
}

abstract class CarFactory {
    public AudiCar createAudi() {
        return createAudiCar();
    }
    public AudiCar createAudiCar() {
        return null;
    }

    public BMWCar createBMW() {
        return createBMWCar();
    }
    public BMWCar createBMWCar() {
        return null;
    }
}

class AudiA4Factory extends CarFactory {
    public AudiCar createAudiCar() {
        return new AudiA4();
    }
}

class AudiQ5Factory extends CarFactory {
    public AudiCar createAudiCar() {
        return new AudiQ5();
    }
}

class BMWX1Factory extends CarFactory {
    public BMWCar createBMWCar() {
        return new BMWX1();
    }
}

class BMW3XFactory extends CarFactory {
    public BMWCar createBMWCar() {
        return new BMW3X();
    }
}


public class AbstractFactory {
    public static void main(String[] args) {
        AudiCar a4 = new AudiA4Factory().createAudi();
        AudiCar q5 = new AudiQ5Factory().createAudi();
        BMWCar x1 = new BMWX1Factory().createBMW();
        BMWCar _3x = new BMW3XFactory().createBMW();

        a4.say();
        q5.say();
        x1.say();
        _3x.say();
    }
}

