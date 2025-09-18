package multithreading;

public class SIngleTonWithDoubleCheck {
    public static class Singleton{
        public static  Singleton instance;
        private Singleton(){
            System.out.println("Singleton intances created");
        }
        public static Singleton getInstances(){
            if(instance==null){
                synchronized (Singleton.class){
                    if(instance==null){
                        instance=new Singleton();
                    }
                }
            }
            return instance;
        }
    }
    public static void main(String[] args) {
        Runnable task= ()->{
            Singleton s =Singleton.getInstances();
            System.out.println(Thread.currentThread().getName()+ " "+s.hashCode());
        };

        Thread thread = new Thread(task,"Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread.start();
        thread2.start();
        thread3.start();
    }
}
