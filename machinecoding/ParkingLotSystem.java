package machinecoding;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    public enum VehicleType{
        BIKE,
        CAR,
        TRUCK
    }
    public static class Vehicle{
        private String number;
        private VehicleType type;
        Vehicle(String number,VehicleType type){
            this.number = number;
            this.type = type;
        }

        public String getNumber() {
            return number;
        }

        public VehicleType getType() {
            return type;
        }
    }
    public static class Bike extends Vehicle{

        Bike(String number) {
            super(number, VehicleType.BIKE);
        }
    }
    public static class Car extends Vehicle{

        Car(String number) {
            super(number, VehicleType.CAR);
        }
    }
    public static class Truck extends Vehicle{

        Truck(String number) {
            super(number, VehicleType.TRUCK);
        }
    }

    public static class ParkingSlot{
        private int slotNumber;
        private VehicleType slotsType;
        private boolean isOccupied;
        private Vehicle parkedVehicle;

        ParkingSlot(int slotNumber,VehicleType vehicleType){
            this.slotsType=vehicleType;
            this.slotNumber=slotNumber;
            this.isOccupied =false;
        }

        public boolean isAvailable(){
            return !isOccupied;
        }
        public boolean canFitVehicle(Vehicle vehicle){
            return !isOccupied && vehicle.getType().ordinal()<=slotsType.ordinal();
        }

        public void park(Vehicle vehicle){
            this.parkedVehicle =vehicle;
            this.isOccupied=true;
        }
        public void unPark(){
            this.parkedVehicle = null;
            this.isOccupied =false;
        }

        public int getSlotNumber(){
            return slotNumber;
        }
        public Vehicle getParkedVehicle(){
            return parkedVehicle;
        }

    }
    public static class ParkingFloor{
        private int floor;
        private List<ParkingSlot> slots;


        ParkingFloor(int floor,int numberOFSlotPerType){
            this.floor =floor;
            this.slots = new ArrayList<>();
            int slotNum=1;
            for(int i=0;i<numberOFSlotPerType;i++){
                slots.add(new ParkingSlot(slotNum++,VehicleType.BIKE));
            }
            for (int i=0;i<numberOFSlotPerType;i++){
                slots.add(new ParkingSlot(slotNum++,VehicleType.CAR));
            }
            for (int i=0;i<numberOFSlotPerType;i++){
                slots.add(new ParkingSlot(slotNum++,VehicleType.TRUCK));
            }
        }
        public ParkingSlot getAvailableSlots(Vehicle vehicle){
            for (ParkingSlot slot : slots){
                if(slot.canFitVehicle(vehicle)){
                    return slot;
                }
            }
            return null;
        }
        public List<ParkingSlot> getSlots(){
            return slots;
        }

        public int getFloor(){
            return floor;
        }
    }

    public static class ParkingLot{
        private List<ParkingFloor> parkingFloors;

        public ParkingLot(int floorNumber,int numberOfSlotPerType){
            this.parkingFloors = new ArrayList<>();
            for(int i=0;i<floorNumber;i++){
                parkingFloors.add(new ParkingFloor(i+1,numberOfSlotPerType));
            }
        }

        public boolean parkVehicle(Vehicle vehicle){
            for(ParkingFloor parkingFloor : parkingFloors){
                ParkingSlot slot = parkingFloor.getAvailableSlots(vehicle);
                if(slot!=null){
                    slot.park(vehicle);
                    System.out.println("Parked vehicle " + vehicle.getNumber() + " at floor" + parkingFloor.getFloor() + "slot " + slot.getSlotNumber());
                    return true;
                }
            }
            System.out.println("No Available Slot for vehicle" + vehicle.getType());
            return false;
        }
        public boolean unPark(String number){
            for (ParkingFloor floor : parkingFloors){
                List<ParkingSlot> slots = floor.getSlots();
                for(ParkingSlot slot : slots){
                    if(!slot.isAvailable() && slot.getParkedVehicle().getNumber().equals(number))
                    {
                        slot.unPark();
                        System.out.println("UnParked Vehicle " + number + "from floor " + floor.getFloor() + "slots "+slot.getSlotNumber());
                        return true;
                    }
                }
            }
            System.out.println("Vehicle " + number+ "not found");
            return false;
        }
        public void showStatus(){
            for(ParkingFloor floor : parkingFloors){
                System.out.println("Floor "+floor.getFloor() + " :");
                for (ParkingSlot slot : floor.getSlots()){
                    String status = slot.isAvailable() ? "Empty" : slot.getParkedVehicle().getNumber();
                    System.out.println("Slots " + slot.getSlotNumber() + "[ "+slot.slotsType +"]: " + status);
                }
            }
        }
    }
    public static void main(String[] args) {

        Vehicle bike = new Bike("bike123");
        Vehicle bike1 = new Bike("bike132");
        Vehicle car = new Car("car123");
        Vehicle car1 = new Car("car132");
        Vehicle truck = new Truck("truck123");
        Vehicle truck1 = new Truck("truck132");

        ParkingLot parkingLot = new ParkingLot(2,2);

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(bike);
        parkingLot.parkVehicle(truck);

        parkingLot.showStatus();


        parkingLot.unPark("truck123");

//        parkingLot.showStatus();


    }
}
