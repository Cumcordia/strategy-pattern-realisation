package greenhouse.watering;

import java.util.Objects;

public class WeatherAwareWatering implements WateringStrategy {
    static final double HOT_THRESHOLD = 30.0;
    static final double HOT_MULTIPLIER = 1.5;

    private final WeatherProvider weather;

    public WeatherAwareWatering(WeatherProvider weather) {
        this.weather = Objects.requireNonNull(weather);
    }

    @Override
    public WateringPlan plan(Zone zone) {
        if (weather.rainExpected()) {
            return WateringPlan.skip("rain expected");
        }
        double liters = zone.plantType().baseLiters();
        if (weather.temperatureCelsius() >= HOT_THRESHOLD) {
            return WateringPlan.continuous(liters * HOT_MULTIPLIER, "hot weather, extra water");
        }
        return WateringPlan.continuous(liters, "normal weather");
    }

    @Override
    public String name() {
        return "WeatherAware";
    }
}
