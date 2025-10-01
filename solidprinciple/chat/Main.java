package solidprinciple.chat;

import solidprinciple.chat.obserables.User;
import solidprinciple.chat.observer.Friends;

public class Main {
    public static void main(String[] args) {
        User user = new User("Monu");

        Friends friend1 = new Friends("Samrat");
        Friends friend2 = new Friends("Mohit");
        user.addObservers(friend1);
        user.addObservers(friend2);

        user.setStatus("active");
        user.setStatus("away");

//        user.updates();
    }
}
