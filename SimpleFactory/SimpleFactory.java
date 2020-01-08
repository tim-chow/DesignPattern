import java.lang.reflect.Constructor;


abstract class Phone {
    private String branch;
    private String os;

    protected Phone(String branch, String os) {
        this.branch = branch;
        this.os = os;
    }

    public void say() {
        System.out.println("I am " + this.branch 
            + ", my OS is: " + this.os);
    }
}


class IPhone extends Phone {
    public IPhone(String os) {
        super("IPhone", os);
    }
}


class Oneplus extends Phone {
    public Oneplus(String os) {
        super("Oneplus", os);
    }
}


public class SimpleFactory {
    public static Phone create(String className, String os) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> consutructor = 
                clazz.getDeclaredConstructor(String.class);
            Phone phone = (Phone) consutructor.newInstance(os);
            return phone;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SimpleFactory.create("IPhone", "IOS8").say();
        SimpleFactory.create("Oneplus", "Android7").say();
    }
}
