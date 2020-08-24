# coding: utf8

import abc


class Visitor(object):
    """
    抽象访问者
    """
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def visit_fruit(self, fruit):
        pass

    @abc.abstractmethod
    def visit_book(self, book):
        pass


class Cashier(Visitor):
    """
    具体访问者 - 收银员
    """
    def visit_book(self, book):
        return book.get_price()

    def visit_fruit(self, fruit):
        return fruit.get_price()


class Customer(Visitor):
    """
    具体访问者 - 顾客
    """
    def visit_book(self, book):
        return book.is_in_good_condition()

    def visit_fruit(self, fruit):
        return fruit.is_fresh()


class Goods(object):
    """抽象元素 - 商品"""
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def accept(self, visitor):
        pass

    @abc.abstractmethod
    def get_price(self):
        pass


class Fruit(Goods):
    """具体元素 - 水果"""
    def __init__(self, price, is_fresh):
        self._price = price
        self._is_fresh = is_fresh

    def accept(self, visitor):
        return visitor.visit_fruit(self)

    def is_fresh(self):
        return self._is_fresh

    def get_price(self):
        return self._price

    def __str__(self):
        return "Fruit{price=%f, fresh=%s}" % (
            self._price, self._is_fresh)


class Book(Goods):
    """
    具体元素 - 图书
    """
    def __init__(self, price, is_in_good_condition):
        self._price = price
        self._is_in_good_condition = is_in_good_condition

    def is_in_good_condition(self):
        return self._is_in_good_condition

    def accept(self, visitor):
        return visitor.visit_book(self)

    def get_price(self):
        return self._price

    def __str__(self):
        return "Book{price=%f, condition=%s}" % (
            self._price, self._is_in_good_condition)


class ObjectStructure(object):
    def __init__(self, array):
        self._array = array
        self._cursor = 0

    def has_next(self):
        return self._cursor < len(self._array)

    def next(self):
        try:
            return self._array[self._cursor]
        finally:
            self._cursor = self._cursor + 1

    def remove(self):
        self._cursor = self._cursor - 1
        self._array.pop(self._cursor)

    def rewind(self):
        self._cursor = 0


def test():
    # 商品列表
    array = list()
    array.append(Book(1, True))
    array.append(Book(2, True))
    array.append(Book(3, False))
    array.append(Fruit(1, True))
    array.append(Fruit(2, False))
    array.append(Fruit(3, True))

    object_structure = ObjectStructure(array)
    customer = Customer()
    cashier = Cashier()

    # 顾客逐个检查商品
    while object_structure.has_next():
        goods = object_structure.next()
        state = goods.accept(customer)
        # 移除不好的商品
        if not state:
            object_structure.remove()

    # 保证收银员从头开始访问
    object_structure.rewind()

    # 收银员逐个计算价格
    prices = 0
    while object_structure.has_next():
        goods = object_structure.next()
        prices = prices + goods.accept(cashier)

    print("total prices %f" % prices)


if __name__ == "__main__":
    test()
