package singleton;
public class singletonPracse {
    static class Singleton{
        private static volatile Singleton intances;

        private Singleton(){}

        public static Singleton getIntances(){
            if(intances==null)
            {
                synchronized (Singleton.class){
                    if(intances==null){
                        intances = new Singleton();
                    }
                }
            }
            return intances;
        }

    }
    public static void main(String[] args) {
        Singleton obj = Singleton.getIntances();
        Singleton obj1 = Singleton.getIntances();
        if(obj==obj1)
        {
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

}
