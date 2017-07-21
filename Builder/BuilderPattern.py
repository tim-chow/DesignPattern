from abc import ABCMeta, abstractmethod

## new style class
class Product(object):
    def __init__(self):
        self._part1 = None
        self._part2 = None

    @property
    def part1(self):
        return self._part1

    @part1.setter
    def part1(self, part1):
        self._part1 = part1

    @property
    def part2(self):
        return self._part2

    @part2.setter
    def part2(self, part2):
        self._part2 = part2

    def __str__(self):
        return "Product{part1=%s, part2=%s}" \
             % (self._part1, self._part2)
    __repr__ = __str__
# end class Product

class Builder:
    __metaclass__ = ABCMeta

    @abstractmethod
    def buildPart1(self):
        pass

    @abstractmethod
    def buildPart2(self):
        pass
# end class Builder

class ConcreteBuilder1(Builder):
    def __init__(self):
        self._product = Product()

    def build(self):
        return self._product

    def buildPart1(self):
        self._product.part1 = "Construct by ConcretBuilder1"

    def buildPart2(self):
        self._product.part2 = "Construct by ConcretBuilder1"

class ConcreteBuilder2(Builder):
    def __init__(self):
        self._product = Product()

    def build(self):
        return self._product

    def buildPart1(self):
        self._product.part1 = "Construct by ConcretBuilder2"

    def buildPart2(self):
        self._product.part2 = "Construct by ConcretBuilder2"

class Director:
    def constructProduct(self, builder):
        builder.buildPart2()
        builder.buildPart1()
        return builder.build();

if __name__ == "__main__":
    director = Director()
    builder1 = ConcreteBuilder1()
    builder2 = ConcreteBuilder2()

    print(director.constructProduct(builder2))
    print(director.constructProduct(builder1))

