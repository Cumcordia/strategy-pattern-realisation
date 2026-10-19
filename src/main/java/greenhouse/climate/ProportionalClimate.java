package greenhouse.climate;

public class ProportionalClimate implements ClimateStrategy{
    static final double PERCENT_PER_DEGREE = 20.0;

    @Override 
    public ClimateAction decide(Conditions c){
        double error = c.insideTemp() - c.outsideTemp();
        int percent = (int) Math.min(100, Math.abs(error) * PERCENT_PER_DEGREE);
        if(error > 0){
            return ClimateAction.vent(percent, "proportional cooling");
        }
        if(error < 0){
            return ClimateAction.heat(percent, "proportional heating");
        }
        return ClimateAction.idle("on target");
    }

    @Override 
    public String name(){
        return "Proportional";
    }
}