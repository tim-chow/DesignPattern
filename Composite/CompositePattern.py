from abc import ABCMeta, abstractmethod
import os


class Node(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_composite(self):
        pass

    @abstractmethod
    def get_name(self):
        pass

    @abstractmethod
    def get_sub_nodes(self):
        pass

    @abstractmethod
    def get_node_by_name(self, name):
        pass

    @abstractmethod
    def add_node(self, node):
        pass

    @abstractmethod
    def get_files(self):
        pass

    @abstractmethod
    def delete_node_by_name(self, name):
        pass

    @classmethod
    def tree(cls, node, prefix=""):
        if not node.is_composite():
            return [os.path.join(prefix, node.get_name())]

        nodes = []
        for sub_node in node.get_sub_nodes():
            if not sub_node.is_composite():
                nodes.append(os.path.join(prefix, node.get_name(), sub_node.get_name()))
                continue
            nodes.extend(cls.tree(sub_node, os.path.join(prefix, node.get_name())))
        return nodes


class File(Node):
    def __init__(self, name):
        self._name = name

    def is_composite(self):
        return False

    def get_name(self):
        return self._name

    def get_sub_nodes(self):
        raise RuntimeError()

    def get_node_by_name(self, name):
        raise RuntimeError()

    def add_node(self, node):
        raise RuntimeError()

    def get_files(self):
        return self._name

    def delete_node_by_name(self, name):
        raise RuntimeError()


class Directory(Node):
    def __init__(self, name):
        self._name = name
        self._map = {}

    def is_composite(self):
        return True

    def get_name(self):
        return self._name

    def get_sub_nodes(self):
        return self._map.values()

    def get_node_by_name(self, name):
        return self._map[name]

    def add_node(self, node):
        self._map[node.get_name()] = node

    def delete_node_by_name(self, name):
        self._map.pop(name)

    def get_files(self):
        return self.tree(self)


if __name__ == "__main__":
    root = Directory("")
    root.add_node(Directory("etc"))
    root.add_node(Directory("lib"))
    etc = root.get_node_by_name("etc")
    lib = root.get_node_by_name("lib")
    etc.add_node(File("my.cnf"))
    etc.add_node(File("ld.so.conf"))
    etc.add_node(Directory("yum.repos.d"))
    lib.add_node(File("ld.so"))
    yum_repos_d = etc.get_node_by_name("yum.repos.d")
    yum_repos_d.add_node(File("CentOS-Base.repo"))

    print(root.get_files())
