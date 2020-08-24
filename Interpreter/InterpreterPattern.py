# coding: utf8

from abc import ABCMeta, abstractmethod


class Expression(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def interpret(self):
        pass

    @abstractmethod
    def get_priority(self):
        pass


class NumberExpression(Expression):
    cn_to_number = {}
    cn_to_number[u"零"] = 0
    cn_to_number[u"一"] = 1
    cn_to_number[u"二"] = 2
    cn_to_number[u"三"] = 3
    cn_to_number[u"四"] = 4
    cn_to_number[u"五"] = 5
    cn_to_number[u"六"] = 6
    cn_to_number[u"七"] = 7
    cn_to_number[u"八"] = 8
    cn_to_number[u"九"] = 9

    def __init__(self, chinese_number):
        self._cn = chinese_number

    def interpret(self):
        return self.cn_to_number[self._cn]

    def get_priority(self):
        return 1


class UnitExpression(Expression):
    unit_to_number = {}
    unit_to_number[u"十"] = 10 ** 1
    unit_to_number[u"百"] = 10 ** 2
    unit_to_number[u"千"] = 10 ** 3
    unit_to_number[u"万"] = 10 ** 4
    unit_to_number[u"亿"] = 10 ** 8

    def __init__(self, unit, lefts):
        self._unit = unit
        self._lefts = lefts

    def interpret(self):
        base = sum([left.interpret() for left in self._lefts])
        return base * self.unit_to_number[self._unit]

    def get_priority(self):
        return self.unit_to_number[self._unit]


class Context(object):
    def __init__(self, chinese_number):
        self._cn = chinese_number

    def convert(self):
        stack = []

        for char in self._cn:
            if char == u"零":
                continue
            if char in NumberExpression.cn_to_number:
                stack.append(NumberExpression(char))
                continue
            if char in UnitExpression.unit_to_number:
                lefts = []
                for ind in range(len(stack) - 1, -1, -1):
                    if stack[ind].get_priority() > UnitExpression.unit_to_number[char]:
                        break
                    lefts.insert(0, stack.pop(ind))
                stack.append(UnitExpression(char, lefts))
                continue
            raise ValueError("unknown character")
        return sum(item.interpret() for item in stack)


if __name__ == "__main__":
    print(Context(u"三千二百五十亿四千三百万八千一百二十九").convert())
