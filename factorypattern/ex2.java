package factorypattern;

public class ex2 {
    public interface Notification{
        void display();
    }

    public static class SmsNotification implements Notification{

        @Override
        public void display() {
            System.out.println("Notification send via sms...");
        }
    }
    public static class WhatsappNotification implements Notification{

        @Override
        public void display() {
            System.out.println("Notification send via whatsapp...");
        }
    }
    public static class PushNotification implements Notification{

        @Override
        public void display() {
            System.out.println("Notification send via push...");
        }
    }

    public static class NotificationFactory{
        public Notification getNotification(String type){
            if(type.isEmpty() || type==null) return null;
            switch (type.toLowerCase()){
                case "sms":
                    return new SmsNotification();
                case "whatsapp" :
                    return new WhatsappNotification();
                case "push":
                    return new PushNotification();
                default:
                    return null;
            }
        }
    }
    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();
        Notification sms = notificationFactory.getNotification("sms");
        sms.display();

        Notification push = notificationFactory.getNotification("push");
        push.display();

        Notification whatsapp = notificationFactory.getNotification("whatsapp");
        whatsapp.display();


    }
}
