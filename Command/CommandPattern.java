class Receiver {
    public void function1() {
        System.out.println("function1");
    }

    public void function2() {
        System.out.println("function2");
    }
}

interface Command {
    public void executeCommand();
    public void setReceiver(Receiver receiver);
}

class ConcreteCommand1 implements Command {
    private Receiver receiver;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void executeCommand() {
        if (receiver != null)
            receiver.function1();
    }
}

class ConcreteCommand2 implements Command {
    private Receiver receiver;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void executeCommand() {
        if (receiver != null)
            receiver.function2();
    }
}

class Invoker {
    private Command command1;
    private Command command2;

    public void setCommand1(Command command) {
        command1 = command;
    }

    public void setCommand2(Command command) {
        command2 = command;
    }

    public void function1() {
        command1.executeCommand();
    }

    public void function2() {
        command2.executeCommand();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        // act as `Client Role`
        Receiver receiver = new Receiver();
        Command command1 = new ConcreteCommand1();
        Command command2 = new ConcreteCommand2();
        command1.setReceiver(receiver);
        command2.setReceiver(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand1(command1);
        invoker.setCommand2(command2);

        invoker.function2();
        invoker.function1();
    }
}

