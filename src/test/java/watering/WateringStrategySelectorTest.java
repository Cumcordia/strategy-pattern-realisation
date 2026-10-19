package watering;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import greenhouse.watering.*;

class WateringStrategySelectorTest {
    private WateringStrategy select(boolean online, boolean rain, double temp) {
        var selector = new WateringStrategySelector(
                new FakeSoilSensor(40, online), new FakeWeatherProvider(rain, temp));
        return selector.select();
    }

    @Test
    void rule1_offlineSensorFallsBackToSchedule() {
        assertInstanceOf(ScheduledWatering.class, select(false, false, 20));
    }

    @Test
    void rule1_offlineSensorWinsOverRain() {
        assertInstanceOf(ScheduledWatering.class, select(false, true, 20));
    }

    @Test
    void rule2_rainSelectsWeatherAware() {
        assertInstanceOf(WeatherAwareWatering.class, select(true, true, 20));
    }

    @Test
    void rule3_hotSelectsDrip() {
        assertInstanceOf(DripPulseWatering.class, select(true, false, 31));
    }

    @Test
    void rule3_exactlyAtThresholdCountsAsHot() {
        assertInstanceOf(DripPulseWatering.class, select(true, false, 30));
    }

    @Test
    void rule4_defaultIsMoistureThreshold() {
        assertInstanceOf(MoistureThresholdWatering.class, select(true, false, 22));
    }
}
