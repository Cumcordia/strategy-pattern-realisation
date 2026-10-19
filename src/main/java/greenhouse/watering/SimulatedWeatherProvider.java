package greenhouse.watering;

public class SimulatedWeatherProvider implements WeatherProvider {
    private boolean rain;
    private double temperature;

    public SimulatedWeatherProvider(boolean rain, double temperature) {
        this.rain = rain;
        this.temperature = temperature;
    }

    public void set(boolean rain, double temperature) {
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
