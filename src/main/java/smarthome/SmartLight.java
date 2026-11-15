/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

/**
 * Đèn thông minh. Mỗi lần đổi trạng thái sẽ in ra màn hình, ví dụ
 * {@code Living room light: ON}.
 */
public class SmartLight implements IDevice {

    /** Tên hiển thị của đèn. */
    private final String name;

    /** Trạng thái hiện tại: {@code true} là đang bật. */
    private boolean on;

    /**
     * Tạo đèn với tên cho trước, ban đầu ở trạng thái tắt.
     *
     * @param name tên hiển thị của đèn
     */
    public SmartLight(String name) {
        this.name = name;
    }

    /** @return tên hiển thị của đèn */
    public String getName() {
        return name;
    }

    /** @return {@code true} nếu đèn đang bật */
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
