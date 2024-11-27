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

// Facade class
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

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
        computer.startComputer();
        computer.stopComputer();
    }
}