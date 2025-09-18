package solidprinciple.decoratorpattern.car;

public class PowerSterring  extends AddedExtraFeaturesDecorator{

    BaseCar baseCar;

    PowerSterring(BaseCar baseCar){
        this.baseCar = baseCar;
    }
    @Override
    public int cost() {
        return baseCar.cost() + 10000;
    }
}
