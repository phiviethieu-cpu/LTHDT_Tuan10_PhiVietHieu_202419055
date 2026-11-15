/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

import java.util.Locale;

/**
 * Factory tạo thiết bị theo loại do người dùng chọn.
 * Client chỉ gọi {@link #create(String, String)} và nhận về {@link IDevice},
 * không cần gọi constructor của từng lớp cụ thể.
 */
public final class DeviceFactory {

    /** Không cho tạo đối tượng vì lớp chỉ chứa phương thức static. */
    private DeviceFactory() {
    }

    /**
     * Tạo thiết bị theo loại. Chuỗi loại không phân biệt chữ hoa, chữ thường
     * và bỏ qua khoảng trắng ở hai đầu.
     *
     * @param type loại thiết bị: {@code "light"}, {@code "fan"} hoặc {@code "door"}
     * @param name tên hiển thị của thiết bị
     * @return thiết bị vừa tạo
     * @throws IllegalArgumentException nếu {@code type} là {@code null} hoặc chưa được hỗ trợ
     */
    public static IDevice create(String type, String name) {
        if (type == null) {
            throw new IllegalArgumentException("Device type must not be null");
        }
        return switch (type.trim().toLowerCase(Locale.ROOT)) {
            case "light" -> new SmartLight(name);
            case "fan" -> new SmartFan(name);
            case "door" -> new SmartDoor(name);
            default -> throw new IllegalArgumentException("Unsupported device type: " + type);
        };
    }
}
