from abc import ABCMeta, abstractmethod

class Node:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_composite(self):
        pass

    @abstractmethod
    def get_name(self):
        pass

    @abstractmethod
    def get_sub_node(self):
        pass

    @abstractmethod
    def get_node_by_name(self, name):
        pass

    @abstractmethod
    def add_node(self, node):
        pass

    @abstractmethod
    def tree(self):
        pass

class File(Node):
    def __init__(self, name):
        self._name = name

    def is_composite(self):
        return False

    def get_name(self):
        return self._name

    def get_sub_node(self):
        raise RuntimeError(self._name + " is not directory")
    
    def get_node_by_name(self, name):
        raise RuntimeError(self._name + " is not directory")

    def add_node(self, node):
        raise RuntimeError(self._name + " is not directory")

    def tree(self):
        print self._name

class Directory(Node):
    def __init__(self, name):
        self._name = name
        self._map = {}

    def is_composite(self):
        return True

    def get_name(self):
        return self._name

    def get_sub_node(self):
        return self._map

    def get_node_by_name(self, name):
        return self._map[name]

    def add_node(self, node):
        self._map[node.get_name()] = node

    def tree(self):
        self.__class__._tree("", self)

    @classmethod
    def _tree(cls, prefix, node):
        print prefix + node.get_name()
        if not node.is_composite():
            return
        for _, subnode in node.get_sub_node().iteritems():
            cls._tree(prefix + " " * (len(node.get_name()) + 1), subnode)

if __name__ == "__main__":
    root = Directory("/")
    root.add_node(Directory("etc"))
    root.add_node(Directory("lib"))
    etc = root.get_node_by_name("etc")
    lib = root.get_node_by_name("lib")
    etc.add_node(File("my.cnf"))
    lib.add_node(File("ld.so"))

    my_cnf = etc.get_node_by_name("my.cnf")
    my_cnf.tree()

    root.tree()

