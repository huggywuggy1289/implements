package observer;

import java.util.ArrayList;
import java.util.List;

// Subject interface
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
        this.temperature = temperature;
        notifyObservers();
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

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Observer interface
interface Observer {
    void update();
}

// Concrete Observer
class TemperatureDisplay implements Observer {
    private WeatherStation weatherStation;

    public TemperatureDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
        this.weatherStation.registerObserver(this);
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