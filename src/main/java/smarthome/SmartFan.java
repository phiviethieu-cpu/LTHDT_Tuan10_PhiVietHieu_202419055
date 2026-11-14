package smarthome;

public class SmartFan implements IDevice {

    private final String name;
    private boolean on;

    public SmartFan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return on;
    }

    @Override
    public void turnOn() {
        on = true;
        System.out.println(name + ": ON");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println(name + ": OFF");
    }
}
