# coding: utf8

import abc


class Business(object):
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def process(self):
        pass


class ConcreteBusiness(Business):
    def process(self):
        print("process in ConcreteBusiness")


class BusinessProxy(Business):
    def __init__(self, business):
        self._business = business

    def process(self):
        print("before calling process")
        self._business.process()
        print("after calling process")


if __name__ == "__main__":
    business = ConcreteBusiness()
    proxy = BusinessProxy(business)
    proxy.process()
