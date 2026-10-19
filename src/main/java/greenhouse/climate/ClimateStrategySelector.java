package greenhouse.climate;

import java.time.LocalTime;

public class ClimateStrategySelector {
    static final double FROST_OUTSIDE = 2.0;
    static final double BIG_DEVIATION = 4.0;
    static final LocalTime NIGHT_START = LocalTime.of(22, 0);
    static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    private final ClimateStrategy threshold = new ThresholdClimate();
    private final ClimateStrategy proportional = new ProportionalClimate();
    private final ClimateStrategy energySaving = new EnergySavingClimate();
    private final ClimateStrategy frost = new FrostProtectionClimate();

    public ClimateStrategy select(Conditions c, LocalTime now){
        if(c.outsideTemp() <= FROST_OUTSIDE){
            return frost;
        }
        if(isNight(now)){
            return energySaving;
        }
        if(Math.abs(c.insideTemp() - c.targetTemp()) >= BIG_DEVIATION){
            return proportional;
        }
        return threshold;
    }

    private boolean isNight(LocalTime now){
        return !now.isBefore(NIGHT_START) || now.isBefore(NIGHT_END);
    }
}
