package solidprinciple.Strategy;

public class Main {
    public static void main(String[] args) {
        PessangerVehicles pessangerVehicles = new PessangerVehicles(new SpecialDriveStrategy());
        pessangerVehicles.drive();
    }
}
