class Filter:
    def do_filter(self, request, response, chain):
        raise NotImplementedError(
            "`do_fileter` method should "
            "be override in subclass")

class ConreteFilter1(Filter):
    def do_filter(self, request, response, chain):
        print("ConreteFilter1: before chain")
        chain.do_filter(request, response)
        print("ConreteFilter1: after chain")

class ConreteFilter2(Filter):
    def do_filter(self, request, response, chain):
        print("ConreteFilter2: before chain")
        chain.do_filter(request, response)
        print("ConreteFilter2: after chain")

class Servlet:
    def process(self, request, response):
        print("Servlet is processing")

class FilterChain:
    def __init__(self):
        self._filters = []
        self._servlet = None
        self._where = 0

    def add_filter(self, filter):
        self._filters.append(filter)
        return self

    def add_servlet(self, servlet):
        self._servlet = servlet

    def do_filter(self, request, response):
        if self._where < len(self._filters):
            self._where = self._where + 1
            self._filters[self._where - 1].do_filter(
                request, response, self)
        else:
            self._servlet.process(request, response)
            

if __name__ == "__main__":
    filter_chain = FilterChain()
    filter_chain.\
        add_filter(ConreteFilter1()).\
        add_filter(ConreteFilter2()).\
        add_servlet(Servlet())

    filter_chain.do_filter({}, {})

