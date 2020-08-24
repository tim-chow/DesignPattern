# coding: utf8

from abc import ABCMeta, abstractmethod


class Iterator(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def has_next(self):
        pass

    @abstractmethod
    def next(self):
        pass

    @abstractmethod
    def remove(self):
        pass

    @abstractmethod
    def rewind(self):
        pass


class ListIterator(Iterator):
    def __init__(self, my_list):
        self._my_list = my_list
        self._cursor = 0

    def has_next(self):
        return self._cursor < self._my_list.size()

    def next(self):
        self._cursor = self._cursor + 1
        return self._my_list.get(self._cursor - 1)

    def remove(self):
        self._cursor = self._cursor - 1
        return self._my_list.remove(self._cursor)

    def rewind(self):
        self._cursor = 0


class MyList(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def add(self, element):
        pass

    @abstractmethod
    def get(self, index):
        pass

    @abstractmethod
    def remove(self, index):
        pass

    @abstractmethod
    def size(self):
        pass

    @abstractmethod
    def iterator(self):
        pass


class MyListImpl(MyList):
    def __init__(self):
        self._inner_list = []

    def add(self, element):
        self._inner_list.append(element)

    def get(self, index):
        return self._inner_list[index]

    def remove(self, index):
        return self._inner_list.pop(index)

    def size(self):
        return len(self._inner_list)

    def iterator(self):
        return ListIterator(self)


if __name__ == "__main__":
    my_list = MyListImpl()
    for element in range(10):
        my_list.add(element)
    iterator = my_list.iterator()

    while iterator.has_next():
        print(iterator.next())
        iterator.remove()

    assert my_list.size() == 0
