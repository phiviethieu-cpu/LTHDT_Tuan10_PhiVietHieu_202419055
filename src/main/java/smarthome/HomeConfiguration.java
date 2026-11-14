package smarthome;

public final class HomeConfiguration {

    private String homeName = "My Home";
    private int nightLightLevel = 30;

    private HomeConfiguration() {
    }

    // JVM chỉ nạp lớp Holder khi getInstance() được gọi lần đầu,
    // và việc nạp lớp là thread-safe nên không cần synchronized.
    private static class Holder {
        static final HomeConfiguration INSTANCE = new HomeConfiguration();
    }

    public static HomeConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getNightLightLevel() {
        return nightLightLevel;
    }

    public void setNightLightLevel(int nightLightLevel) {
        if (nightLightLevel < 0 || nightLightLevel > 100) {
            throw new IllegalArgumentException("Night light level must be between 0 and 100");
        }
        this.nightLightLevel = nightLightLevel;
    }
}
