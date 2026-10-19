package greenhouse.watering;

import java.util.Random;

public class SimulatedSoilSensor implements SoilSensor {
    private final Random random;

    public SimulatedSoilSensor(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public double moisturePercent(Zone zone) {
        return 20 + random.nextDouble() * 50;
    }
}
