from abc import ABCMeta, abstractmethod

class Request:
    pass

class Handler:
    __metaclass__ = ABCMeta

    def __init__(self):
        self._next_handler = None

    @abstractmethod
    def process_request(self, request):
        pass

    def set_next_handler(self, next_handler):
        self._next_handler = next_handler

    def get_next_handler(self):
        return self._next_handler

class ConcreteHandler1(Handler):
    def process_request(self, request):
        print("ConcreteHandler1: " + 
            "I am processing request: %s" % request)

        if self.get_next_handler():
            self.get_next_handler().process_request(request)

class ConcreteHandler2(Handler):
    def process_request(self, request):
        print("ConcreteHandler2: " + 
            "I am processing request: %s" % request)

        if self.get_next_handler():
            self.get_next_handler().process_request(request)

def client():
    handler1 = ConcreteHandler1()
    handler2 = ConcreteHandler2()
    handler1.set_next_handler(handler2)

    handler1.process_request(Request())

if __name__ == "__main__":
    client()

