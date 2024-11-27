package visitor;

// Visitor interface
interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

// Concrete Visitor
class AreaCalculator implements Visitor {
    @Override
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.println("Circle area: " + area);
    }

    @Override
    public void visit(Rectangle rectangle) {
        double area = rectangle.getLength() * rectangle.getWidth();
        System.out.println("Rectangle area: " + area);
    }
}

// Element interface
interface Shape {
    void accept(Visitor visitor);
}

// Concrete Element 1
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Element 2
class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
            new Circle(5),
            new Rectangle(2, 3)
        };

        Visitor visitor = new AreaCalculator();
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
}