import java.util.Set;
import java.util.HashSet;

abstract class Subject {
    protected Set<Observer> observers = new HashSet<>();
    
    public void addObserver(Observer observer) {
        if (observer != null)
            observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        if (observer != null)
            observers.remove(observer);
    }
}

class ConcreteSubject extends Subject {
    private String state;

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    public void notifyObservers() {
        for (Observer observer: observers)
            observer.update(state);
    }
}

interface Observer {
    public void update(String state);
}

class ConcreteObserver implements Observer {
    public void update(String state) {
        System.out.println("current state: " + state);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer = new ConcreteObserver();
        subject.addObserver(observer);
        
        subject.setState("on");
        subject.setState("off");
        subject.removeObserver(observer);
        subject.setState("on");
    }
}

