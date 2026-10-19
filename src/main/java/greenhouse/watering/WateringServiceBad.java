package greenhouse.watering;

/**
 * "BEFORE" commit: all variants in one class with switch / if-else.
 * Replace this file with the Strategy classes in the "AFTER" commit.
 */
public class WateringServiceBad {
    public enum Mode { SCHEDULED, MOISTURE, WEATHER, DRIP }

    private final SoilSensor sensor;
    private final WeatherProvider weather;

    public WateringServiceBad(SoilSensor sensor, WeatherProvider weather) {
        this.sensor = sensor;
        this.weather = weather;
    }

    public WateringPlan plan(Mode mode, Zone zone) {
        switch (mode) {
            case SCHEDULED:
                return WateringPlan.continuous(zone.plantType().baseLiters(), "fixed schedule");
            case MOISTURE: {
                double deficit = zone.targetMoisture() - sensor.moisturePercent(zone);
                if (deficit <= 0) return WateringPlan.skip("soil wet enough");
                return WateringPlan.continuous(deficit * 0.5, "below target");
            }
            case WEATHER: {
                if (weather.rainExpected()) return WateringPlan.skip("rain expected");
                double liters = zone.plantType().baseLiters();
                if (weather.temperatureCelsius() >= 30) liters *= 1.5;
                return WateringPlan.continuous(liters, "weather based");
            }
            case DRIP: {
                double liters = zone.plantType().baseLiters() * 0.8;
                int pulses = Math.max(1, (int) Math.ceil(liters / 0.5));
                return new WateringPlan(liters, pulses, 10, "drip");
            }
            default:
                throw new IllegalArgumentException("unknown mode " + mode);
        }
    }
}
