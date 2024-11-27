package bridge;

// Implementor interface : 구현 계층 구조의 최상위 클래스
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
// -------------------------------------------------기능과 구현의 분리---------------------------

// Abstraction : 기능 계층 구조에서의 최상위 클래스
abstract class RemoteControl {
    protected Device device;

    protected RemoteControl(Device device) { // 구현계층의 디바이스 참조
        this.device = device;
    }
    // 추상적인 기능 정의
    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void setVolume(int volume);
}

// Refined Abstraction: 세부 추상화 계층이자 실제 기능 구현
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
        Device tv = new TV(); // 최상위 구현계층을 참조하는 변수 = new 실제 구현클래스;
        RemoteControl tvRemote = new BasicRemoteControl(tv); // 그 변수를 실제 기능클래스에 넣어서
        tvRemote.turnOn(); // 그 변수를 통해 실제 기능 구현 가능
        tvRemote.setVolume(10);
        tvRemote.turnOff();

        Device radio = new Radio();
        RemoteControl radioRemote = new BasicRemoteControl(radio);
        radioRemote.turnOn();
        radioRemote.setVolume(5);
        radioRemote.turnOff();
    }
}