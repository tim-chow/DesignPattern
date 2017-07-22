class Context {
    private final AirConditioningState open =
        new OpenState();
    private final AirConditioningState close =
        new CloseState();
    private final AirConditioningState cool = 
        new CoolState();
    private final AirConditioningState heat = 
        new HeatState();
    private AirConditioningState currentState = close;

    public void toOpen() {
        if (currentState.open())
            currentState = open;
    }

    public void toClose() {
        if (currentState.close())
            currentState = close;
    }

    public void toCool() {
        if (currentState.cool())
            currentState = cool;
    }

    public void toHeat() {
        if (currentState.heat())
            currentState = heat;
    }
}

abstract class AirConditioningState {
    public boolean open() {
        System.out.println("illegal state, unable to open");
        return false;
    }

    public boolean close() {
        System.out.println("illegal state, unable to close");
        return false;
    }

    public boolean cool() {
        System.out.println("illegal state, unable to cool");
        return false;
    }

    public boolean heat() {
        System.out.println("illegal state, unable to heat");
        return false;
    }
}

class OpenState extends AirConditioningState {
    public boolean close() {
        System.out.println("change to state: `close`");
        return true;
    }

    public boolean cool() {
        System.out.println("change to state: `cool`");
        return true;
    }

    public boolean heat() {
        System.out.println("change to state: `heat`");
        return true;
    }
}

class CloseState extends AirConditioningState {
    public boolean open() {
        System.out.println("change to state: `open`");
        return true;
    }
}

class CoolState extends AirConditioningState {
    public boolean close() {
        System.out.println("change to state: `close`");
        return true;
    }

    public boolean heat() {
        System.out.println("change to state: `heat`");
        return true;
    }
}

class HeatState extends AirConditioningState {
    public boolean close() {
        System.out.println("change to state: `close`");
        return true;
    }

    public boolean cool() {
        System.out.println("change to state: `cool`");
        return true;
    }
}


public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context();
        context.toHeat();
        context.toOpen();
        context.toHeat();
        context.toCool();
        context.toClose();
        context.toClose();
    }
}

