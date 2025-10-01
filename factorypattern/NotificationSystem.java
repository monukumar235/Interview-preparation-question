package factorypattern;

public class NotificationSystem {
    public interface Notification{
        void send(String msg);
    }

    public static class Email implements Notification{

        @Override
        public void send(String msg) {
            System.out.println("Emailed "+msg);
        }
    }
    public static class Push implements Notification{

        @Override
        public void send(String msg) {
            System.out.println("Pushed "+msg);
        }
    }

    public static class NotificationFactory{
        public  Notification createNotification(String type){
            if(type.isEmpty())
            {
                throw new IllegalArgumentException("Type does not supported!");
            }
            switch (type.toLowerCase())
            {
                case "email":
                    return new Email();
                case "push" :
                    return new Push();
                default:
                    throw new UnsupportedOperationException("channel does not supported!"+  type);
            }
        }
    }
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();
        Notification email = factory.createNotification("email");
        email.send("order placed!");
        Notification push = factory.createNotification("push");
        push.send("your otp is this 1234");

    }
}
