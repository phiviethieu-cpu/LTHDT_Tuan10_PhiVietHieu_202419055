package smarthome;

import java.util.Objects;

public final class SmartHomeFacade {

    private final IDevice light;
    private final IDevice fan;
    private final ILockable door;

    public SmartHomeFacade(IDevice light, IDevice fan, ILockable door) {
        this.light = Objects.requireNonNull(light, "light");
        this.fan = Objects.requireNonNull(fan, "fan");
        this.door = Objects.requireNonNull(door, "door");
    }

    public void goToWork() {
        light.turnOff();
        fan.turnOff();
        door.lock();
    }

    public void goToSleep() {
        fan.turnOff();
        door.lock();
        light.turnOn();

        int level = HomeConfiguration.getInstance().getNightLightLevel();
        System.out.println("Night light level: " + level + "%");
    }
}
