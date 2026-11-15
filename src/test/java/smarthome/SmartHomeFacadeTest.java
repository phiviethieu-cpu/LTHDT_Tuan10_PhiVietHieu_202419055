package smarthome;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SmartHomeFacadeTest {

    private SmartLight light;
    private SmartFan fan;
    private SmartDoor door;
    private SmartHomeFacade facade;

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        light = (SmartLight) DeviceFactory.create("light", "Living room light");
        fan = (SmartFan) DeviceFactory.create("fan", "Living room fan");
        door = (SmartDoor) DeviceFactory.create("door", "Main door");
        facade = new SmartHomeFacade(light, fan, door);
        System.setOut(new PrintStream(output, true));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        HomeConfiguration.getInstance().setNightLightLevel(30);
    }

    @Test
    @DisplayName("T4: goToWork() tắt đèn, tắt quạt, khóa cửa")
    void goToWork() {
        light.turnOn();
        fan.turnOn();
        door.unlock();

        facade.goToWork();

        assertFalse(light.isOn());
        assertFalse(fan.isOn());
        assertTrue(door.isLocked());
        assertTrue(output.toString().contains("Main door: LOCKED"));
    }

    @Test
    @DisplayName("T5: goToSleep() tắt quạt, khóa cửa, bật đèn và hiện mức sáng")
    void goToSleep() {
        fan.turnOn();
        door.unlock();

        facade.goToSleep();

        assertFalse(fan.isOn());
        assertTrue(door.isLocked());
        assertTrue(light.isOn());
        assertTrue(output.toString().contains("Night light level: 30%"));
    }

    @Test
    @DisplayName("T5: mức sáng được đọc từ HomeConfiguration")
    void goToSleepUsesConfiguration() {
        HomeConfiguration.getInstance().setNightLightLevel(10);

        facade.goToSleep();

        assertTrue(output.toString().contains("Night light level: 10%"));
    }

    @Test
    @DisplayName("Client vẫn điều khiển riêng từng thiết bị được sau khi dùng facade")
    void devicesStillUsableDirectly() {
        facade.goToWork();
        light.turnOn();
        door.unlock();

        assertTrue(light.isOn());
        assertFalse(door.isLocked());
    }

    @Test
    @DisplayName("Không cho truyền thiết bị null vào facade")
    void rejectsNullDevices() {
        assertThrows(NullPointerException.class, () -> new SmartHomeFacade(null, fan, door));
        assertThrows(NullPointerException.class, () -> new SmartHomeFacade(light, null, door));
        assertThrows(NullPointerException.class, () -> new SmartHomeFacade(light, fan, null));
    }
}
