package smarthome;

public class SmartLight implements IDevice {

    private final String name;
    private boolean on;

    public SmartLight(String name) {
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
