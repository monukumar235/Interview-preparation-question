package lld.practise;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TicketBooking {
    public enum SeatType{
        CLASSIC,
        PREMIUM,
        VIP
    }
    public static abstract class Seat{
        public int seatNumber;
        public double amount;
        public SeatType type;
    }


    public static class ClasssicSeat extends  Seat{
        ClasssicSeat(int seatNumber)
        {
            this.seatNumber=seatNumber;
            this.type=SeatType.CLASSIC;
            this.amount=150;
        }
    }
    public static class PremiunSeat extends Seat{
        PremiunSeat(int seatNumber)
        {
            this.seatNumber=seatNumber;
            this.type=SeatType.PREMIUM;
            this.amount=200;
        }
    }

    public static class VipSeat extends Seat{
        VipSeat(int seatNumber)
        {
            this.seatNumber=seatNumber;
            this.type=SeatType.VIP;
            this.amount=300;
        }
    }

    public class SeatFactory{
        public static Seat createSeat(String type,int seatNumber)
        {
            if(type==null || type.isEmpty())
            {
                return null;
            }
            switch (type.toLowerCase())
            {
                case "classic":
                    return new ClasssicSeat(seatNumber);
                case "premium":
                    return new PremiunSeat(seatNumber);
                case "vip":
                    return new VipSeat(seatNumber);
                default:
                    return null;
            }
        }
    }
    public interface PaymentStrategy{
        void pay(double amount);
    }
    public static class Upi implements PaymentStrategy{

        @Override
        public void pay(double amount) {
            System.out.println(amount+" paid using upi! ");
        }
    }
    public static class Card implements PaymentStrategy{

        @Override
        public void pay(double amount) {
            System.out.println(amount+" paid using card ");
        }
    }
    public static class Booking{
        public int bookingId;
        List<Seat> seats;
        PaymentStrategy paymentStrategy;
        public double totalAmount;
        Booking(List<Seat>seats,PaymentStrategy paymentStrategy)
        {
            this.seats=seats;
            this.paymentStrategy=paymentStrategy;
            this.totalAmount=seats.stream().mapToDouble(s->s.amount).sum();
            this.bookingId=(int)(Math.random()*1000);
        }
        public void confirmBooking(){
            paymentStrategy.pay(totalAmount);
            System.out.println("Booking confirm with bookingId "+bookingId);
        }
    }
    public static class BookingService{
        public static BookingService instance;
        private BookingService(){}

        public static BookingService getInstance()
        {
            if(instance==null)
            {
                instance= new BookingService();
            }
            return instance;
        }

        public Booking bookSeat(List<Seat>seats,PaymentStrategy paymentStrategy)
        {
            return new Booking(seats,paymentStrategy);
        }
    }

    public static void main(String[] args) {
        Seat s1 = SeatFactory.createSeat("vip", 1);
        Seat s2 = SeatFactory.createSeat("vip", 2);

        List<Seat> seats = Arrays.asList(s1,s2);

        BookingService bookingService = BookingService.getInstance();
        Booking booking = bookingService.bookSeat(seats, new Card());

        booking.confirmBooking();

        Booking booking1 = bookingService.bookSeat(seats, new Upi());
        booking1.confirmBooking();


    }

}
