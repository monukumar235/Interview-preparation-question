package solidprinciple.ObserverDesignPattern.Notify.Observer;

import solidprinciple.ObserverDesignPattern.Notify.Observable.StockObservable;

public class MoblieNotificationObserverImpl implements NotificationObserver{
    StockObservable stockObservable;
    String userName;

    public MoblieNotificationObserverImpl(StockObservable stockObservable, String userName){
        this.stockObservable=stockObservable;
        this.userName=userName;
    }
    @Override
    public void update() {
        sendOnMobile(userName,"Hurry up Stock is up!!!");
    }

    private void sendOnMobile(String userName, String text) {
        System.out.println("Notify on Mobile " + userName + " that "+ text);
    }
}
