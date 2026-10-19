package greenhouse.watering;

public record WateringPlan(double liters, int pulses, int pauseMinutes, String reason) {
    public WateringPlan {
        if (liters < 0) throw new IllegalArgumentException("liters must be >= 0");
        if (pulses < 0) throw new IllegalArgumentException("pulses must be >= 0");
        if (pauseMinutes < 0) throw new IllegalArgumentException("pauseMinutes must be >= 0");
    }

    public static WateringPlan continuous(double liters, String reason) {
        return new WateringPlan(liters, 1, 0, reason);
    }

    public static WateringPlan skip(String reason) {
        return new WateringPlan(0, 0, 0, reason);
    }

    public boolean isSkipped() {
        return liters == 0;
    }
}
