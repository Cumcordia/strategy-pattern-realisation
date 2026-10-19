package greenhouse.climate;

public class FrostProtectionClimate implements ClimateStrategy{
    static final double OUTSIDE_FROST_LIMIT = 2.0;
    static final double INSIDE_FROST_LIMIT = 5.0;
    
    @Override 
    public ClimateAction decide(Conditions c){
        if(c.outsideTemp() <= OUTSIDE_FROST_LIMIT || c.insideTemp() <= INSIDE_FROST_LIMIT){
            return ClimateAction.heat(100, "frost danger, selaed and heating");
        }
        return ClimateAction.idle("no frost danger");
    }

    @Override 
    public String name(){
        return "FrostProtection";
    }
}
