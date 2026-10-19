package greenhouse.watering;

import java.util.List;

public class WateringMain {
    public static void run() {
        Zone zone = new Zone("Tomato row A", PlantType.TOMATO, 60);
        SimulatedSoilSensor sensor = new SimulatedSoilSensor(42);
        SimulatedWeatherProvider weather = new SimulatedWeatherProvider(false, 22);

        List<WateringStrategy> all = List.of(
                new ScheduledWatering(),
                new MoistureThresholdWatering(sensor),
                new WeatherAwareWatering(weather),
                new DripPulseWatering());

        System.out.println("=== 1. Same input, all strategies ===");
        for (WateringStrategy s : all) {
            System.out.printf("%-18s -> %s%n", s.name(), s.plan(zone));
        }

        System.out.println("\n=== 2. Manual choice ===");
        WateringController controller = new WateringController(all.get(0));
        System.out.printf("%-18s -> %s%n", controller.currentStrategyName(), controller.water(zone));

        System.out.println("\n=== 3. Automatic choice by Selector ===");
        WateringStrategySelector selector = new WateringStrategySelector(sensor, weather);
        weather.set(false, 22);
        show("normal day", selector, controller, zone);
        weather.set(true, 18);
        show("rain forecast", selector, controller, zone);
        weather.set(false, 34);
        show("hot day", selector, controller, zone);

        System.out.println("\n=== 4. Switching strategy at runtime ===");
        controller.setStrategy(new DripPulseWatering());
        System.out.printf("%-18s -> %s%n", controller.currentStrategyName(), controller.water(zone));
        controller.setStrategy(new ScheduledWatering());
        System.out.printf("%-18s -> %s%n", controller.currentStrategyName(), controller.water(zone));
    }

    private static void show(String situation, WateringStrategySelector selector,
                             WateringController controller, Zone zone) {
        controller.setStrategy(selector.select());
        System.out.printf("%-14s: %-18s -> %s%n",
                situation, controller.currentStrategyName(), controller.water(zone));
    }
}
