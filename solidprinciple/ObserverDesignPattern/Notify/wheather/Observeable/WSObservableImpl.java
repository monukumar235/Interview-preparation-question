package solidprinciple.ObserverDesignPattern.Notify.wheather.Observeable;

import solidprinciple.ObserverDesignPattern.Notify.wheather.Observer.DisplayObserver;

import java.util.ArrayList;
import java.util.List;

public class WSObservableImpl implements WSObservable{
    List<DisplayObserver> displayObservers = new ArrayList<>();
    int temp=0;
    @Override
    public void add(DisplayObserver displayObserver) {
        displayObservers.add(displayObserver);
    }

    @Override
    public void remove(DisplayObserver displayObserver) {
        displayObservers.remove(displayObserver);
    }

    @Override
    public void notifyUser() {
        for(DisplayObserver displayObserver : displayObservers){
            displayObserver.update();
        }
    }

    @Override
    public void setData(int t) {
        if(temp==0){
            notifyUser();
        }
        temp+=t;
    }

    @Override
    public int getData() {
        return temp;
    }
}
