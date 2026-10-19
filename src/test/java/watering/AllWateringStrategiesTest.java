// package watering;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.stream.Stream;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
// import greenhouse.watering.*;

// class AllWateringStrategiesTest {

//     static Stream<Arguments> strategies() {
//         return Stream.of(
//                 Arguments.of(new ScheduledWatering()),
//                 Arguments.of(new MoistureThresholdWatering(new FakeSoilSensor(30, true))),
//                 Arguments.of(new MoistureThresholdWatering(new FakeSoilSensor(95, true))),
//                 Arguments.of(new WeatherAwareWatering(new FakeWeatherProvider(false, 25))),
//                 Arguments.of(new WeatherAwareWatering(new FakeWeatherProvider(true, 25))),
//                 Arguments.of(new DripPulseWatering()));
//     }

//     @ParameterizedTest
//     @MethodSource("strategies")
//     void litersAreNeverNegative(WateringStrategy strategy) {
//         for (PlantType type : PlantType.values()) {
//             WateringPlan plan = strategy.plan(new Zone("Z", type, 60));
//             assertTrue(plan.liters() >= 0, strategy.name() + " gave negative liters");
//         }
//     }

//     @ParameterizedTest
//     @MethodSource("strategies")
//     void planAlwaysHasReasonAndName(WateringStrategy strategy) {
//         WateringPlan plan = strategy.plan(new Zone("Z", PlantType.TOMATO, 60));
//         assertNotNull(plan.reason());
//         assertNotNull(strategy.name());
//     }
// }
