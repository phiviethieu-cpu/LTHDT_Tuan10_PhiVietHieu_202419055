/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

/**
 * Thiết bị có thể khóa và mở khóa.
 * Tách riêng khỏi {@link IDevice} vì đèn và quạt không khóa được
 * (nguyên tắc Interface Segregation).
 */
public interface ILockable {

    /** Khóa thiết bị. */
    void lock();

    /** Mở khóa thiết bị. */
    void unlock();
}
