package solidprinciple.ObserverDesignPattern.Notify;

import solidprinciple.ObserverDesignPattern.Notify.Observable.IphoneObservableImple;
import solidprinciple.ObserverDesignPattern.Notify.Observer.EmailNotificationObserverImpl;
import solidprinciple.ObserverDesignPattern.Notify.Observer.MoblieNotificationObserverImpl;

public class Main {
    public static void main(String[] args) {
        IphoneObservableImple iphoneObservableImple = new IphoneObservableImple();

        EmailNotificationObserverImpl emailNotificationObserver = new EmailNotificationObserverImpl(iphoneObservableImple,"xyz@123gmail.com");
        EmailNotificationObserverImpl emailNotificationObserver1 = new EmailNotificationObserverImpl(iphoneObservableImple,"vxy123@gmail.com");
        MoblieNotificationObserverImpl moblieNotificationObserver = new MoblieNotificationObserverImpl(iphoneObservableImple,"mk235");

        iphoneObservableImple.add(emailNotificationObserver);
        iphoneObservableImple.add(emailNotificationObserver1);
        iphoneObservableImple.add(moblieNotificationObserver);

        iphoneObservableImple.setStocks(10);
    }
}
