class Business:
    def process(self):
        print("Business processing")

class Proxy:
    def __init__(self, target):
        self._target = target

    def __getattr__(self, attr):
        if not getattr(self._target, attr, None):
            raise AttributeError("there is no"
                " attribute named: " + attr)

        func = getattr(self._target, attr)
        if not callable(func):
            return func
        return self._wrap(func)

    def _wrap(self, func):
        def _inner(*a, **kw):
            # here here here!!!
            print("before")
            result = func(*a, **kw)
            print("after")
            return result
        return _inner

if __name__ == "__main__":
    proxy = Proxy(Business())
    proxy.process()

