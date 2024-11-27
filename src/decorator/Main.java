package decorator;

// 기존에 구현된 클래스에 그때그때 필요한 기능을 추가해나가는 패턴
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component : 다른 객체를 장식할 수 없는 가운데 심 역할하는 클래스
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}
// >> SimpleCoffee 클래스는 기본 커피를 나타냄.
// Coffee 인터페이스를 구현하고, 기본 커피의 설명과 비용을 반환

// Base Decorator : 장식자들의 부모 클래스
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// MilkDecorator와 SugarDecorator는 CoffeeDecorator를 확장하여 각각 우유와 설탕을 추가하는 기능을 제공
// Concrete Decorator 1
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

// Concrete Decorator 2
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.2;
    }
}

// Client code 클라이언트 코드로, 커피 객체를 생성하고 데코레이터들을 사용하여 기능을 동적으로 추가
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}