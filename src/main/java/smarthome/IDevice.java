/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

/**
 * Abstraction chung cho mọi thiết bị trong nhà: thiết bị nào cũng bật và tắt được.
 * Client làm việc qua interface này thay vì phụ thuộc vào lớp cụ thể.
 */
public interface IDevice {

    /** Bật thiết bị. */
    void turnOn();

    /** Tắt thiết bị. */
    void turnOff();
}
