package lld.practise;

import java.util.ArrayList;
import java.util.List;

public class ParkingLots {
    public enum VehicleType{
        CAR,
        BIKE
    }
    public abstract static class Vehicles{
        public String number;
        public VehicleType type;
        Vehicles(String number,VehicleType type)
        {
            this.number=number;
            this.type=type;
        }
    }
    public static class Car extends Vehicles{
        Car(String number) {
            super(number, VehicleType.CAR);
        }
    }
    public static class Bike extends Vehicles{

        Bike(String number) {
            super(number, VehicleType.BIKE);
        }
    }
    public class VehicleFactory{
        public static Vehicles createVehicles(String type,String number)
        {
            if(type==null || type.isEmpty())
            {
                throw new IllegalArgumentException("type can not be null!");
            }
            switch (type.toLowerCase()){
                case "car":
                    return new Car(number);
                case "bike":
                    return new Bike(number);
                default:
                    throw new UnsupportedOperationException("type is not supported! "+type);
            }
        }
    }
    public static class ParkingSlots{
        public String slotsId;
        public Vehicles parkedVehicles;
        public VehicleType type;
        public boolean isAvailable;

        public String getSlotsId() {
            return slotsId;
        }

        public Vehicles getParkedVehicles() {
            return parkedVehicles;
        }

        public VehicleType getType() {
            return type;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        ParkingSlots(String slotsId, VehicleType type)
        {
            this.slotsId = slotsId;
            this.isAvailable=true;
            this.type=type;
        }
        public boolean canFit(Vehicles vehicles){
            return isAvailable && type.equals(vehicles.type);
        }
        public void park(Vehicles v)
        {
            this.parkedVehicles=v;
            this.isAvailable=false;
        }
        public void unpark(){
            this.isAvailable=true;
            this.parkedVehicles=null;
        }
    }
    public static class Floor{
        public int floorId;
        public List<ParkingSlots> parkingSlots;

        public int getFloorId() {
            return floorId;
        }

        public List<ParkingSlots> getParkingSlots() {
            return parkingSlots;
        }

        Floor(int floorId, int carSlots, int bikeSlots)
        {
            this.floorId=floorId;
            this.parkingSlots=new ArrayList<>();
            for(int i=1;i<=carSlots;i++){
                parkingSlots.add(new ParkingSlots("C-"+floorId+"-"+i,VehicleType.CAR));
            }
            for (int i=1;i<=bikeSlots;i++)
            {
                parkingSlots.add(new ParkingSlots("B-"+floorId+"-"+i,VehicleType.BIKE));
            }
        }
        public ParkingSlots getFreeSlots(Vehicles v)
        {
            for (ParkingSlots slots:parkingSlots)
            {
                if(slots.canFit(v))
                {
                    return slots;
                }
            }
            return null;
        }
    }
    public interface ParkingObserver{
        void notifyObserver(String msg);
    }
    public static class DisplayBoard implements ParkingObserver{

        @Override
        public void notifyObserver(String msg) {
            System.out.println("DisplayBoard "+msg);
        }
    }
    public static class Notification implements ParkingObserver{

        @Override
        public void notifyObserver(String msg) {
            System.out.println("Notification "+msg);
        }
    }

    public static class ParkingLot {
        public String lotsId;
        List<Floor> floors;
        List<ParkingObserver> parkingObservers = new ArrayList<>();

        public String getLotsId() {
            return lotsId;
        }

        public List<Floor> getFloors() {
            return floors;
        }

        public List<ParkingObserver> getParkingObservers() {
            return parkingObservers;
        }

        ParkingLot(String lotsId, int carSlots, int bikeSlots, int noOfFloor) {
            this.lotsId = lotsId;
            this.floors = new ArrayList<>();
            for (int i = 1; i <= noOfFloor; i++) {
                floors.add(new Floor(i, carSlots, bikeSlots));
            }
        }

        public void addObserver(ParkingObserver parkingObserver) {
            parkingObservers.add(parkingObserver);
        }

        public void removeObservers(ParkingObserver parkingObserver) {
            parkingObservers.remove(parkingObserver);
        }

        public void notifyObservers(String msg) {
            for (ParkingObserver parkingObserver : parkingObservers) {
                parkingObserver.notifyObserver(msg);
            }
        }

        public String park(Vehicles vehicles) {
            for (Floor floor : floors) {
                ParkingSlots freeSlots = floor.getFreeSlots(vehicles);
                if (freeSlots != null) {
                    freeSlots.park(vehicles);
                    freeSlots.isAvailable = false;
                    String ticket = "Ticket" + "_" + floor.floorId + "_" + freeSlots.slotsId + "_" + vehicles.type;
                    notifyObservers(vehicles.number + " parked " + " ticket :" + ticket);
                    return ticket;
                }
            }
            notifyObservers("Currently slots are not available right now! " + vehicles.number);
            return null;
        }

        public void unpark(String ticket) {
            String[] s = ticket.split("_");
            int floorId = Integer.parseInt(s[1]);
            String slotsId = s[2];
            Floor floor = floors.get(floorId - 1);
            for (ParkingSlots slots : floor.getParkingSlots()) {
                if (slots.getSlotsId().equalsIgnoreCase(slotsId)) {
                    slots.unpark();
                    slots.isAvailable = true;
                    notifyObservers("Freed Slots " + slotsId);
                    return;
                }
            }
        }
    }
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot("lot-1",1,1,1);

        lot.addObserver(new DisplayBoard());
        lot.addObserver(new Notification());

        Vehicles car = VehicleFactory.createVehicles("car", "c-123");
        Vehicles bike = VehicleFactory.createVehicles("bike", "b-123");
        String carTicket = lot.park(car);

        lot.park(bike);

        lot.unpark(carTicket);
        lot.park(car);


    }
}
