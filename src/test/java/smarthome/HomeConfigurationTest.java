/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Kiểm thử Singleton: T3 (mọi lần lấy đều là cùng một đối tượng).
 */
class HomeConfigurationTest {

    // Singleton giữ trạng thái giữa các test nên phải trả về giá trị mặc định sau mỗi test
    @AfterEach
    void resetConfiguration() {
        HomeConfiguration config = HomeConfiguration.getInstance();
        config.setHomeName("My Home");
        config.setNightLightLevel(30);
    }

    @Test
    @DisplayName("T3: lấy getInstance() hai lần trả về cùng một đối tượng")
    void returnsSameInstance() {
        HomeConfiguration c1 = HomeConfiguration.getInstance();
        HomeConfiguration c2 = HomeConfiguration.getInstance();

        assertSame(c1, c2);
        if (c1 != c2) {
            throw new IllegalStateException("Singleton implementation is incorrect.");
        }
    }

    @Test
    @DisplayName("T3: thay đổi qua biến này thì biến kia cũng thấy")
    void sharesStateBetweenReferences() {
        HomeConfiguration c1 = HomeConfiguration.getInstance();
        HomeConfiguration c2 = HomeConfiguration.getInstance();

        c1.setNightLightLevel(50);

        assertEquals(50, c2.getNightLightLevel());
    }

    @Test
    @DisplayName("T3: nhiều luồng cùng gọi vẫn chỉ có một đối tượng")
    void sameInstanceAcrossThreads() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        try {
            List<Callable<HomeConfiguration>> tasks = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                tasks.add(HomeConfiguration::getInstance);
            }
            HomeConfiguration expected = HomeConfiguration.getInstance();
            for (Future<HomeConfiguration> f : pool.invokeAll(tasks)) {
                assertSame(expected, f.get());
            }
        } finally {
            pool.shutdown();
        }
    }

    @Test
    @DisplayName("Constructor là private và lớp là final")
    void cannotBeInstantiatedOrExtended() {
        Constructor<?>[] constructors = HomeConfiguration.class.getDeclaredConstructors();
        assertEquals(1, constructors.length);
        assertTrue(Modifier.isPrivate(constructors[0].getModifiers()));
        assertTrue(Modifier.isFinal(HomeConfiguration.class.getModifiers()));
    }

    @Test
    @DisplayName("Giá trị mặc định và kiểm tra mức sáng hợp lệ")
    void defaultValuesAndValidation() {
        HomeConfiguration config = HomeConfiguration.getInstance();

        assertEquals("My Home", config.getHomeName());
        assertEquals(30, config.getNightLightLevel());
        assertThrows(IllegalArgumentException.class, () -> config.setNightLightLevel(101));
        assertThrows(IllegalArgumentException.class, () -> config.setNightLightLevel(-1));
    }
}
