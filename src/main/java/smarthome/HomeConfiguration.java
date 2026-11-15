/****************
 * Mã sinh viên: 202419055
 * Họ tên: Phí Việt Hiếu
 ****************/
package smarthome;

/**
 * Cấu hình chung của ngôi nhà, cài đặt theo mẫu Singleton (Holder idiom):
 * cả ứng dụng chỉ có một đối tượng, lấy qua {@link #getInstance()}.
 */
public final class HomeConfiguration {

    /** Tên ngôi nhà. */
    private String homeName = "My Home";

    /** Mức sáng đèn ngủ ban đêm, tính theo phần trăm (0-100). */
    private int nightLightLevel = 30;

    /** Constructor private để bên ngoài không tạo thêm được đối tượng. */
    private HomeConfiguration() {
    }

    // JVM chỉ nạp lớp Holder khi getInstance() được gọi lần đầu,
    // và việc nạp lớp là thread-safe nên không cần synchronized.
    private static class Holder {
        static final HomeConfiguration INSTANCE = new HomeConfiguration();
    }

    /**
     * Lấy đối tượng cấu hình duy nhất.
     *
     * @return thể hiện duy nhất của {@code HomeConfiguration}
     */
    public static HomeConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    /** @return tên ngôi nhà */
    public String getHomeName() {
        return homeName;
    }

    /** @param homeName tên mới của ngôi nhà */
    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    /** @return mức sáng đèn ngủ ban đêm (phần trăm) */
    public int getNightLightLevel() {
        return nightLightLevel;
    }

    /**
     * Đặt mức sáng đèn ngủ ban đêm.
     *
     * @param nightLightLevel mức sáng từ 0 đến 100
     * @throws IllegalArgumentException nếu mức sáng nằm ngoài khoảng 0-100
     */
    public void setNightLightLevel(int nightLightLevel) {
        if (nightLightLevel < 0 || nightLightLevel > 100) {
            throw new IllegalArgumentException("Night light level must be between 0 and 100");
        }
        this.nightLightLevel = nightLightLevel;
    }
}
