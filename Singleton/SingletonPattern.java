class Singleton {
    private static Singleton singleton;

    public synchronized static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    // other methods here
}

public class SingletonPattern {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == 
            Singleton.getInstance());
    }
}
