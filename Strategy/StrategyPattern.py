from abc import ABCMeta, abstractmethod


class Strategy(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def execute_algorithm(self):
        pass


class ConcreteStrategy1(Strategy):
    def execute_algorithm(self):
        print("ConcreteStrategy1")


class ConcreteStrategy2(Strategy):
    def execute_algorithm(self):
        print("ConcreteStrategy2")


class Context(object):
    def __init__(self, strategy):
        self._strategy = strategy

    def set_strategy(self, strategy):
        self._strategy = strategy

    def execute(self):
        self._strategy.execute_algorithm()


if __name__ == "__main__":
    strategy1 = ConcreteStrategy1()
    strategy2 = ConcreteStrategy2()

    context = Context(strategy1)
    context.execute()

    context.set_strategy(strategy2)
    context.execute()
