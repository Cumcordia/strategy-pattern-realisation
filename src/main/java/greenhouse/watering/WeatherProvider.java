package greenhouse.watering;

public interface WeatherProvider {
    boolean rainExpected();

    double temperatureCelsius();
}
