package solidprinciple.decoratorpattern.car;

public class SeatCover extends AddedExtraFeaturesDecorator{

    BaseCar car;
    SeatCover(BaseCar baseCar){
        this.car=baseCar;
    }
    @Override
    public int cost() {
        return car.cost() + 5000;
    }
}
