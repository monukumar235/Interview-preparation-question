package machinecoding.practise;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    public enum VehicleType{
        CAR,BIKE;
    }
    public static class Vehicle{
        private final String registrationNumber;
        private final VehicleType type;
        Vehicle(String registrationNumber,VehicleType type){
            this.registrationNumber=registrationNumber;
            this.type=type;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public VehicleType getType() {
            return type;
        }
    }
    public static class Bike extends Vehicle{

        Bike(String  registrationNumber) {
            super(registrationNumber, VehicleType.BIKE);
        }
    }
    public static class Car extends Vehicle{
            Car(String  registrationNumber) {
            super(registrationNumber, VehicleType.CAR);
        }
    }
    public static class ParkingSlots{
        private int slotNumber;
        private VehicleType slotsType;
        private boolean isOccupied;
        private Vehicle parkedVehicles;

        public ParkingSlots(int slotNumber,VehicleType type){
            this.slotNumber=slotNumber;
            this.slotsType=type;
            this.isOccupied=false;
        }

        public boolean isAvailable(){
            return !isOccupied;
        }

        public boolean canFitVehicle(Vehicle vehicle){
            return !isOccupied && vehicle.getType().ordinal()<=slotsType.ordinal();
        }

        public void park(Vehicle vehicle){
            this.parkedVehicles =vehicle;
            this.isOccupied=true;
        }
        public void unpark(){
            this.parkedVehicles=null;
            this.isOccupied=false;
        }

        public int getSlotNumber() {
            return slotNumber;
        }

        public VehicleType getSlotsType() {
            return slotsType;
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public Vehicle getParkedVehicles() {
            return parkedVehicles;
        }
    }

    public static class ParkingFloor{
        private int floorNumber;
        private List<ParkingSlots> slots;
        ParkingFloor(int floorNumber,int noOfSlotsPerType){
            this.floorNumber=floorNumber;
            this.slots= new ArrayList<>();

            int slotNums=0;
            for(int i=0;i<noOfSlotsPerType;i++){
                slots.add(new ParkingSlots(slotNums++,VehicleType.CAR));
            }
            for(int i=0;i<noOfSlotsPerType;i++){
                slots.add(new ParkingSlots(slotNums++,VehicleType.BIKE));
            }
        }
        public ParkingSlots getAvailableSot(Vehicle vehicle){
            for (ParkingSlots slot : slots){
                if(slot.canFitVehicle(vehicle)){
                    return slot;
                }
            }
            return null;
        }

        public int getFloorNumber() {
            return floorNumber;
        }

        public List<ParkingSlots> getSlots() {
            return slots;
        }
    }

    public static class ParkingLot{
        private final List<ParkingFloor> parkingFloors;

        ParkingLot(int floorNumber,int noOfSlotPerFloor){
            this.parkingFloors = new ArrayList<>();
            for (int i=0;i<floorNumber;i++){
                parkingFloors.add(new ParkingFloor(i+1,noOfSlotPerFloor));
            }
        }

        public boolean parkVehicle(Vehicle vehicle){
            for(ParkingFloor floor : parkingFloors){
                ParkingSlots slot = floor.getAvailableSot(vehicle);
                if(slot!=null){
                    slot.park(vehicle);
                    System.out.println("Vehicle Parked "+ slot.getParkedVehicles().getRegistrationNumber() + "At Floor "+ floor.getFloorNumber()+ " slot number" + slot.getSlotNumber());
                    return true;
                }
            }
            System.out.println("No Slot Available for Vehicle" + vehicle.getRegistrationNumber());
            return false;
        }

        public boolean unParkVehicle(String registrationNumber){
            for (ParkingFloor floor : parkingFloors){
                List<ParkingSlots> slots = floor.getSlots();
                for (ParkingSlots slot : slots){
                    if(slot.getParkedVehicles().getRegistrationNumber().equals(registrationNumber)){
                        slot.unpark();
                        System.out.println("Vehicle UnParked " + registrationNumber + "At floor " + floor.getFloorNumber() + "slot " + slot.getSlotNumber());
                        return true;
                    }
                }
            }
            System.out.println("Vehicle with " + registrationNumber + "not found");
            return false;
        }

        public void showStatus(){
            for (ParkingFloor floor : parkingFloors){
                System.out.println("Floor " + floor.getFloorNumber() + ":");
                for(ParkingSlots slot : floor.getSlots()) {
                    String status = slot.isAvailable() ? "Empty" : slot.getParkedVehicles().getRegistrationNumber();
                    System.out.println("Status " + "[ " + slot.getSlotsType() + "] " + "slots: " + slot.getSlotNumber() + " " + status);
                }
            }
        }
    }
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2,2);
        Vehicle bike = new Bike("KA-01-5678kJ");
        Vehicle car = new Car("JH-01-7896BW");

        parkingLot.parkVehicle(bike);
        parkingLot.parkVehicle(car);

        parkingLot.showStatus();
    }
}
