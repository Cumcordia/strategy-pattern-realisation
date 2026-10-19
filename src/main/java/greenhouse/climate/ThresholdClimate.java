package greenhouse.climate;

public class ThresholdClimate implements ClimateStrategy{
    static final double DEAD_BAND = 2.0;

    @Override 
    public ClimateAction decide(Conditions c){
        if(c.insideTemp() > c.outsideTemp() + DEAD_BAND){
            return ClimateAction.vent(100, "too hot, vents fully open");
        }
        if(c.insideTemp() < c.outsideTemp() + DEAD_BAND){
            return ClimateAction.heat(100, "too cold, jeater at full power");
        }
        return ClimateAction.idle("inside the dead band");
    }

    @Override 
    public String name(){
        return "Threshold";
    }
}
