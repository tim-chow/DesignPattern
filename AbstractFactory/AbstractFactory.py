# coding: utf8

import abc


class Television(object):
    def __init__(self, branch):
        self._branch = branch

    def say(self):
        print("I am %s television" % self._branch)


class TCLTelevision(Television):
    def __init__(self):
        Television.__init__(self, "TCL")


class HaierTelevision(Television):
    def __init__(self):
        Television.__init__(self, "Haier")


class AirCondition(object):
    def __init__(self, branch):
        self._branch = branch

    def say(self):
        print("I am %s aircondition" % self._branch)


class TCLAirCondition(AirCondition):
    def __init__(self):
        AirCondition.__init__(self, "TCL")


class HaierAirCondition(AirCondition):
    def __init__(self):
        AirCondition.__init__(self, "Haier")


class AbstractFactory(object):
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def create_television(self):
        pass

    @abc.abstractmethod
    def create_aircondition(self):
        pass


class TCLFactory(AbstractFactory):
    def create_television(self):
        return TCLTelevision()

    def create_aircondition(self):
        return TCLAirCondition()


class HaierFactory(AbstractFactory):
    def create_television(self):
        return HaierTelevision()

    def create_aircondition(self):
        return HaierAirCondition()


if __name__ == "__main__":
    tcl_factory = TCLFactory()
    tcl_factory.create_television().say()
    tcl_factory.create_aircondition().say()

    haier_factory = HaierFactory()
    haier_factory.create_television().say()
    haier_factory.create_aircondition().say()
