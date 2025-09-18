package solidprinciple.decoratorpattern.pizza;

public class VegOverLoaded extends BasePizza{
    @Override
    public int cost() {
        return 100;
    }
}
