package singleton;

public class FactoryPatternPracise {

    public abstract static class Payment{
        public abstract void pay(int amount);
    }
    public static class UpiPayment extends Payment{

        @Override
        public void pay(int amount) {
            System.out.println(amount + " paid using upi ");
        }
    }

    public static class CardPayment extends Payment{
        @Override
        public void pay(int amount) {
            System.out.println(amount+ " paid using Card ");
        }
    }
    public static class PaymentFactory{
        public Payment getPayment(String type)
        {
            if(type.isEmpty() || type==null){
                return null;
            }
            switch (type.toLowerCase()){
                case "card":
                    return new CardPayment();
                case "upi" :
                    return new UpiPayment();
                default:
                    throw new UnsupportedOperationException("Unsupported type!");
            }
        }
    }
    public static void main(String[] args) {
        PaymentFactory paymentFactory = new PaymentFactory();

        Payment card = paymentFactory.getPayment("card");
        card.pay(2000);

        Payment upi = paymentFactory.getPayment("upi");
        upi.pay(9000);

        Payment paypal = paymentFactory.getPayment("paypal");
        paypal.pay(500);
    }
}
