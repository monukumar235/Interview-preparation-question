package practise;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    public enum VehicleType{
        CAR,BIKE;
    }
    public  static class Vehicle
    {
        private String registrationNumber;
        private VehicleType type;
        Vehicle(String registrationNumber,VehicleType vehicleType){
            this.registrationNumber = registrationNumber;
            this.type=vehicleType;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public VehicleType getType() {
            return type;
        }
    }

    public static class Car extends Vehicle{
        Car(String registrationNumber) {
            super(registrationNumber,VehicleType.CAR);
        }
    }

    public static class Bike extends Vehicle{

        Bike(String registrationNumber) {
            super(registrationNumber,VehicleType.BIKE);
        }
    }

    public static class ParkingSlot{
        private int slotId;
        private VehicleType slotType;
        private boolean isOccupied;
        private Vehicle parkedvehicle;

        ParkingSlot(int slotId,VehicleType slotType){
            this.slotId=slotId;
            this.slotType=slotType;
            this.isOccupied=false;
        }
        public boolean canFitVehicle(Vehicle vehicle){
            return !isOccupied && vehicle.type.ordinal()<=slotType.ordinal();
        }
        public boolean isAvailable(){
            return !isOccupied;
        }
        public void park(Vehicle vehicle){
            this.parkedvehicle =vehicle;
            this.isOccupied =true;
        }
        public void unPark(){
            this.parkedvehicle = null;
            this.isOccupied = false;
        }

        public int getSlotId() {
            return slotId;
        }

        public VehicleType getSlotType() {
            return slotType;
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public Vehicle getParkedvehicle() {
            return parkedvehicle;
        }
    }

    public static class ParkingFloor{
        private int floor;
        private List<ParkingSlot> slots;
        ParkingFloor(int floor,int noOfSlotsPerType){
            this.floor = floor;
            this.slots = new ArrayList<>();

            int slotNum=0;
            for(int i=0;i<noOfSlotsPerType;i++){
                slots.add(new ParkingSlot(slotNum++,VehicleType.BIKE));
            }
            for (int i=0;i<noOfSlotsPerType;i++){
                slots.add(new ParkingSlot(slotNum++,VehicleType.CAR));
            }
        }
        public ParkingSlot getAvailableSlot(Vehicle vehicle){
            for (ParkingSlot parkingSlot : slots){
                if(parkingSlot.canFitVehicle(vehicle)){
                    return parkingSlot;
                }
            }
            return null;
        }

        public int getFloor() {
            return floor;
        }

        public List<ParkingSlot> getSlots() {
            return slots;
        }
    }
    public static class ParkingLotService
    {
        private List<ParkingFloor> parkingFloors;
        ParkingLotService(int numberOfFloor,int noOfSlotPerFloor)
        {
            this.parkingFloors = new ArrayList<>();
            for(int i=0;i<numberOfFloor;i++){
                parkingFloors.add(new ParkingFloor(i+1,noOfSlotPerFloor));
            }
        }
        public boolean parkVehicle(Vehicle vehicle){
            for (ParkingFloor floor : parkingFloors){
                ParkingSlot slot = floor.getAvailableSlot(vehicle);
                if(slot!=null){
                    slot.park(vehicle);
                    System.out.println("Vehicle "+vehicle.getRegistrationNumber()+" Parked at floor "+ floor.getFloor()+ " slots "+slot.getSlotId());
                    return true;
                }
            }
            System.out.println("No Available slots for "+vehicle.getType());
            return false;
        }

        public boolean unpark(String vehicleNumber){
            for(ParkingFloor floor : parkingFloors){
                List<ParkingSlot> slots = floor.getSlots();
                for (ParkingSlot slot : slots){
                    if (!slot.isAvailable() && slot.getParkedvehicle().getRegistrationNumber().equals(vehicleNumber)){
                        slot.unPark();
                        System.out.println("Vehicle "+ vehicleNumber+ " Unparked from floor "+floor.getFloor() +" slots "+slot.getSlotId());
                        return true;
                    }
                }
            }
            System.out.println("Vehicles Not Found with "+vehicleNumber);
            return false;
        }

        public void showStatus(){
            for(ParkingFloor floor : parkingFloors){
                System.out.println("Floors "+ floor.getFloor() + ": ");
                for (ParkingSlot slot : floor.getSlots()){
                    String status = slot.isAvailable() ? "Empty" : slot.getParkedvehicle().getRegistrationNumber();
                    System.out.println("Status "+"[ "+slot.getSlotType() + "] " + status);
                }
            }
        }
    }
    public static void main(String[] args) {
        ParkingLotService service = new ParkingLotService(2,2);

        service.parkVehicle(new Bike("KA-01-BW-2301"));
        service.parkVehicle(new Car("KA-02-MN-9080"));

        service.showStatus();

        service.unpark("KA-01-BW-2301");

        service.showStatus();
    }
}
