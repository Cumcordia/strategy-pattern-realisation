package greenhouse.climate;

public class EnergySavingClimate implements ClimateStrategy {
    static final double TOLERANCE = 2.0;
    static final int ECO_HEATER_POWER = 50;

    @Override 
    public ClimateAction decide(Conditions c){
        if(c.insideTemp() > c.targetTemp() + TOLERANCE){
            if(c.outsideTemp() < c.insideTemp()){
                return ClimateAction.vent(100, "free cooling with outside air");
            }
            return ClimateAction.idle("outside is warmer, keep vents closed");
        }
        if(c.insideTemp() < c.targetTemp() - TOLERANCE){
            return ClimateAction.heat(ECO_HEATER_POWER, "eco heating");
        }
        return ClimateAction.idle("within TOLERANCE");
    }

    @Override 
    public String name(){
        return "Energysaving";
    }
}
