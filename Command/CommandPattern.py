class Receiver:
    def function1(self):
        print("function1")

    def function2(self):
        print("function2")

class Command:
    def __init__(self):
        self._receiver = None
    
    def set_receiver(self, receiver):
        self._receiver = receiver

    def execute_command(self):
        raise NotImplementedError(
            "this method should be overriden in subclass")

# every concrete command responses for 
# one function
class ConcreteCommand1(Command):
    def execute_command(self):
        self._receiver.function1()

class ConcreteCommand2(Command):
    def execute_command(self):
        self._receiver.function2()

class Invoker:
    def __init__(self):
        self._command1 = None
        self._command2 = None

    def set_command1(self, command):
        self._command1 = command

    def set_command2(self, command):
        self._command2 = command

    def function1(self):
        self._command1.execute_command()

    def function2(self):
        self._command2.execute_command()

def client():
    receiver = Receiver()
    command1 = ConcreteCommand1()
    command2 = ConcreteCommand2()
    command1.set_receiver(receiver)
    command2.set_receiver(receiver)
    invoker = Invoker()
    invoker.set_command1(command1)
    invoker.set_command2(command2)

    invoker.function2()
    invoker.function1()

if __name__ == "__main__":
    client()

