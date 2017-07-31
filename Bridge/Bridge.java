class Shape {
    private Drawer drawer;
    
    public Shape(Drawer drawer) {
        this.drawer = drawer;
    }

    public <T extends Number> void draw(T... args) {
        this.drawer.draw(args);
    }
}

class CircleShape extends Shape {
    private int radius;
    
    public CircleShape(int radius) {
        super(new CircleDrawer());
        this.radius = radius;
    }

    public void draw() {
        super.draw(radius);
    }
}

class RectangleShape extends Shape {
    private int length;
    private int width;
    
    public RectangleShape(int length, int width) {
        super(new RectangleDrawer());
        this.length = length;
        this.width = width;
    }
    
    public void draw() {
        super.draw(length, width);
    }
}

interface Drawer {
    <T extends Number> void draw(T... args);
}

class CircleDrawer implements Drawer {
    public <T extends Number> void draw(T... args) {
        System.out.println("I am a circle, my radius " + 
            "is: " + args[0]);
    }
}

class RectangleDrawer implements Drawer {
    public <T extends Number> void draw(T... args) {
        System.out.println("I am a rectangle, my length" + 
            "is " + args[0] + ", width is " + args[1]);
    }
}

public class Bridge {
    public static void main(String[] args) {
        new CircleShape(100).draw();
        new RectangleShape(100, 200).draw();
    }
}
