package abstractFactory;

// Abstract product A
interface Chair {
    void sitOn();
}

// Concrete product A1
class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair.");
    }
}

// Concrete product A2
class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Modern chair.");
    }
}

// Abstract product B
interface Sofa {
    void lieOn();
}

// Concrete product B1
class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a Victorian sofa.");
    }
}

// Concrete product B2
class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a Modern sofa.");
    }
}

// Abstract factory
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}

// Concrete factory 1
class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}

// Concrete factory 2
class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {

        // 제품을 직접 생성
        Chair chair = new VictorianChair();
        chair.sitOn();
        Sofa sofa  = new ModernSofa(); // 혹은 VictorianSofa라던지
        sofa.lieOn();

        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Chair victorianChair = victorianFactory.createChair();
        Sofa victorianSofa = victorianFactory.createSofa();

        victorianChair.sitOn();
        victorianSofa.lieOn();

        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Sofa modernSofa = modernFactory.createSofa();

        modernChair.sitOn();
        modernSofa.lieOn();
    }
}