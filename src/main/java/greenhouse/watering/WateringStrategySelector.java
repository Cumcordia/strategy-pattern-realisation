package greenhouse.watering;

import java.util.Objects;

public class WateringStrategySelector {
    static final double HOT_THRESHOLD = 30.0;

    private final SoilSensor sensor;
    private final WeatherProvider weather;

    private final WateringStrategy scheduled = new ScheduledWatering();
    private final WateringStrategy moisture;
    private final WateringStrategy weatherAware;
    private final WateringStrategy drip = new DripPulseWatering();

    public WateringStrategySelector(SoilSensor sensor, WeatherProvider weather) {
        this.sensor = Objects.requireNonNull(sensor);
        this.weather = Objects.requireNonNull(weather);
        this.moisture = new MoistureThresholdWatering(sensor);
        this.weatherAware = new WeatherAwareWatering(weather);
    }

    public WateringStrategy select() {
        if (!sensor.isOnline()) {
            return scheduled;
        }
        if (weather.rainExpected()) {
            return weatherAware;
        }
        if (weather.temperatureCelsius() >= HOT_THRESHOLD) {
            return drip;
        }
        return moisture;
    }
}
