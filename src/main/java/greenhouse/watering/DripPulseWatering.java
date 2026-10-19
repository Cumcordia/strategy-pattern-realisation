package greenhouse.watering;

public class DripPulseWatering implements WateringStrategy {
    static final double EFFICIENCY = 0.8;
    static final double LITERS_PER_PULSE = 0.5;
    static final int PAUSE_MINUTES = 10;

    @Override
    public WateringPlan plan(Zone zone) {
        double liters = zone.plantType().baseLiters() * EFFICIENCY;
        int pulses = Math.max(1, (int) Math.ceil(liters / LITERS_PER_PULSE));
        return new WateringPlan(liters, pulses, PAUSE_MINUTES, "drip in " + pulses + " pulses");
    }

    @Override
    public String name() {
        return "DripPulse";
    }
}
