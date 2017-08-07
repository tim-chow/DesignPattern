from abc import ABCMeta, abstractmethod

class Subject:
    __metaclass__ = ABCMeta

    @abstractmethod
    def do_something(self):
        pass

class ConcreteSubject(Subject):
    def do_something(self):
        print("do something")

class Proxy(Subject):
    def __init__(self, target):
        self._target = target

    def do_something(self):
        print("before")
        self._target.do_something()
        print("after")

if __name__ == "__main__":
    proxy = Proxy(ConcreteSubject())
    proxy.do_something()

