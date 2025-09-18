package practise;

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
            this.y =y;
        }
        public double distanceTo(Location others){
            double dx = this.x - others.x;
            double dy = this.y - others.y;
            return Math.sqrt(dx*dx - dy*dy);
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
        Rider(String name,Location location)
        {
            this.name = name;
            this.location =location;
        }
    }

    public static class Trip{

        AtomicInteger GENERATE_ID = new AtomicInteger(1);
        private int id;
        private Driver driver;
        private Rider rider;
        private Location source;
        private Location destination;
        Trip(Driver driver,Rider rider,Location source,Location destination){
            this.driver = driver;
            this.rider = rider;
            this.source = source;
            this.destination = destination;
            this.id = GENERATE_ID.getAndIncrement();
        }

        @Override
        public String toString() {
            return "Trip{" +
                    "GENERATE_ID=" + GENERATE_ID +
                    ", id=" + id +
                    ", driver=" + driver +
                    ", rider=" + rider +
                    ", source=" + source +
                    ", destination=" + destination +
                    '}';
        }
    }


    public static  class CabBookingService{
        Map<String,Driver> driverMap = new HashMap<>();
        Map<String, Rider> riderMap = new HashMap<>();
        List<Trip> trips = new ArrayList<>();

        public void addDriver(String name,double x, double y){
            driverMap.put(name,new Driver(name,new Location(x,y)));
            System.out.println("Driver Has Resigester with name "+name);
        }
        public void addRider(String name,double x,double y){
            riderMap.put(name,new Rider(name,new Location(x,y)));
            System.out.println("Rider has register with name as "+name);
        }
        public List<Driver> driverNearBy(String riderName){
            List<Driver> driverList = new ArrayList<>();
            Rider rider = riderMap.get(riderName);
            for (Driver driver : driverMap.values()){
                if(driver.isAvailable && rider.location.distanceTo(driver.location)<=5){
                    driverList.add(driver);
                }
            }
            return driverList;
        }

        public Trip bookCab(String riderName ,double destx,double destY){
            List<Driver> driverNearBy = driverNearBy(riderName);
            if(driverNearBy.isEmpty()){
                System.out.println("No Driver Found At this moment!");
                return null;
            }
            Rider rider = riderMap.get(riderName);
            Driver selectedDriver = driverNearBy.get(0);
            selectedDriver.isAvailable=false;
            Location destination = new Location(destx, destY);
            Trip trip = new Trip(selectedDriver, rider, rider.location, destination);
            trips.add(trip);
            System.out.println("Trip started with driver: "+ selectedDriver.name);
            return trip;
        }
        public void endTrip(int tripId){
            for (Trip trip : trips){
                if (trip.id== tripId){
                    Driver driver = trip.driver;
                    driver.isAvailable=true;
                    driver.location=trip.destination;
                    System.out.println("Trip got over at: "+ trip.destination);
                    return;
                }
            }
            System.out.println("Trip not founs with: " + tripId);
        }
        public void printTrip(){
            for (Trip trip : trips)
            {
                System.out.println(trip);
            }
        }
    }

    public static void main(String[] args) {
        CabBookingService service = new CabBookingService();

        service.addDriver("roghit",0,0);
        service.addDriver("mohit",1,2);
        service.addDriver("golu",3,1);

        service.addRider("hema",1,1);

        Trip trip = service.bookCab("hema", 1, 4);

        if (trip!=null){
            service.endTrip(trip.id);
        }


    }
}
