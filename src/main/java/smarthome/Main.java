package smarthome;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Factory: tao thiet bi ===");
        IDevice light = DeviceFactory.create("light", "Living room light");
        IDevice fan = DeviceFactory.create("fan", "Living room fan");
        IDevice door = DeviceFactory.create("door", "Main door");

        try {
            DeviceFactory.create("camera", "Front camera");
        } catch (IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Dieu khien tung thiet bi ===");
        light.turnOn();
        fan.turnOn();
        ((ILockable) door).unlock();

        System.out.println();
        System.out.println("=== Singleton: cau hinh chung ===");
        HomeConfiguration config1 = HomeConfiguration.getInstance();
        HomeConfiguration config2 = HomeConfiguration.getInstance();
        System.out.println("Cung mot doi tuong: " + (config1 == config2));
        System.out.println("Ten nha: " + config1.getHomeName());

        SmartHomeFacade facade = new SmartHomeFacade(light, fan, (ILockable) door);

        System.out.println();
        System.out.println("=== Facade: che do Di lam ===");
        facade.goToWork();

        System.out.println();
        System.out.println("=== Facade: che do Di ngu ===");
        facade.goToSleep();
    }
}
