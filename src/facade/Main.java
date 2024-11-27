package facade;

// Subsystem class 1
class CPU {
    public void start() {
        System.out.println("CPU started.");
    }

    public void execute() {
        System.out.println("CPU executing instructions.");
    }

    public void stop() {
        System.out.println("CPU stopped.");
    }
}

// Subsystem class 2
class Memory {
    public void load() {
        System.out.println("Memory loaded.");
    }

    public void unload() {
        System.out.println("Memory unloaded.");
    }
}

// Subsystem class 3
class HardDrive {
    public void readData() {
        System.out.println("HardDrive reading data.");
    }

    public void writeData() {
        System.out.println("HardDrive writing data.");
    }
}
// CPU 클래스는 start(), execute(), stop() 메서드를,
// Memory 클래스는 load(), unload() 메서드를, 
// HardDrive 클래스는 readData(), writeData() 메서드를 가지고 있음.

// Facade class
class ComputerFacade {
    // 이 클래스는 아래처럼 여러 서브시스템 객체를 포함
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    // 서브 시스템에 포함된 메서드들을 이용하는 새로운 메서드 생성
    // 즉, 내부적으로 서브시스템의 여러 메서드를 호출
    public void startComputer() {
        cpu.start();
        memory.load();
        hardDrive.readData();
        cpu.execute();
    }

    public void stopComputer() {
        cpu.stop();
        memory.unload();
        hardDrive.writeData();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        // Facade클래스의 두개의 메소드 호출 그안에 포함된 여러 서브시스템 메서드도 호출
        computer.startComputer();
        computer.stopComputer();
    }
}