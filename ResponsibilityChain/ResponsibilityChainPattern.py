# coding: utf8

import abc


class Handler(object):
    __metaclass__ = abc.ABCMeta

    def __init__(self, next_handler=None):
        self._next_handler = next_handler

    def handle(self, request):
        self._handle(request)
        if self.next_handler is not None:
            self.next_handler.handle(request)

    @abc.abstractmethod
    def _handle(self, request):
        pass

    @property
    def next_handler(self):
        return self._next_handler


class ConcreteHandler1(Handler):
    def _handle(self, request):
        print("handle request in ConcreteHandler1")


class ConcreteHandler2(Handler):
    def _handle(self, request):
        print("handle request in ConcreteHandler2")


if __name__ == "__main__":
    import unittest


    class ResponsibilityChainTest(unittest.TestCase):
        def testResponsibilityChain(self):
            class Request(object):
                pass

            handler2 = ConcreteHandler2()
            handler1 = ConcreteHandler1(handler2)
            handler1.handle(Request())


    unittest.main()
