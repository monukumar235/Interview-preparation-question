package solidprinciple.ObserverDesignPattern.Notify.Observer;

import solidprinciple.ObserverDesignPattern.Notify.Observable.StockObservable;

public class EmailNotificationObserverImpl implements NotificationObserver{
    StockObservable stockObservable;
    String email;
    public EmailNotificationObserverImpl(StockObservable obj, String email){
        this.stockObservable =obj;
        this.email=email;
    }
    @Override
    public void update() {
        sendEmail(email,"hurry up stock is now available!!!!!");
    }

    private void sendEmail(String email, String text) {
        System.out.println("Notify to "+email + " that "+text);
    }
}
