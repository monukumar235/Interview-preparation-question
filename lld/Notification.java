package lld;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    public interface NotificationChannel{
        void send(User user,String msg);
    }
    public static class Email implements NotificationChannel{

        @Override
        public void send(User user,String msg) {
            System.out.println("Emailed to"+" "+ user.getName() + " "+msg);
        }
    }
    public static class Sms implements NotificationChannel{

        @Override
        public void send(User user,String msg) {
            System.out.println("Sms to"+" "+user.getName()+" "+msg);
        }
    }
    public static class Push implements NotificationChannel{

        @Override
        public void send(User user,String msg) {
            System.out.println("pushed to"+" "+user.getName()+" "+msg);
        }
    }

    public class NotificationFactory{
        public static NotificationChannel createChannel(String type)
        {
            if(type==null || type.isEmpty())
            {
                throw new IllegalArgumentException("Type is null");
            }
            switch (type.toLowerCase()){
                case "email":
                    return new Email();
                case "push":
                    return new Push();
                case "sms" :
                    return new Sms();
                default:
                    throw new UnsupportedOperationException("Not supported"+type);
            }
        }
    }
    public interface User{
        void update(String msg);
        public String getName();
    }
    public static class UserImpl implements User{

        public String name;
        UserImpl(String name)
        {
            this.name=name;
        }
        @Override
        public void update(String msg) {
            System.out.println(name+" "+msg);
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public interface NotificationService{
        void subscribe(User user);
        void unsubscribe(User user);
        void notifyUser(String msg,String type);
    }

    public static class Notificationimpl implements NotificationService{

        List<User> userList = new ArrayList<>();
        @Override
        public void subscribe(User user) {
            userList.add(user);
        }

        @Override
        public void unsubscribe(User user) {
            userList.remove(user);
        }

        @Override
        public void notifyUser(String msg,String type) {
            NotificationChannel channel = NotificationFactory.createChannel(type);
            for(User user: userList){
                channel.send(user,msg);
            }
        }
    }
    public static void main(String[] args) {
        NotificationService notificationService = new Notificationimpl();

        User user = new UserImpl("monu");
        User user1 = new UserImpl("samrat");
        notificationService.subscribe(user);
        notificationService.subscribe(user1);

        notificationService.notifyUser("new features is added","email");
        notificationService.notifyUser("you have a class today","sms");

        notificationService.unsubscribe(user1);

        notificationService.notifyUser("fuck you","push");
    }
}
