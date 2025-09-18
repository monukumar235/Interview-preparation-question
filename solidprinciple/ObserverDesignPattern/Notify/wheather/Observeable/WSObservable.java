package solidprinciple.ObserverDesignPattern.Notify.wheather.Observeable;

import solidprinciple.ObserverDesignPattern.Notify.wheather.Observer.DisplayObserver;

public interface WSObservable {
    void add(DisplayObserver displayObserver);
    void remove(DisplayObserver displayObserver);
    void notifyUser();
    void setData(int temp);
    int getData();
}
