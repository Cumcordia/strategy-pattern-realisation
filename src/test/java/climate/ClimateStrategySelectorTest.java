package climate;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import greenhouse.climate.*;

class ClimateStrategySelectorTest {
    private final ClimateStrategySelector selector = new ClimateStrategySelector();
    private final LocalTime noon = LocalTime.of(12, 0);

    @Test
    void rule1_frostOutsideSelectsFrostProtection() {
        assertInstanceOf(FrostProtectionClimate.class,
                selector.select(new Conditions(20, 1, 24), noon));
    }

    @Test
    void rule1_frostBeatsNight() {
        assertInstanceOf(FrostProtectionClimate.class,
                selector.select(new Conditions(20, 1, 24), LocalTime.of(23, 0)));
    }

    @Test
    void rule2_nightSelectsEnergySaving() {
        assertInstanceOf(EnergySavingClimate.class,
                selector.select(new Conditions(22, 10, 24), LocalTime.of(23, 0)));
    }

    @Test
    void rule2_nightStartsAt22() {
        assertInstanceOf(EnergySavingClimate.class,
                selector.select(new Conditions(22, 10, 24), LocalTime.of(22, 0)));
    }

    @Test
    void rule2_nightEndsAt6() {
        assertInstanceOf(ThresholdClimate.class,
                selector.select(new Conditions(22, 10, 24), LocalTime.of(6, 0)));
    }

    @Test
    void rule3_bigDeviationSelectsProportional() {
        assertInstanceOf(ProportionalClimate.class,
                selector.select(new Conditions(30, 15, 24), noon));
    }

    @Test
    void rule3_deviationExactlyAtLimitCounts() {
        assertInstanceOf(ProportionalClimate.class,
                selector.select(new Conditions(28, 15, 24), noon));
    }

    @Test
    void rule4_defaultIsThreshold() {
        assertInstanceOf(ThresholdClimate.class,
                selector.select(new Conditions(25, 15, 24), noon));
    }
}
