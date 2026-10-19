package watering;

import greenhouse.watering.*;

class FakeSoilSensor implements SoilSensor {
    private final double moisture;
    private final boolean online;

    FakeSoilSensor(double moisture, boolean online) {
        this.moisture = moisture;
        this.online = online;
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public double moisturePercent(Zone zone) {
        return moisture;
    }
}
