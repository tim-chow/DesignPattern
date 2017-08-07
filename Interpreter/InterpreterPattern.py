#coding: utf8
from abc import ABCMeta, abstractmethod

context = {"number": {}, "unit": {}}
context["number"][u"零"] = 0
context["number"][u"一"] = 1
context["number"][u"二"] = 2
context["number"][u"三"] = 3
context["number"][u"四"] = 4
context["number"][u"五"] = 5
context["number"][u"六"] = 6
context["number"][u"七"] = 7
context["number"][u"八"] = 8
context["number"][u"九"] = 9

context["unit"][u"十"] = 10
context["unit"][u"百"] = 100
context["unit"][u"千"] = 1000
context["unit"][u"万"] = 10000
context["unit"][u"亿"] = 100000000

class Expression:
    __metaclass__ = ABCMeta

    @abstractmethod
    def interpreter(self, context):
        pass

    @abstractmethod
    def get_priority(self, context):
        pass

class NumberExpression(Expression):
    def __init__(self, chinese_number):
        self._cn = chinese_number 

    def interpreter(self, context):
        return context["number"][self._cn]

    def get_priority(self, context):
        return 1

class UnitExpression(Expression):
    def __init__(self, unit, lefts):
        self._unit = unit
        self._lefts = lefts

    def interpreter(self, context):
        base = sum([left.interpreter(context) for
            left in self._lefts])
        return base * context["unit"][self._unit]

    def get_priority(self, context):
        return context["unit"][self._unit]

class Converter:
    def __init__(self):
        pass

    def convert(self, chinese_number):
        global context
        cn = chinese_number
        stack = []

        for word in cn:
            if word == u"零":
                continue
            if word in context["number"]:
                stack.append(NumberExpression(word))
                continue
            if word in context["unit"]:
                lefts = []
                for ind in range(len(stack)-1, -1, -1):
                    if stack[ind].get_priority(context) > \
                        context["unit"][word]:
                        break
                    lefts.insert(0, stack.pop(ind))
                stack.append(UnitExpression(word, lefts))
        return sum(item.interpreter(context) for item in stack)

if __name__ == "__main__":
    input_string = raw_input(u"请输入汉字表示：".encode("utf8"))
    print Converter().convert(unicode(input_string, "utf8"))

