package solidprinciple;

public class ex1 {
    public interface PaymentMethod{
        void makePayment();
    }
    public static class CardPayment implements  PaymentMethod{

        @Override
        public void makePayment() {
            System.out.println("Card Payment.......");
        }
    }
    public static class UpiPayment implements PaymentMethod{

        @Override
        public void makePayment() {
            System.out.println("Upi Payment.....");
        }
    }
    public static class NetBanking implements PaymentMethod{

        @Override
        public void makePayment() {
            System.out.println("Net Banking.....");
        }
    }
    public static class PaymentProcessor{
        public void payment(PaymentMethod paymentMethod){
              paymentMethod.makePayment();
        }
    }
    public static void main(String[] args) {

        PaymentProcessor paymentProcessor = new PaymentProcessor();
       paymentProcessor.payment(new UpiPayment());
       paymentProcessor.payment(new CardPayment());
       paymentProcessor.payment(new NetBanking());
    }
}
