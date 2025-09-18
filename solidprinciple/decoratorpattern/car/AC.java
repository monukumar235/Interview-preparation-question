package solidprinciple.decoratorpattern.car;

public class AC extends AddedExtraFeaturesDecorator{

    BaseCar baseCar;

    AC(BaseCar baseCar){
        this.baseCar = baseCar;
    }
    @Override
    public int cost() {
        return baseCar.cost() + 20000;
    }
}
