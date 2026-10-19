package watering;

import greenhouse.watering.*;

class FakeWeatherProvider implements WeatherProvider {
    private final boolean rain;
    private final double temperature;

    FakeWeatherProvider(boolean rain, double temperature) {
        this.rain = rain;
        this.temperature = temperature;
    }

    @Override
    public boolean rainExpected() {
        return rain;
    }

    @Override
    public double temperatureCelsius() {
        return temperature;
    }
}
