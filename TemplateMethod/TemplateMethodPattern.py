from abc import ABCMeta, abstractmethod

class AbstractClass:
    __metaclass__ = ABCMeta

    @abstractmethod
    def step1(self):
        pass

    @abstractmethod
    def step2(self):
        pass

    def default_step(self):
        print("default_step in AbstractClass")

    def template_method(self):
        self.default_step()
        self.step1()
        self.step2()

class ConcreteClass1(AbstractClass):
    def step1(self):
        print("step1 in ConcreteClass1")

    def step2(self):
        print("step2 in ConcreteClass1")

class ConcreteClass2(AbstractClass):
    def step1(self):
        print("step1 in ConcreteClass2")

    def step2(self):
        print("step2 in ConcreteClass2")

if __name__ == "__main__":
    template = ConcreteClass1()
    template.template_method()
    print("==========")
    template = ConcreteClass1()
    template.template_method()

