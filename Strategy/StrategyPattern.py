from abc import ABCMeta, abstractmethod

class Strategy:
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

class Context:
    def __init__(self, strategy):
        self._strategy = strategy

    def set_strategy(self, strategy):
        if isinstance(strategy, Strategy):
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

