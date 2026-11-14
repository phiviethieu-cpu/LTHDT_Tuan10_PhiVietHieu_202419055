package smarthome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeviceFactoryTest {

    @Test
    @DisplayName("T1: tạo light, fan, door trả đúng kiểu")
    void createsCorrectTypes() {
        IDevice light = DeviceFactory.create("light", "Living room light");
        IDevice fan = DeviceFactory.create("fan", "Living room fan");
        IDevice door = DeviceFactory.create("door", "Main door");

        assertInstanceOf(SmartLight.class, light);
        assertInstanceOf(SmartFan.class, fan);
        assertInstanceOf(SmartDoor.class, door);
        assertInstanceOf(ILockable.class, door);
        assertEquals("Main door", ((SmartDoor) door).getName());
    }

    @Test
    @DisplayName("T1: không phân biệt chữ hoa, chữ thường và khoảng trắng")
    void ignoresCaseAndSpaces() {
        assertInstanceOf(SmartLight.class, DeviceFactory.create("LIGHT", "a"));
        assertInstanceOf(SmartFan.class, DeviceFactory.create(" Fan ", "b"));
        assertInstanceOf(SmartDoor.class, DeviceFactory.create("dOoR", "c"));
    }

    @Test
    @DisplayName("T2: loại camera chưa hỗ trợ thì ném IllegalArgumentException")
    void rejectsUnsupportedType() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> DeviceFactory.create("camera", "Front camera"));
        assertTrue(ex.getMessage().contains("camera"));
    }

    @Test
    @DisplayName("T2: loại null cũng ném IllegalArgumentException")
    void rejectsNullType() {
        assertThrows(IllegalArgumentException.class, () -> DeviceFactory.create(null, "x"));
    }
}
