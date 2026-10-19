package greenhouse.watering;

public interface WateringStrategy {
    WateringPlan plan(Zone zone);

    String name();
}
