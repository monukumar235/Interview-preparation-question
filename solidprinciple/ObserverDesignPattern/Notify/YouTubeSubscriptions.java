package solidprinciple.ObserverDesignPattern.Notify;

import java.util.ArrayList;
import java.util.List;

public class YouTubeSubscriptions {
    public interface YouTubeChannel{
        void addSubscriber(Subscriber subscriber);
        void removeSubscriber(Subscriber subscriber);
        void send();
    }
    public static class YouTubeChannelImpl implements YouTubeChannel{

        public String events;
        public List<Subscriber> subscriberList = new ArrayList<>();
        @Override
        public void addSubscriber(Subscriber subscriber) {
            subscriberList.add(subscriber);
        }

        @Override
        public void removeSubscriber(Subscriber subscriber) {
            subscriberList.remove(subscriber);
        }

        @Override
        public void send() {
            for(Subscriber subscriber : subscriberList)
            {
                subscriber.updates(events);
            }
        }
        public void setEvents(String events)
        {
            this.events=events;
            send();
        }
    }
    public interface Subscriber{
        void updates(String msg);
    }
    public static class EmailSubscriber  implements Subscriber{

        public String name;

        EmailSubscriber(String name)
        {
            this.name = name;
        }
        @Override
        public void updates(String msg) {
            System.out.println("Emailed to "+ name + " about events "+ msg);
        }
    }
    public static class PushSubscriber implements Subscriber{

        public String name;
        PushSubscriber(String name)
        {
            this.name = name;
        }
        @Override
        public void updates(String msg) {
            System.out.println("Pushed Notification to "+ name+ " about events "+msg);
        }
    }
    public static void main(String[] args) {
        YouTubeChannelImpl youTubeChannel = new YouTubeChannelImpl();
        EmailSubscriber emailSubscriberMonu = new EmailSubscriber("monu");
        PushSubscriber pushSubscriberSamrat = new PushSubscriber("samrat");
        youTubeChannel.addSubscriber(emailSubscriberMonu);
        youTubeChannel.addSubscriber(pushSubscriberSamrat);

        youTubeChannel.setEvents("new serial for machine learning have beed upload in youtube!");

        youTubeChannel.removeSubscriber(pushSubscriberSamrat);

        youTubeChannel.setEvents("new java series for web development is live on youtube!");
    }
}
