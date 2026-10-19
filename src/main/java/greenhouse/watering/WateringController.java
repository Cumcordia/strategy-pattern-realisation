package greenhouse.watering;

import java.util.Objects;

public class WateringController {
    private WateringStrategy strategy;

    public WateringController(WateringStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public void setStrategy(WateringStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public WateringPlan water(Zone zone) {
        return strategy.plan(zone);
    }

    public String currentStrategyName() {
        return strategy.name();
    }
}
