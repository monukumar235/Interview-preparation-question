package solidprinciple.chat.obserables;

import solidprinciple.chat.observer.Observer;

public interface System {
    void addObservers(Observer observer);
    void removeObservers(Observer observer);
    void updates();
}
