package solidprinciple.ObserverDesignPattern.Notify.Observable;

import solidprinciple.ObserverDesignPattern.Notify.Observer.NotificationObserver;

public interface StockObservable {

    void add(NotificationObserver obj);
    void remove(NotificationObserver obj);
    void notifyUsers();
    void setStocks(int newStock);

}
