package solidprinciple.Strategy;

public class Vehicles {
    DriveStrategy driveStrategy;
    Vehicles(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }
    public void drive(){
        driveStrategy.drive();
    }
}
