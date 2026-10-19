package greenhouse.watering;

public class ScheduledWatering implements WateringStrategy {
    @Override
    public WateringPlan plan(Zone zone) {
        double liters = zone.plantType().baseLiters();
        return WateringPlan.continuous(liters, "fixed schedule for " + zone.plantType());
    }

    @Override
    public String name() {
        return "Scheduled";
    }
}
