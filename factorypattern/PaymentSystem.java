package factorypattern;

public class PaymentSystem {
    public interface Payment{
        void pay();
    }
    public static class UpiPayment implements Payment{

        @Override
        public void pay() {
            System.out.println("Payment done using UPI!");
        }
    }

    public static class CardPayment implements Payment{

        @Override
        public void pay() {
            System.out.println("Payment done using Card!");
        }
    }

    public static class PaymentFactory{
        public Payment getPaymentMethod(String type)
        {
            if(type.isEmpty() || type==null)
            {
                return null;
            }
            switch (type.toLowerCase()){
                case "upi":
                    return new UpiPayment();
                case "card":
                    return new CardPayment();
                default:
                    throw new UnsupportedOperationException("Payment metod not supported!");
            }
        }
    }
    public static void main(String[] args) {
        PaymentFactory paymentFactory = new PaymentFactory();
        Payment payment= paymentFactory.getPaymentMethod("upi");
        payment.pay();

        Payment card = paymentFactory.getPaymentMethod("card");
        card.pay();
    }
}
