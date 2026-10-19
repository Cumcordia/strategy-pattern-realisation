package greenhouse.watering;

public record Zone(String name, PlantType plantType, double targetMoisture) {
    public Zone {
        if (targetMoisture < 0 || targetMoisture > 100) {
            throw new IllegalArgumentException("targetMoisture must be 0..100");
        }
    }
}
