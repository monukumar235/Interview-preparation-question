package solidprinciple;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    public interface Subject {
        void addChannel(Channels channels);

        void removeChannels(Channels channels);

        void updates();
    }

    public static class Events implements Subject {

        public String events;
        public String name;
        List<Channels> channelsList = new ArrayList<>();

        @Override
        public void addChannel(Channels channels) {
            channelsList.add(channels);
        }

        @Override
        public void removeChannels(Channels channels) {
            channelsList.remove(channels);
        }

        @Override
        public void updates() {
            for (Channels channels : channelsList) {
                channels.update(events + " " + name);
            }
        }

        public void setData(String name, String events) {
            this.name = name;
            this.events = events;
            updates();
        }
    }

    public interface Channels {
        void update(String msg);
    }

    public static class EmailChannels implements Channels {
        @Override
        public void update(String msg) {
            System.out.println("Email " + msg);
        }
    }

    public static class PushNotification implements Channels {
        @Override
        public void update(String msg) {
            System.out.println("Push " + msg);
        }
    }

    public static void main(String[] args) {
        Events events = new Events();
        events.addChannel(new EmailChannels());
        events.addChannel(new PushNotification());

        events.setData("monu", "Missed called from");

    }
}
