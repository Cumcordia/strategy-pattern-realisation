package greenhouse.climate;

public record ClimateAction(int ventOpenPercent, int heaterPowerPercent, String reason) {
    public ClimateAction{
        if(ventOpenPercent < 0 || ventOpenPercent > 100){
            throw new IllegalArgumentException("ventOpenPercent must be 0...100");
        }
        if(heaterPowerPercent < 0 || heaterPowerPercent > 100){
            throw new IllegalArgumentException("heaterPowerPercent must be 0...100");
        }
    }

    public static ClimateAction idle(String reason){
        return new ClimateAction(0, 0, reason);
    }

    public static ClimateAction vent(int percent, String reason) {
        return new ClimateAction(percent, 0, reason);
    }

    public static ClimateAction heat(int percent, String reason) {
        return new ClimateAction(0, percent, reason);
    }
}
