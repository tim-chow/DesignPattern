from abc import ABCMeta, abstractmethod
import threading

class Fruit:
    __metaclass__ = ABCMeta

    @abstractmethod
    def calc_amount(self, weight):
        pass

class FruitImpl(Fruit):
    def __init__(self, name, unit_price):
        self._name = name
        self._unit_price = unit_price

    def calc_amount(self, weight):
        return weight * self._unit_price

class FruitFactory:
    lock = threading.Lock()
    instance = None
    NAME_2_PRICE = {
        "banana": 20,
        "apple": 21,
    }

    def __init__(self):
        self._lock = threading.Lock()
        self._map = {}

    @classmethod
    def get_instance(cls):
        with cls.lock:
            if not cls.instance:
                cls.instance = FruitFactory()
            return cls.instance

    def factory(self, name):
        price = self.__class__.NAME_2_PRICE.get(name)
        if not price:
            raise RuntimeError("invalid name")

        with self._lock:
            if name not in self._map:
                self._map[name] = FruitImpl(name, price)
            return self._map[name]

if __name__ == "__main__":
    factory = FruitFactory.get_instance()
    apple = factory.factory("apple")
    apple1 = factory.factory("apple")
    banana = factory.factory("banana")
    print apple == apple1
    nothing = factory.factory("nothing")

