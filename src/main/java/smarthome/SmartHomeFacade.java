/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

import java.util.Objects;

/**
 * Facade cung cấp các chế độ dùng nhiều thiết bị cùng lúc, để client không phải
 * gọi từng thiết bị riêng lẻ. Client vẫn điều khiển trực tiếp từng thiết bị được khi cần.
 */
public final class SmartHomeFacade {

    private final IDevice light;
    private final IDevice fan;
    private final ILockable door;

    /**
     * Tạo facade cho một nhóm thiết bị.
     *
     * @param light đèn
     * @param fan   quạt
     * @param door  cửa (cần khả năng khóa)
     * @throws NullPointerException nếu thiếu thiết bị nào
     */
    public SmartHomeFacade(IDevice light, IDevice fan, ILockable door) {
        this.light = Objects.requireNonNull(light, "light");
        this.fan = Objects.requireNonNull(fan, "fan");
        this.door = Objects.requireNonNull(door, "door");
    }

    /** Chế độ đi làm: tắt đèn, tắt quạt, khóa cửa. */
    public void goToWork() {
        light.turnOff();
        fan.turnOff();
        door.lock();
    }

    /**
     * Chế độ đi ngủ: tắt quạt, khóa cửa, bật đèn ngủ, sau đó in mức sáng
     * ban đêm đọc từ {@link HomeConfiguration}.
     */
    public void goToSleep() {
        fan.turnOff();
        door.lock();
        light.turnOn();

        int level = HomeConfiguration.getInstance().getNightLightLevel();
        System.out.println("Night light level: " + level + "%");
    }
}
