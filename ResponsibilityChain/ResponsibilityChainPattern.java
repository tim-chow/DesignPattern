class Request {
}

abstract class Handler {
    private Handler nextHandler;

    public abstract void processRequest(Request request);

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }
}

class ConcreteHandler1 extends Handler {
    @Override
    public void processRequest(Request request) {
        System.out.println("ConcreteHandler1: "
             + "I am processing request: " + request);
        if (getNextHandler() != null)
            getNextHandler().processRequest(request);
    }
}

class ConcreteHandler2 extends Handler {
    @Override
    public void processRequest(Request request) {
        System.out.println("ConcreteHandler2: "
             + "I am processing request: " + request);
        if (getNextHandler() != null)
            getNextHandler().processRequest(request);
    }
}

public class ResponsibilityChainPattern {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNextHandler(handler2);

        handler1.processRequest(new Request());
    }
}

