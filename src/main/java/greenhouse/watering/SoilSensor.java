package greenhouse.watering;

public interface SoilSensor {
    boolean isOnline();

    double moisturePercent(Zone zone);
}
