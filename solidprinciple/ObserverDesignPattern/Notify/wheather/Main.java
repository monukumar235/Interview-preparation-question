package solidprinciple.ObserverDesignPattern.Notify.wheather;

import solidprinciple.ObserverDesignPattern.Notify.wheather.Observeable.WSObservableImpl;
import solidprinciple.ObserverDesignPattern.Notify.wheather.Observer.MobileScreenObserver;
import solidprinciple.ObserverDesignPattern.Notify.wheather.Observer.TvScreenObserver;

public class Main {
    public static void main(String[] args) {
        WSObservableImpl wsObservable = new WSObservableImpl();

        TvScreenObserver tvScreenObserver = new TvScreenObserver(wsObservable);
        MobileScreenObserver mobileScreenObserver = new MobileScreenObserver(wsObservable);

        wsObservable.add(tvScreenObserver);
        wsObservable.add(mobileScreenObserver);

        wsObservable.setData(10);
    }
}
