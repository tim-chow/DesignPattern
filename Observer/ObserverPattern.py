# coding: utf8

import abc


class Subject(object):
    """抽象主题"""
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def add_observer(self, observer):
        pass

    @abc.abstractmethod
    def remove_observer(self, observer):
        pass

    @abc.abstractmethod
    def notify(self):
        pass


class ConcreteSubject(Subject):
    """具体主题"""
    def __init__(self):
        self._observers = set()
        self._state = None

    def add_observer(self, observer):
        self._observers.add(observer)

    def remove_observer(self, observer):
        self._observers.remove(observer)

    def notify(self):
        for observer in self._observers:
            observer.update(self._state)

    def set_state(self, state):
        self._state = state
        self.notify()


class Observer(object):
    """抽象观察者"""
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def update(self, state):
        pass


class ConcreteObserver1(Observer):
    """具体观察者"""
    def update(self, state):
        print("update state to %s in ConcreteObserver1" % state)


class ConcreteObserver2(Observer):
    """具体观察者"""
    def update(self, state):
        print("update state to %s in ConcreteObserver2" % state)


if __name__ == "__main__":
    import unittest


    class ObserverTest(unittest.TestCase):
        def testObserver(self):
            # 创建主题对象
            subject = ConcreteSubject()

            # 创建观察者对象
            observer1 = ConcreteObserver1()
            observer2 = ConcreteObserver2()

            # 添加观察者
            subject.add_observer(observer1)
            subject.add_observer(observer2)

            # 改变主题状态
            subject.set_state(1)
            subject.set_state(2)

            # 移除观察者
            subject.remove_observer(observer1)
            subject.remove_observer(observer2)

            # 改变主题状态
            subject.set_state(3)

    unittest.main()
