# coding: utf8

import abc


class Component(object):
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def process(self):
        pass


class ConcreteComponent(Component):
    def process(self):
        print("process in ConcreteComponent")


class Decorator(Component):
    def __init__(self, component):
        self._component = component

    def process(self):
        self.before()
        self._component.process()
        self.after()

    @abc.abstractmethod
    def before(self):
        pass

    @abc.abstractmethod
    def after(self):
        pass


class LogDecorator(Decorator):
    def before(self):
        print("before calling process")

    def after(self):
        print("after calling process")


if __name__ == "__main__":
    import unittest


    class DecoratorTest(unittest.TestCase):
        def testDecorator(self):
            component = ConcreteComponent()
            decorator = LogDecorator(component)
            decorator.process()

    unittest.main()
