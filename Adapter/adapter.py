import abc


class Target(object):
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def target_method(self):
        pass


class Adaptee(object):
    def method(self):
        print("method in Adaptee")


class Adapter(Target):
    def __init__(self, adaptee):
        self._adaptee = adaptee

    def target_method(self):
        self._adaptee.method()


if __name__ == "__main__":
    adapter = Adapter(Adaptee())
    adapter.target_method()
