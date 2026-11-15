/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

/**
 * Cửa thông minh: vừa bật/tắt được như mọi thiết bị, vừa khóa/mở khóa được.
 * Mỗi lần đổi trạng thái sẽ in ra màn hình, ví dụ {@code Main door: LOCKED}.
 */
public class SmartDoor implements IDevice, ILockable {

    /** Tên hiển thị của cửa. */
    private final String name;

    /** Trạng thái nguồn: {@code true} là đang bật. */
    private boolean on;

    /** Trạng thái khóa: {@code true} là đang khóa. */
    private boolean locked;

    /**
     * Tạo cửa với tên cho trước, ban đầu tắt và chưa khóa.
     *
     * @param name tên hiển thị của cửa
     */
    public SmartDoor(String name) {
        this.name = name;
    }

    /** @return tên hiển thị của cửa */
    public String getName() {
        return name;
    }

    /** @return {@code true} nếu cửa đang bật */
    public boolean isOn() {
        return on;
    }

    /** @return {@code true} nếu cửa đang khóa */
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
