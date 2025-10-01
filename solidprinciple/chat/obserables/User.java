package solidprinciple.chat.obserables;

import solidprinciple.chat.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class User implements System{

    public String name;
    public  String status;
    List<Observer> observers = new ArrayList<>();

    public User(String name){
        this.name=name;
    }
    public void setStatus(String status)
    {
        this.status=status;
        updates();
    }
    @Override
    public void addObservers(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObservers(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void updates() {
        for(Observer observer : observers)
        {
            observer.update(name+ " is now " + status);
        }
    }
}
