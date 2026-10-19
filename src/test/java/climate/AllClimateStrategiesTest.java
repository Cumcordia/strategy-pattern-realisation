package climate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import greenhouse.climate.*;

class AllClimateStrategiesTest {

    static Stream<Arguments> strategies() {
        return Stream.of(
                Arguments.of(new ThresholdClimate()),
                Arguments.of(new ProportionalClimate()),
                Arguments.of(new EnergySavingClimate()),
                Arguments.of(new FrostProtectionClimate()));
    }

    @ParameterizedTest
    @MethodSource("strategies")
    void neverHeatsAndVentsAtTheSameTime(ClimateStrategy strategy) {
        for (double inside = -5; inside <= 45; inside += 2.5) {
            for (double outside = -10; outside <= 40; outside += 5) {
                ClimateAction a = strategy.decide(new Conditions(inside, outside, 24));
                assertFalse(a.ventOpenPercent() > 0 && a.heaterPowerPercent() > 0,
                        strategy.name() + " heats and vents at " + inside + "/" + outside);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("strategies")
    void actionAlwaysHasReason(ClimateStrategy strategy) {
        ClimateAction a = strategy.decide(new Conditions(24, 15, 24));
        assertNotNull(a.reason());
    }
}
