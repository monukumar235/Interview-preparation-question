package solidprinciple.ObserverDesignPattern.Notify.wheather.Observer;

import solidprinciple.ObserverDesignPattern.Notify.wheather.Observeable.WSObservable;

public class TvScreenObserver implements DisplayObserver{
    WSObservable wsObservable;

    public TvScreenObserver(WSObservable wsObservable){
        this.wsObservable =wsObservable;
    }
    @Override
    public void update() {
        System.out.println("Temperature on Tv Screen "+ wsObservable.getData());
    }
}
