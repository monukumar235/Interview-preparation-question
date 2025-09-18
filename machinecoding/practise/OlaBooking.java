package machinecoding.practise;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OlaBooking {
    public static  class Location{
        private double x;
        private double y;
        public Location(double x, double y){
            this.x =x;
            this.y =y;
        }
        public double distanceTo(Location others){
            double dx = this.x - others.x;
            double dy = this.y - others.y;
            return Math.sqrt(dx*dx -dy*dy);
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static  class Driver{
        private String name;
        private Location location;
        private boolean isAvailable =true;

        public Driver(String name,Location location){
            this.name = name;
            this.location =location;
        }

        @Override
        public String toString() {
            return "Driver{" +
                    "name='" + name + '\'' +
                    ", location=" + location +
                    ", isAvailable=" + isAvailable +
                    '}';
        }
    }

    public static class Rider{
        private String name;
        private Location location;
        public Rider(String name,Location location)
        {
            this.name=name;
            this.location =location;
        }

        @Override
        public String toString() {
            return "Rider{" +
                    "name='" + name + '\'' +
                    ", location=" + location +
                    '}';
        }
    }

    public static class Trip{
        AtomicInteger atomicInteger = new AtomicInteger(1);
        private int id;
        private Location source;
        private Location destination;
        private  Driver driver;
        private Rider rider;

        public Trip(Driver driver,Rider rider,Location source,Location destination){
            this.destination=destination;
            this.driver = driver;
            this.source=source;
            this.rider = rider;
            this.id = atomicInteger.getAndIncrement();
        }

        @Override
        public String toString() {
            return "Trip{" +
                    ", id=" + id +
                    ", source=" + source +
                    ", destination=" + destination +
                    ", driver=" + driver +
                    ", rider=" + rider +
                    '}';
        }
    }

    public static class OlaBookingSystem{
        Map<String,Driver> driverMap = new HashMap<>();
        Map<String,Rider> riderMap = new HashMap<>();
        List<Trip> trips = new ArrayList<>();
        public void addDriver(String driverName,double x,double y){
            driverMap.putIfAbsent(driverName,new Driver(driverName,new Location(x,y)));
            System.out.println("Driver Added "+ driverName);
        }

        public void addRider(String riderName,double x,double y)
        {
            riderMap.putIfAbsent(riderName,new Rider(riderName,new Location(x,y)));
            System.out.println("Rider Added " + riderName);
        }

        public List<Driver> driverNearBy(String riderName){
            List<Driver> res = new ArrayList<>();

            Rider rider = riderMap.get(riderName);
            for (Driver driver : driverMap.values()){
                if (driver.isAvailable && driver.location.distanceTo(rider.location)<=5){
                    res.add(driver);
                }
            }
            return  res;
        }
        public Trip bookTrip(String riderName,double destx,double desty){
            Rider rider = riderMap.get(riderName);
            List<Driver> drivers = driverNearBy(riderName);
            if(drivers.isEmpty()){
                System.out.println("No ride available from rider "+ riderName);
                return null;
            }
            Driver selectedDriver = drivers.get(0);
            Location destination = new Location(destx, desty);
            selectedDriver.isAvailable =false;
            Trip trip = new Trip(selectedDriver, rider, rider.location, destination);
            trips.add(trip);
            System.out.println("Trip Started with Driver name: "+ selectedDriver.name);
            return trip;
        }

        public void endTrip(int tripId){
            for(Trip trip : trips){
                if(trip.id ==tripId){
                    Driver driver = trip.driver;
                    driver.isAvailable=true;
                    driver.location=trip.destination;
                    System.out.println("Trip over at: "+trip.destination);
                    return;
                }
            }
            System.out.println("Trip not found with tripId: "+tripId);
        }

        public void printTrip(){
            for (Trip trip :trips){
                System.out.println(trip);
            }
        }

    }
    public static void main(String[] args) {

        OlaBookingSystem olaBookingSystem = new OlaBookingSystem();

        olaBookingSystem.addDriver("rohit",1,2);
        olaBookingSystem.addDriver("mohit",2,3);
        olaBookingSystem.addDriver("sonu",0,0);

        olaBookingSystem.addRider("yesh",1,4);

//        List<Driver> driverList = olaBookingSystem.driverNearBy("yesh");
//        System.out.println(driverList);

        Trip trip = olaBookingSystem.bookTrip("yesh", 100, 500);

        if(trip!=null) olaBookingSystem.endTrip(trip.id);

        olaBookingSystem.printTrip();

    }
}
