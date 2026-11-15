/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

/**
 * Chương trình demo minh họa ba mẫu thiết kế Factory, Singleton và Facade.
 */
public class Main {

    /**
     * Điểm bắt đầu chương trình.
     *
     * @param args không sử dụng
     */
    public static void main(String[] args) {
        // Factory: tạo thiết bị theo loại, client không gọi constructor trực tiếp
        System.out.println("=== Factory: tao thiet bi ===");
        IDevice light = DeviceFactory.create("light", "Living room light");
        IDevice fan = DeviceFactory.create("fan", "Living room fan");
        IDevice door = DeviceFactory.create("door", "Main door");

        // Loại chưa hỗ trợ thì factory ném IllegalArgumentException
        try {
            DeviceFactory.create("camera", "Front camera");
        } catch (IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        // Vẫn điều khiển riêng từng thiết bị được
        System.out.println();
        System.out.println("=== Dieu khien tung thiet bi ===");
        light.turnOn();
        fan.turnOn();
        ((ILockable) door).unlock();

        // Singleton: hai lần lấy cấu hình là cùng một đối tượng
        System.out.println();
        System.out.println("=== Singleton: cau hinh chung ===");
        HomeConfiguration config1 = HomeConfiguration.getInstance();
        HomeConfiguration config2 = HomeConfiguration.getInstance();
        System.out.println("Cung mot doi tuong: " + (config1 == config2));
        System.out.println("Ten nha: " + config1.getHomeName());

        // Facade: kích hoạt cả chế độ bằng một lời gọi
        SmartHomeFacade facade = new SmartHomeFacade(light, fan, (ILockable) door);

        System.out.println();
        System.out.println("=== Facade: che do Di lam ===");
        facade.goToWork();

        System.out.println();
        System.out.println("=== Facade: che do Di ngu ===");
        facade.goToSleep();
    }
}
