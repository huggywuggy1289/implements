package bridge;

// Implementor interface
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

// Concrete Implementor 1
class TV implements Device {
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("Turning on the TV.");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the TV.");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Setting TV volume to " + volume);
    }
}

// Concrete Implementor 2
class Radio implements Device {
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("Turning on the Radio.");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the Radio.");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Setting Radio volume to " + volume);
    }
}

// Abstraction
abstract class RemoteControl {
    protected Device device;

    protected RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void setVolume(int volume);
}

// Refined Abstraction
class BasicRemoteControl extends RemoteControl {
    public BasicRemoteControl(Device device) {
        super(device);
    }

    @Override
    public void turnOn() {
        device.turnOn();
    }

    @Override
    public void turnOff() {
        device.turnOff();
    }

    @Override
    public void setVolume(int volume) {
        device.setVolume(volume);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl tvRemote = new BasicRemoteControl(tv);
        tvRemote.turnOn();
        tvRemote.setVolume(10);
        tvRemote.turnOff();

        Device radio = new Radio();
        RemoteControl radioRemote = new BasicRemoteControl(radio);
        radioRemote.turnOn();
        radioRemote.setVolume(5);
        radioRemote.turnOff();
    }
}