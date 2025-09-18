package solidprinciple.Strategy;

public class PessangerVehicles extends Vehicles{
    PessangerVehicles(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}
