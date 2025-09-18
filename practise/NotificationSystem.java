package practise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationSystem {
    public interface NotificationChannel{

        void send(String user,String message);
    }

    public static class EmailChannel implements NotificationChannel{

        @Override
        public void send(String user, String message) {
            System.out.println("Sendinng Email Notification to "+  user + " " +message);
        }
    }

    public static class PushNotification implements NotificationChannel{

        @Override
        public void send(String user, String message) {
            System.out.println("Sending Push Notification to "+user + " "+message);
        }
    }
    public static  class SmsNotificationChannel implements NotificationChannel{

        @Override
        public void send(String user, String message) {
            System.out.println("Sending Sms Notification to "+user + " "+message);
        }
    }
    public static class NotificationService{
        Map<String , List<NotificationChannel>> userSubscriptions = new HashMap<>();

        public void subscribe(String user,NotificationChannel channel){
            userSubscriptions.computeIfAbsent(user,k->new ArrayList<>()).add(channel);
        }
        public void unsubscribe(String user,NotificationChannel channel){
            List<NotificationChannel> notificationChannels = userSubscriptions.get(user);
            if(notificationChannels!=null)
            {
                notificationChannels.remove(channel);
            }
        }
        public void notifyAll(String mes){
            for(Map.Entry<String,List<NotificationChannel>> entry : userSubscriptions.entrySet()){
                String user = entry.getKey();
                for(NotificationChannel channel : entry.getValue()){
                    channel.send(user,mes);
                }
            }
        }
    }
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        EmailChannel email = new EmailChannel();
        PushNotification push = new PushNotification();
        SmsNotificationChannel sms = new SmsNotificationChannel();
        service.subscribe("monu",email);
        service.subscribe("monu",push);
        service.subscribe("monu",sms);

        service.notifyAll("Order has been placed");

        service.unsubscribe("monu",sms);

        service.notifyAll("going to delhi");
    }
}
