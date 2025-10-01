package multithreading;

public class example {
     class animal{
        void sound(){
            System.out.println("animal sound");
        }
    }
    class  dog extends  animal{
        @Override
        void sound(){
            System.out.println("dog sound");
        }
    }
    public static void main(String[] args) {

    }
}
