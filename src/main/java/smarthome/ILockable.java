package smarthome;

/**
 * Thiết bị có thể khóa và mở khóa.
 * Tách riêng khỏi IDevice vì đèn và quạt không khóa được.
 */
public interface ILockable {

    void lock();

    void unlock();
}
