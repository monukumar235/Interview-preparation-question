package lld;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotsPractise {
    public enum  VehiclesType
    {
        CAR,
        BIKE
    }
    public static abstract class Vehicles{
        public String number;
        public VehiclesType type;
        Vehicles(String number,VehiclesType type)
        {
            this.number=number;
            this.type=type;
        }
    }
    public static class Car extends Vehicles{

        Car(String number) {
            super(number,VehiclesType.CAR);
        }
    }
    public static class Bike extends Vehicles{

        Bike(String number) {
            super(number, VehiclesType.BIKE);
        }
    }
    public static class VehiclesFactory{
        public static Vehicles createVehicles(String type,String number){
            if(type==null || type.isEmpty())
            {
                throw new IllegalArgumentException("type cann't be null!");
            }
            switch (type.toLowerCase()){
                case "car":
                    return new Car(number);
                case "bike":
                    return new Bike(number);
                default:
                    throw new UnsupportedOperationException("unupported type!"+type);
            }
        }
    }

    public static class ParkingSlots{
        public String slotsId;
        public VehiclesType slotsType;
        public boolean isFree;
        public Vehicles parkedVehicle;

        ParkingSlots(String slotsId,VehiclesType slotsType){
            this.slotsId=slotsId;
            this.slotsType=slotsType;
            this.isFree=true;
        }
        public void park(Vehicles v){
            this.parkedVehicle=v;
            this.isFree=false;
        }
        public void unpark(){
            this.parkedVehicle=null;
            this.isFree=true;
        }
        public boolean canFit(Vehicles v){
            return isFree && this.slotsType.equals(v.type);
        }

        public boolean isFree() {
            return isFree;
        }
        public String getSlotsId(){
            return slotsId;
        }
    }

    public static class Floor{
        public int floorNumber;
        public List<ParkingSlots> parkingSlots;
        Floor(int floorNumber,int carSots,int bikeSlots){
            this.floorNumber = floorNumber;
            this.parkingSlots = new ArrayList<>();

            for(int i=1;i<=carSots;i++){
                parkingSlots.add(new ParkingSlots("c"+"-"+floorNumber+"-"+i,VehiclesType.CAR));
            }
            for (int i=1;i<=bikeSlots;i++)
            {
                parkingSlots.add(new ParkingSlots("b"+"-"+floorNumber+"-"+i,VehiclesType.BIKE));
            }
        }

        public ParkingSlots getFreeSlots(Vehicles vehicles){
            for (ParkingSlots slots: parkingSlots)
            {
                if(slots.canFit(vehicles))
                {
                    return slots;
                }
            }
            return null;
        }
        public int getFloorNumber() {
            return floorNumber;
        }

        public List<ParkingSlots> getParkingSlots() {
            return parkingSlots;
        }
    }
    public interface ParkingSotsObserve{
        void notifyObservers(String msg);
    }
    public static class DisplayBoard implements ParkingSotsObserve{

        @Override
        public void notifyObservers(String msg) {
            System.out.println("DisplayBoard "+msg);
        }
    }
    public static class NotificationService implements ParkingSotsObserve{

        @Override
        public void notifyObservers(String msg) {
            System.out.println("NotificationService "+msg);
        }
    }

    public interface ParkingSlotsObserable {
        void addObserver(ParkingSotsObserve parkingSotsObserve);
        void removeObserver(ParkingSotsObserve parkingSotsObserve);
        void notifyEach(String msg);
    }

    public static class ParkingLots implements ParkingSlotsObserable{
        public String lotId;
        public List<Floor> floors;
        public List<ParkingSotsObserve> parkingSotsObserves= new ArrayList<>();

        ParkingLots(String lotId,int noOfFloor,int carSots,int bikeSlots){
            this.lotId=lotId;
            this.floors = new ArrayList<>();

            for(int i=1;i<=noOfFloor;i++){
                floors.add(new Floor(i,carSots,bikeSlots));
            }
        }
        @Override
        public void addObserver(ParkingSotsObserve parkingSotsObserve) {
            parkingSotsObserves.add(parkingSotsObserve);
        }

        @Override
        public void removeObserver(ParkingSotsObserve parkingSotsObserve) {
            parkingSotsObserves.remove(parkingSotsObserve);
        }

        @Override
        public void notifyEach(String msg) {
            for (ParkingSotsObserve observe: parkingSotsObserves)
            {
                observe.notifyObservers(msg);
            }
        }
        public String parkVehicles(Vehicles v){
            for (Floor floor : floors){
                ParkingSlots freeSlots = floor.getFreeSlots(v);
                if(freeSlots!=null)
                {
                    freeSlots.park(v);
                    String ticket = lotId + "_"+floor.getFloorNumber()+"_"+freeSlots.getSlotsId();
                    notifyEach(v.number+" parked successfully with ticket number as "+ticket);
                    return ticket;
                }
            }
            notifyEach("cann't park vehicles parking is already full! "+v.number);
            return null;
        }
        public void unparkVehicles(String ticket)
        {
            String[] split = ticket.split("_");
            int floorNumber =Integer.parseInt(split[1]);
            String slotId = split[2];

            Floor floor= floors.get(floorNumber - 1);
            for (ParkingSlots slots : floor.getParkingSlots()){
                if (slots.getSlotsId().equals(slotId)){
                    slots.unpark();
                    notifyEach("Slot has been freed! "+ticket);
                    return;
                }
            }
        }
    }
    public static void main(String[] args) {
        ParkingLots lots = new ParkingLots("Lots1",1,1,1);

        lots.addObserver(new NotificationService());
        lots.addObserver(new DisplayBoard());

        Vehicles car = VehiclesFactory.createVehicles("car", "1234");
        Vehicles bike = VehiclesFactory.createVehicles("bike", "4353232");

        String carTicket = lots.parkVehicles(car);
        String bikeTicket = lots.parkVehicles(bike);

        lots.unparkVehicles(carTicket);

    }
}
