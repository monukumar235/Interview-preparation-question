package lld;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotsService {
    public static abstract class Vehicles{
        public String number;
        Vehicles(String number)
        {
            this.number=number;
        }
        public String getNumber() {
            return number;
        }
    }

    public static class Car extends Vehicles{

        Car(String number) {
            super(number);
        }
    }
    public static class Bike extends Vehicles{

        Bike(String number) {
            super(number);
        }
    }
    public class VehiclesFactory{
        public static Vehicles createType(String type,String number){
            if(type==null ||type.isEmpty())
            {
                throw new IllegalArgumentException("type can not ne null");
            }
            switch (type.toLowerCase()){
                case "car":
                    return new Car(number);
                case "bike":
                    return new Bike(number);
                default:
                    throw new UnsupportedOperationException("type is not supported!"+type);
            }
        }
    }

    public interface Observers{
        void update(String msg);
    }

    public static class VehiclesOwners implements Observers{

        public String name;
        VehiclesOwners(String name)
        {
            this.name=name;
        }
        @Override
        public void update(String msg) {
            System.out.println(name + " recieved message "+msg);
        }
    }
    public interface ParkingLots{
        void addUsers(Observers observers);
        void removeUsers(Observers observers);
        void notifyUsers(String msg);
    }

    public static class ParkingLotsImpl implements ParkingLots{

        public int totalSpots;
        public int occupied;
        ParkingLotsImpl(int totalSpots)
        {
            this.totalSpots=totalSpots;
        }
        List<Observers> observersList = new ArrayList<>();
        @Override
        public void addUsers(Observers observers) {
            observersList.add(observers);
        }

        @Override
        public void removeUsers(Observers observers) {
            observersList.remove(observers);
        }

        @Override
        public void notifyUsers(String msg) {
            for(Observers observers : observersList)
            {
                observers.update(msg);
            }
        }
        public void park(Vehicles vehicles){
            if(occupied>=totalSpots){
                notifyUsers("parking slots has been full! can not park! "+vehicles.getNumber());
            }
            else{
                occupied++;
                notifyUsers(vehicles.getNumber()+ " parked successfully!.");
            }
        }

        public void unpark(Vehicles vehicles){
            if(occupied>0){
                occupied--;
                notifyUsers(vehicles.getNumber() + "unpark successfully!");
            }
        }
    }

    public static void main(String[] args) {
        ParkingLotsImpl parkingLots = new ParkingLotsImpl(10);
        VehiclesOwners owners =new VehiclesOwners("rohit");

        parkingLots.addUsers(owners);

        Vehicles bike = VehiclesFactory.createType("bike", "abcd1234");
        Vehicles car = VehiclesFactory.createType("car", "abnm1234");

        parkingLots.park(bike);
        parkingLots.park(car);

    }
}
