package smarthome;

public class SmartDoor implements IDevice, ILockable {

    private final String name;
    private boolean on;
    private boolean locked;

    public SmartDoor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isLocked() {
        return locked;
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

    @Override
    public void lock() {
        locked = true;
        System.out.println(name + ": LOCKED");
    }

    @Override
    public void unlock() {
        locked = false;
        System.out.println(name + ": UNLOCKED");
    }
}
