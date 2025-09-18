package machinecoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CabBooking {
    public static class Location{
        private double x;
        private double y;
        Location(double x,double y){
            this.x=x;
            this.y=y;
        }
        public double distanceTo(Location other){
            double dX =this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dX*dX-dy*dy);
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class Driver{
        private String name;
        private Location location;
        private boolean isAvailable =true;
        Driver(String name,Location location){
            this.name =name;
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

        Rider(String name,Location location){
            this.name = name;
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
        private Driver driver;
        private Rider rider;
        private Location source;
        private Location destination;

        Trip(Driver driver,Rider rider,Location source,Location destination){
            this.id = atomicInteger.getAndIncrement();
            this.driver=driver;
            this.rider=rider;
            this.source=source;
            this.destination=destination;
        }

        @Override
        public String toString() {
            return "Trip{" +
                    "id=" + id +
                    ", driver=" + driver +
                    ", rider=" + rider +
                    ", source=" + source +
                    ", destination=" + destination +
                    '}';
        }
    }
    public static class CabBookingApp{
        Map<String,Driver> driverMap = new HashMap<>();
        Map<String,Rider> riderMap =new HashMap<>();
        List<Trip> trips = new ArrayList<>();

        public void addDriver(String name,double x,double y){
            driverMap.put(name,new Driver(name,new Location(x,y)));
            System.out.println("Driver Added: "+ name);
        }

        public void addRider(String name ,double x,double y){
            riderMap.put(name,new Rider(name,new Location(x,y)));
            System.out.println("Rider Added: "+name);
        }
        public List<Driver> findNearByDriver(String riderName){
            List<Driver> drivers = new ArrayList<>();
            Rider rider = riderMap.get(riderName);
            for(Driver driver : driverMap.values()){
                if(driver.isAvailable && driver.location.distanceTo(rider.location)<=5){
                    drivers.add(driver);
                }
            }
            return drivers;
        }
        public Trip bookCap(String riderName,double desX,double desY){
            List<Driver> nearByDriver = findNearByDriver(riderName);
            Rider rider = riderMap.get(riderName);
            if(nearByDriver.isEmpty()){
                System.out.println("No available can for rider "+riderName);
                return null;
            }
            Driver selectedDriver = nearByDriver.get(0);
            selectedDriver.isAvailable=false;
            Location destination = new Location(desX,desY);
            Trip trip = new Trip(selectedDriver, rider, rider.location, destination);
            trips.add(trip);
            System.out.println("Trip started: "+trip);
            return trip;
        }
        public void endTrip(int tripId){
            for(Trip trip : trips){
                if(trip.id ==tripId) {
                    Driver driver = trip.driver;
                    driver.isAvailable = true;
                    driver.location = trip.destination;
                    System.out.println("Trip got over: " + trip);
                    return;
                }
            }
            System.out.println("TripId not found! "+tripId);
        }
        public void printTrip(){
            for (Trip trip : trips){
                System.out.println(trip);
            }
        }
    }

    public static void main(String[] args) {

        CabBookingApp cabBooking = new CabBookingApp();

        cabBooking.addDriver("Anna",0,0);
        cabBooking.addDriver("Appa",2,1);

        cabBooking.addRider("gappa",1,1);

//        List<Driver> nearByDriver = cabBooking.findNearByDriver("gappa");
//        System.out.println(nearByDriver);

        Trip trip = cabBooking.bookCap("gappa", 400, 1000);
//        System.out.println(trip);


       if(trip!=null) cabBooking.endTrip(trip.id);

        cabBooking.printTrip();

    }
}
