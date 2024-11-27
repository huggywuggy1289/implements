package command;

// Command interface
interface Command {
    void execute();
}

// Receiver class: 클라이언트 서비스를 수신하는 클래스
class Light {
    public void turnOn() { // 얘는 TurnOnLightCommand라는 Concrete Command를 가지고 있음.
        System.out.println("The light is on");
    }

    public void turnOff() { // 얘는 TurnOffLightCommand라는 Concrete Command를 가지고 있음.
        System.out.println("The light is off");
    }
}

// Concrete Command for turning on the light
class TurnOnLightCommand implements Command {
    private Light light; // 클라이언트 서비스를 수신하는 클래스를 가지고 있는 concrete command

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command for turning off the light
class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker class : 이걸 통해 실행 요청이 오면 concreteCommand의 excute()에서는 receiver의 연산을 호출하거나 실행하도록 구현함.
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command turnOn = new TurnOnLightCommand(light);
        Command turnOff = new TurnOffLightCommand(light);

        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(turnOn);
        remote.pressButton();

        // Turn off the light
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}