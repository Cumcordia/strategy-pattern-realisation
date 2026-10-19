package greenhouse.watering;

import java.util.Objects;

public class MoistureThresholdWatering implements WateringStrategy {
    static final double LITERS_PER_PERCENT = 0.5;

    private final SoilSensor sensor;

    public MoistureThresholdWatering(SoilSensor sensor) {
        this.sensor = Objects.requireNonNull(sensor);
    }

    @Override
    public WateringPlan plan(Zone zone) {
        double current = sensor.moisturePercent(zone);
        double deficit = zone.targetMoisture() - current;
        if (deficit <= 0) {
            return WateringPlan.skip("soil already at " + current + "%");
        }
        return WateringPlan.continuous(deficit * LITERS_PER_PERCENT,
                "soil " + current + "% below target " + zone.targetMoisture() + "%");
    }

    @Override
    public String name() {
        return "MoistureThreshold";
    }
}
