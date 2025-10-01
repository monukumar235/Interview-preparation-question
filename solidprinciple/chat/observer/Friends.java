package solidprinciple.chat.observer;

public class Friends implements Observer{
    public String name;

    public Friends(String name){
        this.name = name;
    }
    @Override
    public void update(String msg) {
        System.out.println("Friend "+ name + " got notification about "+ msg);
    }
}
