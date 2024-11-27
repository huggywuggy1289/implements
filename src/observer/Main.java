package observer;

import java.util.ArrayList;
import java.util.List;

// Subject interface : observer 객체를 등록/삭제/알림
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature; // 온도상태 관리
        notifyObservers(); // 상태가 변경되면 모든 관찰자에게 상태 변경사실을 알림
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override // 모든 관찰자에게 알린다는 메서드 구체화
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Observer interface
interface Observer { // subject 상태가 변경되면 통지받는 클래스
    void update();
}

// Concrete Observer Observer 인터페이스를 구현한 구체적인 관찰자 클래스
class TemperatureDisplay implements Observer {
    private WeatherStation weatherStation;

    public TemperatureDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        this.weatherStation.registerObserver(this);//WeatherStation 객체에 관찰자로 등록
    }

    @Override
    public void update() {
        System.out.println("Temperature updated: " + weatherStation.getTemperature() + "°C");
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        TemperatureDisplay display1 = new TemperatureDisplay(weatherStation);
        TemperatureDisplay display2 = new TemperatureDisplay(weatherStation);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);
    }
}