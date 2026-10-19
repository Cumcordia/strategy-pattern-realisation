package greenhouse.climate;

import java.time.LocalTime;
import java.util.List;

public class ClimateMain {
    public static void run() {
        Conditions hotDay = new Conditions(27, 20, 24);

        List<ClimateStrategy> all = List.of(
                new ThresholdClimate(),
                new ProportionalClimate(),
                new EnergySavingClimate(),
                new FrostProtectionClimate());

        System.out.println("=== 1. Same input, all strategies ===");
        for (ClimateStrategy s : all) {
            System.out.printf("%-16s -> %s%n", s.name(), s.decide(hotDay));
        }

        System.out.println("\n=== 2. Manual choice ===");
        ClimateController controller = new ClimateController(all.get(0));
        System.out.printf("%-16s -> %s%n", controller.currentStrategyName(), controller.adjust(hotDay));

        System.out.println("\n=== 3. Automatic choice by Selector ===");
        ClimateStrategySelector selector = new ClimateStrategySelector();
        show("hot afternoon", selector, controller, new Conditions(30, 26, 24), LocalTime.of(14, 0));
        show("calm day", selector, controller, new Conditions(25, 20, 24), LocalTime.of(11, 0));
        show("night", selector, controller, new Conditions(20, 10, 24), LocalTime.of(23, 30));
        show("frosty morning", selector, controller, new Conditions(12, -3, 24), LocalTime.of(7, 0));

        System.out.println("\n=== 4. Switching strategy at runtime ===");
        controller.setStrategy(new ProportionalClimate());
        System.out.printf("%-16s -> %s%n", controller.currentStrategyName(), controller.adjust(hotDay));
        controller.setStrategy(new EnergySavingClimate());
        System.out.printf("%-16s -> %s%n", controller.currentStrategyName(), controller.adjust(hotDay));
    }

    private static void show(String situation, ClimateStrategySelector selector,
                             ClimateController controller, Conditions c, LocalTime now) {
        controller.setStrategy(selector.select(c, now));
        System.out.printf("%-14s: %-16s -> %s%n",
                situation, controller.currentStrategyName(), controller.adjust(c));
    }
}
