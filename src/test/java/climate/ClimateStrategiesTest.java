package climate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import greenhouse.climate.*;

class ClimateStrategiesTest {

    @Test
    void thresholdOpensVentsWhenTooHot() {
        ClimateAction a = new ThresholdClimate().decide(new Conditions(30, 20, 24));
        assertEquals(100, a.ventOpenPercent());
        assertEquals(0, a.heaterPowerPercent());
    }

    @Test
    void thresholdHeatsWhenTooCold() {
        ClimateAction a = new ThresholdClimate().decide(new Conditions(18, 10, 24));
        assertEquals(100, a.heaterPowerPercent());
        assertEquals(0, a.ventOpenPercent());
    }

    @Test
    void thresholdDoesNothingInsideDeadBand() {
        ClimateAction a = new ThresholdClimate().decide(new Conditions(25, 20, 24));
        assertEquals(0, a.ventOpenPercent());
        assertEquals(0, a.heaterPowerPercent());
    }

    // ---- ProportionalClimate ----
    @Test
    void proportionalVentsInProportionToError() {
        ClimateAction a = new ProportionalClimate().decide(new Conditions(27, 20, 24));
        assertEquals(60, a.ventOpenPercent());
    }

    @Test
    void proportionalHeatsInProportionToError() {
        ClimateAction a = new ProportionalClimate().decide(new Conditions(22, 10, 24));
        assertEquals(40, a.heaterPowerPercent());
    }

    @Test
    void proportionalIsCappedAtHundredPercent() {
        ClimateAction a = new ProportionalClimate().decide(new Conditions(40, 20, 24));
        assertEquals(100, a.ventOpenPercent());
    }

    @Test
    void energySavingUsesFreeCoolingWhenOutsideIsCooler() {
        ClimateAction a = new EnergySavingClimate().decide(new Conditions(30, 20, 24));
        assertEquals(100, a.ventOpenPercent());
    }

    @Test
    void energySavingKeepsVentsClosedWhenOutsideIsWarmer() {
        ClimateAction a = new EnergySavingClimate().decide(new Conditions(30, 35, 24));
        assertEquals(0, a.ventOpenPercent());
        assertEquals(0, a.heaterPowerPercent());
    }

    @Test
    void energySavingHeatsAtHalfPower() {
        ClimateAction a = new EnergySavingClimate().decide(new Conditions(18, 10, 24));
        assertEquals(50, a.heaterPowerPercent());
    }

    @Test
    void frostProtectionHeatsWhenOutsideIsFreezing() {
        ClimateAction a = new FrostProtectionClimate().decide(new Conditions(20, 1, 24));
        assertEquals(100, a.heaterPowerPercent());
        assertEquals(0, a.ventOpenPercent());
    }

    @Test
    void frostProtectionHeatsWhenInsideIsNearFreezing() {
        ClimateAction a = new FrostProtectionClimate().decide(new Conditions(4, 10, 24));
        assertEquals(100, a.heaterPowerPercent());
    }

    @Test
    void frostProtectionDoesNothingWithoutDanger() {
        ClimateAction a = new FrostProtectionClimate().decide(new Conditions(20, 10, 24));
        assertEquals(0, a.heaterPowerPercent());
    }

    @Test
    void controllerDelegatesAndCanSwitchStrategy() {
        Conditions hot = new Conditions(27, 20, 24);
        ClimateController controller = new ClimateController(new ThresholdClimate());
        assertEquals(100, controller.adjust(hot).ventOpenPercent());

        controller.setStrategy(new ProportionalClimate());
        assertEquals("Proportional", controller.currentStrategyName());
        assertEquals(60, controller.adjust(hot).ventOpenPercent());
    }

    @Test
    void controllerRejectsNullStrategy() {
        assertThrows(NullPointerException.class, () -> new ClimateController(null));
    }
}
