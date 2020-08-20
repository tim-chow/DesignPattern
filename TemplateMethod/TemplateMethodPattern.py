# coding: utf8

import abc


class AbstractClass(object):
    __metaclass__ = abc.ABCMeta

    def template_method(self):
        """
        模版方法
        """
        self.defaultStep()
        self.step1()
        self.step2()

    def defaultStep(self):
        print("defaultStep in AbstractClass")

    @abc.abstractmethod
    def step1(self):
        pass

    @abc.abstractmethod
    def step2(self):
        pass


class ConcreteClass1(AbstractClass):
    def step1(self):
        print("step1 in ConcreteClass1")

    def step2(self):
        print("step2 in ConcreteClass1")

    def defaultStep(self):
        print("invoke defaultStep of AbstractClass")
        AbstractClass.defaultStep(self)
        print("defaultStep in ConcreteClass1")


class ConcreteClass2(AbstractClass):
    def step1(self):
        print("step1 in ConcreteClass2")

    def step2(self):
        print("step2 in ConcreteClass2")


if __name__ == "__main__":
    concrete_class_1 = ConcreteClass1()
    concrete_class_2 = ConcreteClass2()
    concrete_class_1.template_method()
    concrete_class_2.template_method()
