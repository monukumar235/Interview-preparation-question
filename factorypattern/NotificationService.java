package factorypattern;

public class NotificationService {
    public interface Notification{
        void send(String to,String msg);
    }
    public static class Email implements Notification{

        @Override
        public void send(String to, String msg) {
            System.out.println("Email send to "+ to + " " +
                    "with msg " + msg);
        }
    }
    public static class Push implements Notification{

        @Override
        public void send(String to, String msg) {
            System.out.println("Push Notification send to "+  to + " with msg "+msg);
        }
    }
    public static class Whatsapp implements Notification{

        @Override
        public void send(String to, String msg) {
            System.out.println("Whatsapp Notification send to "+ to + " with msg "+msg);
        }
    }

    public static class NotificationFactory{
        public Notification getNotification(String type){
            if(type==null || type.isEmpty()) return null;
            switch (type.toLowerCase()){
                case "email":
                    return new Email();
                case "push":
                    return new Push();
                case "whatsapp":
                    return new Whatsapp();
                default:
                    return null;
            }
        }
    }

    public static String getTemplate(String type){
        if(type.equalsIgnoreCase("order_failed")){
            return "Your order has been failed! but don't worry we will book it manually on your befalh please reach out to us in case of any query!";
        }
        if(type.equalsIgnoreCase("order_placed")){
            return "your order has been placed successfully with us";
        }
        if(type.equalsIgnoreCase("order_pending")){
            return "your order is pending please complete is as soon as possible";
        }
        return null;
    }

    public static  String getChannel(String channel){
        if(channel.equalsIgnoreCase("whatsapp")){
            return "999999999";
        }
        if(channel.equalsIgnoreCase("email")){
            return "xyz@gmail.com";
        }
        if(channel.equalsIgnoreCase("push")){
            return "phoneToken";
        }
        return null;
    }
    public static void main(String[] args) {
        NotificationFactory notificationFactory =new NotificationFactory();
        Notification email = notificationFactory.getNotification("email");

        String to =getChannel("email");
        String msg = getTemplate("order_placed");
        email.send(to,msg);

        System.out.println("-------------------------------------");

        Notification whatsaap = notificationFactory.getNotification("whatsapp");
        String toWhatsapp = getChannel("whatsapp");
        String orderPending = getTemplate("order_pending");
        whatsaap.send(toWhatsapp,orderPending);
    }
}
