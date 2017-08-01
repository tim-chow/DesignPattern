# coding: utf8

from abc import ABCMeta, abstractmethod

class Iterator:
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

# 外禀迭代子模式 开始

class ListIterator(Iterator):
    def __init__(self, my_list):
        if not isinstance(my_list, MyList):
            raise RuntimeError("my_list must be"
                " a instance of MyList")
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

class MyList:
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

    def __str__(self):
        return str(self._inner_list)

    def __repr__(self):
        return repr(self._inner_list)
# 外禀迭代子模式 结束

# 内禀迭代子模式 开始

class MyList2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def add(self, element):
        pass

    @abstractmethod
    def iterator(self):
        pass

"""
java中的内部类分为：成员内部类、静态内部类、局部内部类、匿名内部类

python其实没有内部类的概念，当然类的定义可以出现在其他类的内部，
    也可以出现在函数或方法的内部
    如果非要称之为内部类的话，那么可以理解为：python支持静态内部类
        和局部内部类，以及匿名内部类。但是不支持 所谓的 成员内部类
"""

# 内禀迭代子模式 结束

if __name__ == "__main__":
    list = MyListImpl()
    list.add(1); list.add(2); list.add(3)
    iterator = list.iterator()

    while iterator.has_next():
        print(iterator.next())
        iterator.remove()
    print list

