import types

class Factory:
    @staticmethod
    def create(product_class_name, *a, **kw):
        clazz = globals().get(product_class_name)
        if isinstance(clazz,
            (types.TypeType, types.ClassType)):
            return clazz(*a, **kw)
        raise RuntimeError("no class named: %s found" 
            % product_class_name)

class Phone(object):
    def say(self):
        print("I am " + self._branch + 
            ", and my os is: " + self._os)

class Oneplus(Phone):
    def __init__(self, os):
        self._branch = "Oneplus"
        self._os = os

class IPhone(Phone):
    def __init__(self, os):
        self._branch = "IPhone"
        self._os = os

def test():
    Factory.create("IPhone", "IOS8").say()
    Factory.create("Oneplus", "Android7").say()

if __name__ == "__main__":
    test()

