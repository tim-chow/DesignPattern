class Snapshot:
    def __init__(self, content):
        self._content = content

    @property
    def content(self):
        return self._content

    @content.setter
    def content(self, *a, **kw):
        raise RuntimeError("illegal action")

class Caretaker:
    _queue = []
    _max_size = 10

    @classmethod
    def set_max_size(cls, max_size):
        cls._max_size = max_size

    @classmethod
    def add_snapshot(cls, snapshot):
        if len(cls._queue) >= cls._max_size:
            cls._queue.pop(0)
        cls._queue.append(snapshot)

    @classmethod
    def get_snapshot(cls, offset=0):
        if offset >= len(cls._queue):
            return 
        return cls._queue[offset]

class Editor:
    def __init__(self, content):
        self._content = content

    def create_snapshot(self):
        return Snapshot(self._content)

    def recover_from_snapshot(self, snapshot):
        self._content = snapshot.content

    def change_content(self, start_offset, end_offset, new_content):
        self._content = self._content[:start_offset] + \
            new_content + self._content[end_offset + 1:]

    @property
    def content(self):
        return self._content

if __name__ == "__main__":
    editor = Editor("this is a original content")
    print(editor.content)

    print("create a snapshot")
    snapshot = editor.create_snapshot()
    Caretaker.add_snapshot(snapshot)

    print("change content")
    editor.change_content(8, 17, "another")
    print(editor.content)

    print("create a snapshot")
    snapshot = editor.create_snapshot()
    Caretaker.add_snapshot(snapshot)

    print("recover from snapshot[0]")
    snapshot = Caretaker.get_snapshot(0)
    editor.recover_from_snapshot(snapshot)
    print(editor.content)

    print("recover from snapshot[1]")
    snapshot = Caretaker.get_snapshot(1)
    editor.recover_from_snapshot(snapshot)
    print(editor.content)

