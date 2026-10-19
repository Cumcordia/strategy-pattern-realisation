package greenhouse.climate;

import java.util.Objects;

public class ClimateController {
    private ClimateStrategy strategy;

    public ClimateController(ClimateStrategy strategy){
        this.strategy = Objects.requireNonNull(strategy);
    }

    public void setStrategy(ClimateStrategy strategy){
        this.strategy = Objects.requireNonNull(strategy);
    }

    public ClimateAction adjust(Conditions conditions){
        return strategy.decide(conditions);
    }

    public String currentStrategyName(){
        return strategy.name();
    }
}
