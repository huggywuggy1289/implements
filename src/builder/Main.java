package builder;

// 코드 빌드패턴구조: [Client] → [Director] → [Builder Interface] → [Concrete Builder] → [Product]
// Product class
class House {
    private String foundation;
    private String structure;
    private String roof;
    // setFoundation, setStructure, setRoof 메서드를 통해 객체의 구성 요소를 설정
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    @Override
    public String toString() {
        return "House [foundation=" + foundation + ", structure=" + structure + ", roof=" + roof + "]";
    }
}

// Builder interface : Product 객체를 구성하기 위한 공통 인터페이스
interface HouseBuilder {
    void buildFoundation();
    void buildStructure();
    void buildRoof();
    House getHouse(); // 최종적으로 완성된 Product객체 반환
}

// Concrete Builder 1 : Builder 인터페이스를 구현하고 특정 타입의 Product 객체를 생성하는 책임을 가짐.
class WoodenHouseBuilder implements HouseBuilder {
    private House house;

    public WoodenHouseBuilder() {
        this.house = new House();
    }

    @Override
    public void buildFoundation() {
        house.setFoundation("Wooden foundation");
        System.out.println("WoodenHouseBuilder: Foundation complete...");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Wooden structure");
        System.out.println("WoodenHouseBuilder: Structure complete...");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Wooden roof");
        System.out.println("WoodenHouseBuilder: Roof complete...");
    }

    @Override
    public House getHouse() {
        return this.house;
    }
}

// Concrete Builder 2
class BrickHouseBuilder implements HouseBuilder {
    private House house;

    public BrickHouseBuilder() {
        this.house = new House();
    }

    @Override
    public void buildFoundation() {
        house.setFoundation("Brick foundation");
        System.out.println("BrickHouseBuilder: Foundation complete...");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Brick structure");
        System.out.println("BrickHouseBuilder: Structure complete...");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Brick roof");
        System.out.println("BrickHouseBuilder: Roof complete...");
    }

    @Override
    public House getHouse() {
        return this.house;
    }
}

// Director : Builder를 사용하여 Product를 생성하는 단계를 정의 및 통제
class ConstructionEngineer {
    private HouseBuilder houseBuilder; // 부모타입: 나무집과 벽돌집 둘다 받을 수 있음.

    public ConstructionEngineer(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House constructHouse() {
        this.houseBuilder.buildFoundation(); //기초
        this.houseBuilder.buildStructure(); //구조물
        this.houseBuilder.buildRoof(); //지붕
        return this.houseBuilder.getHouse(); // 집반환
    }
}

// Client code
public class Main {
    public static void main(String[] args) {

        //엔지니어한테 부탁해서 빌더를 통해 집을 만들어달라고 요청
        // 빌더는 엔지니어가 통제
        HouseBuilder woodenHouseBuilder = new WoodenHouseBuilder();
        ConstructionEngineer engineer1 = new ConstructionEngineer(woodenHouseBuilder);
        House woodenHouse = engineer1.constructHouse();
        System.out.println("House is: " + woodenHouse);

        HouseBuilder brickHouseBuilder = new BrickHouseBuilder();
        ConstructionEngineer engineer2 = new ConstructionEngineer(brickHouseBuilder);
        House brickHouse = engineer2.constructHouse();
        System.out.println("House is: " + brickHouse);
    }
}