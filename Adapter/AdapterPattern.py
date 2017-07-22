from abc import ABCMeta, abstractmethod

class Target:
    __metaclass__ = ABCMeta
    
    @abstractmethod
    def target_method(self):
        pass

class Adaptee:
    def spec_method(self):
        print("spec_method in Adaptee")

class Adapter(Target):
    def __init__(self, adaptee):
        self._adaptee = adaptee

    def target_method(self):
        self._adaptee.spec_method()

if __name__ == "__main__":
    adaptee = Adaptee()
    adapter = Adapter(adaptee)
    adapter.target_method()

