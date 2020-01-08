import types


class SimpleFactory(object):
    @staticmethod
    def create(product_class_name, *a, **kw):
        clazz = globals().get(product_class_name)
        if clazz != None:
            return clazz(*a, **kw)
        raise RuntimeError("no class named: %s was found" 
            % product_class_name)


class Phone(object):
    def __init__(self, branch, os):
        self._branch = branch
        self._os = os

    def say(self):
        print("I am " + self._branch + 
            ", and my OS is: " + self._os)


class IPhone(Phone):
    def __init__(self, os):
        super(self.__class__, self).__init__("IPhone", os)


class Oneplus(Phone):
    def __init__(self, os):
        super(self.__class__, self).__init__("Oneplus", os)

if __name__ == "__main__":
    SimpleFactory.create("IPhone", "IOS8").say()
    SimpleFactory.create("Oneplus", "Android7").say()

