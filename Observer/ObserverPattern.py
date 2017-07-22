class Subject:
    def __init__(self):
        self._observers = set()

    def add_observer(self, observer):
        if observer:
            self._observers.add(observer)
    
    def remove_observer(self, observer):
        if observer:
            self._observers.remove(observer)

class ConcreteSubject(Subject):
    def __init__(self, *a, **kw):
        Subject.__init__(self, *a, **kw)
        self._state = None

    def set_state(self, state):
        self._state = state
        self.notify_observers()

    def notify_observers(self):
        for observer in self._observers:
            observer.update(self._state)

class Observer:
    def update(self, state):
        raise NotImplementedError("not implemented")

class ConcreteObserver(Observer):
    def update(self, state):
        print("current state is: %s" % state)

if __name__ == "__main__":
    subject = ConcreteSubject()
    observer = ConcreteObserver()
    subject.add_observer(observer)

    subject.set_state("on")
    subject.set_state("off")
    subject.remove_observer(observer)
    subject.set_state("on")

