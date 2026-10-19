package greenhouse.watering;

public class WateringMain {
    public static void run() {
        Zone zone = new Zone("Tomato row A", PlantType.TOMATO, 60);
        SimulatedSoilSensor sensor = new SimulatedSoilSensor(42);
        SimulatedWeatherProvider weather = new SimulatedWeatherProvider(false, 22);
        WateringServiceBad service = new WateringServiceBad(sensor, weather);

        System.out.println("=== BEFORE: one class with switch, all modes ===");
        for (WateringServiceBad.Mode mode : WateringServiceBad.Mode.values()) {
            System.out.printf("%-12s -> %s%n", mode, service.plan(mode, zone));
        }
    }
}