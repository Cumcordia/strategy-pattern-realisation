// package watering;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.Test;
// import greenhouse.watering.*;

// class WateringStrategiesTest {
//     private final Zone tomato = new Zone("A", PlantType.TOMATO, 60);

//     @Test
//     void scheduledUsesBaseLitersOfPlantType() {
//         WateringPlan plan = new ScheduledWatering().plan(tomato);
//         assertEquals(6.0, plan.liters());
//         assertEquals(1, plan.pulses());
//     }

//     @Test
//     void scheduledGivesDifferentAmountForDifferentPlants() {
//         Zone herbs = new Zone("B", PlantType.HERBS, 60);
//         assertEquals(2.0, new ScheduledWatering().plan(herbs).liters());
//     }

//     @Test
//     void moistureWatersTheDeficit() {
//         var strategy = new MoistureThresholdWatering(new FakeSoilSensor(40, true));
//         WateringPlan plan = strategy.plan(tomato);   // deficit 20% * 0.5 L
//         assertEquals(10.0, plan.liters(), 1e-9);
//     }

//     @Test
//     void moistureSkipsWhenSoilIsWetEnough() {
//         var strategy = new MoistureThresholdWatering(new FakeSoilSensor(75, true));
//         assertTrue(strategy.plan(tomato).isSkipped());
//     }

//     @Test
//     void weatherAwareSkipsOnRain() {
//         var strategy = new WeatherAwareWatering(new FakeWeatherProvider(true, 20));
//         assertTrue(strategy.plan(tomato).isSkipped());
//     }

//     @Test
//     void weatherAwareGivesExtraWaterWhenHot() {
//         var strategy = new WeatherAwareWatering(new FakeWeatherProvider(false, 35));
//         assertEquals(9.0, strategy.plan(tomato).liters(), 1e-9);
//     }

//     @Test
//     void weatherAwareGivesBaseWaterOnNormalDay() {
//         var strategy = new WeatherAwareWatering(new FakeWeatherProvider(false, 22));
//         assertEquals(6.0, strategy.plan(tomato).liters(), 1e-9);
//     }

//     @Test
//     void dripSplitsReducedAmountIntoPulses() {
//         WateringPlan plan = new DripPulseWatering().plan(tomato);
//         assertEquals(4.8, plan.liters(), 1e-9);
//         assertEquals(10, plan.pulses());
//         assertEquals(10, plan.pauseMinutes());
//     }

//     @Test
//     void controllerDelegatesAndCanSwitchStrategy() {
//         WateringController controller = new WateringController(new ScheduledWatering());
//         assertEquals(6.0, controller.water(tomato).liters());

//         controller.setStrategy(new DripPulseWatering());
//         assertEquals("DripPulse", controller.currentStrategyName());
//         assertEquals(4.8, controller.water(tomato).liters(), 1e-9);
//     }

//     @Test
//     void controllerRejectsNullStrategy() {
//         assertThrows(NullPointerException.class, () -> new WateringController(null));
//     }
// }
