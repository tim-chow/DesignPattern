# coding: utf8

import abc


class Product(object):
    """产品类"""
    def __init__(self):
        self._part_a = None
        self._part_b = None

    def get_part_a(self):
        return self._part_a

    def get_part_b(self):
        return self._part_b

    def set_part_a(self, part_a):
        self._part_a = part_a

    def set_part_b(self, part_b):
        self._part_b = part_b


class Builder(object):
    """抽象建造者"""
    __metaclass__ = abc.ABCMeta

    def __init__(self):
        self._product = Product()

    @abc.abstractmethod
    def build_part_a(self):
        pass

    @abc.abstractmethod
    def build_part_b(self):
        pass

    def build(self):
        return self._product


class ConcreteBuilder1(Builder):
    def build_part_a(self):
        self._product.set_part_a("ConcreteBuilder1 part a")

    def build_part_b(self):
        self._product.set_part_b("ConcreteBuilder1 part b")


class ConcreteBuilder2(Builder):
    def build_part_a(self):
        self._product.set_part_a("ConcreteBuilder2 part a")

    def build_part_b(self):
        self._product.set_part_b("ConcreteBuilder2 part b")


class Director(object):
    """指挥者"""
    def create_product(self, builder):
        builder.build_part_a()
        builder.build_part_b()
        return builder.build()


def test():
    director = Director()

    concrete_builder_1 = ConcreteBuilder1()
    concrete_builder_2 = ConcreteBuilder2()

    product_1 = director.create_product(concrete_builder_1)
    product_2 = director.create_product(concrete_builder_2)

    print(product_1.get_part_a())
    print(product_1.get_part_b())

    print(product_2.get_part_a())
    print(product_2.get_part_b())


if __name__ == "__main__":
    test()
