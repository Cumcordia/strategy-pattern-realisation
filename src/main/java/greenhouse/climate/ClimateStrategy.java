package greenhouse.climate;

public interface ClimateStrategy {
    ClimateAction decide(Conditions c);

    String name();
}
