package greenhouse.watering;

public enum PlantType {
    TOMATO(6.0),
    CUCUMBER(8.0),
    HERBS(2.0);

    private final double baseLiters;

    PlantType(double baseLiters) {
        this.baseLiters = baseLiters;
    }

    public double baseLiters() {
        return baseLiters;
    }
}
