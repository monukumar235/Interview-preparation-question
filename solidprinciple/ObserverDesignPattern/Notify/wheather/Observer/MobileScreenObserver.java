package solidprinciple.ObserverDesignPattern.Notify.wheather.Observer;

import solidprinciple.ObserverDesignPattern.Notify.wheather.Observeable.WSObservable;

public class MobileScreenObserver implements DisplayObserver{
    WSObservable wsObservable;
    public MobileScreenObserver(WSObservable wsObservable){
        this.wsObservable=wsObservable;
    }
    @Override
    public void update() {
        System.out.println("Temperature on Mobile Screen is "+wsObservable.getData());
    }
}
