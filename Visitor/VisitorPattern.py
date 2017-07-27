import datetime
from abc import ABCMeta, abstractmethod

class Visitor:
    @abstractmethod
    def visit_apple_element(self, apple_element):
        pass

    @abstractmethod
    def visit_book_element(self, book_element):
        pass

class Consumer(Visitor):
    def visit_apple_element(self, apple_element):
        if (apple_element.get_expire_date() 
            - datetime.datetime.now() > datetime.timedelta(0)
            and apple_element.is_perfect()):
            return True
        return False

    def visit_book_element(self, book_element):
        return apple_element.is_perfect()

class Cashier(Visitor):
    def visit_apple_element(self, apple_element):
        return apple_element.get_price()

    def visit_book_element(self, book_element):
        return book_element.get_price()

class Element:
    @abstractmethod
    def accept(self, visitor):
        pass

class AppleElement(Element):
    def __init__(self, expire_date, price):
        self._expire_date = expire_date
        self._price = price

    def get_price(self):
        return self._price
    
    def get_expire_date(self):
        return self._expire_date

    def is_perfect(self):
        return True

    def accept(self, visitor):
        return visitor.visit_apple_element(self)
# end class AppleElement

class BookElement(Element):
    def __init__(self, price):
        self._price = price

    def get_price(self):
        return self._price

    def is_perfect(self):
        return True

    def accept(self, visitor):
        return visitor.visit_book_element(self)
# end class BookElement

class Iterator:
    """
remove elements while iterating `sequence`(
    `remove` method must be invocated after `next`)
for example:

```    
this_is_a_list = range(100)
iterator = Iterator(this_is_a_list)

while iterator.has_next():
    element = iterator.next()
    if some_condition(element):
        iterator.remove()
```
    """
    def __init__(self, sequence):
        self._cursor = 0
        self._sequence = sequence

    def has_next(self):
        if self._cursor < len(self._sequence):
            return True
        return False

    def next(self):
        result = self._sequence[self._cursor]
        self._cursor = self._cursor + 1
        return result

    def remove(self):
        self._cursor = self._cursor - 1
        self._sequence.pop(self._cursor)

    def rewind(self):
        self._cursor = 0

class ObjectStructure:
    def __init__(self):
        self._container = []

    def add_element(self, element):
        self._container.append(element)

    def remove_element(self, element):
        self._container.remove(element)

    def iterator(self):
        return Iterator(self._container)

if __name__ == "__main__":
    object_structure = ObjectStructure()
    apple_element = AppleElement(
        datetime.datetime.strptime("2017-5-21 00:00:00",
            "%Y-%m-%d %H:%M:%S"), 1500)
    book_element = BookElement(1000)
    object_structure.add_element(apple_element)
    object_structure.add_element(book_element)

    consumer = Consumer()
    cashier = Cashier()

    iterator = object_structure.iterator()
    while iterator.has_next():
        if not iterator.next().accept(consumer):
            iterator.remove()

    iterator.rewind()
    sum = 0
    while iterator.has_next():
        sum = sum + iterator.next().accept(cashier)
    print("sum = %s" % sum)

