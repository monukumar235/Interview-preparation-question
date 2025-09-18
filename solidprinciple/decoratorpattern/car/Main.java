package solidprinciple.decoratorpattern.car;

public class Main {
    public static void main(String[] args) {
        AltoCar baseCar = new AltoCar();

        SeatCover addedFirstFeatures = new SeatCover(baseCar);

        PowerSterring powerSterring = new PowerSterring(addedFirstFeatures);

        AC completeCarWithAllFeatures = new AC(powerSterring);

        System.out.println("Total Prices "+completeCarWithAllFeatures.cost());
    }
}
