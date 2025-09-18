package machinecoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationSystem {
    public interface NotificationChannel{
        void send(String user,String message);
    }
    public static class EmailNotification implements  NotificationChannel{
        @Override
        public void send(String user, String message) {
            System.out.println("Sending Emial to " + user + ": "+message);
        }
    }
    public static class SMSNotification implements NotificationChannel{

        @Override
        public void send(String user, String message) {
            System.out.println("Sending SMS Notification to "+ user + ": "+message);
        }
    }
    public static class PushNotification implements NotificationChannel{

        @Override
        public void send(String user, String message) {
            System.out.println("Sending Push Notification to: "+ user + ": "+message);
        }
    }
    public static class NotificationService{
        private Map<String, List<NotificationChannel>> userSubscriptions = new HashMap<>();

        public void subscription(String user,NotificationChannel channel){
            userSubscriptions.computeIfAbsent(user,k->new ArrayList<>()).add(channel);
        }

        public void unsubscribe(String user,NotificationChannel channel){
            List<NotificationChannel> notificationChannels = userSubscriptions.get(user);
            if(notificationChannels!=null){
                notificationChannels.remove(channel);
            }
        }

        public void notifyUsers(String message){
            for(Map.Entry<String,List<NotificationChannel>> entry : userSubscriptions.entrySet()){
                String user = entry.getKey();
                for (NotificationChannel channel: entry.getValue()){
                    channel.send(user,message);
                }
            }
        }
    }
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        notificationService.subscription("monu123@gmail.com",new EmailNotification());
        notificationService.subscription("monu123@gmail.com",new PushNotification());
        notificationService.subscription("monu123@gmail.com",new SMSNotification());

        notificationService.notifyUsers("Order has been placed successfully");
    }
}
