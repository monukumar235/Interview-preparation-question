package solidprinciple.ObserverDesignPattern.Notify.Observable;

import solidprinciple.ObserverDesignPattern.Notify.Observer.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImple implements  StockObservable{

    List<NotificationObserver> notificationObservers = new ArrayList<>();
    int stock=0;

    @Override
    public void add(NotificationObserver obj) {
        notificationObservers.add(obj);
    }

    @Override
    public void remove(NotificationObserver obj) {
        notificationObservers.remove(obj);
    }

    @Override
    public void notifyUsers() {
        for(NotificationObserver notificationObserver : notificationObservers){
            notificationObserver.update();
        }
    }

    @Override
    public void setStocks(int newStock) {
        if(stock==0){
            notifyUsers();
        }
        stock+=newStock;
    }
}
