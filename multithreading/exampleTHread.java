package multithreading;

public class exampleTHread {
     static class World implements Runnable {

         @Override
         public void run() {
             while (true) {
                 System.out.println("Hello!");
             }
         }
     }

         public static void main(String[] args) throws InterruptedException {
             World world = new World();
             Thread t1 = new Thread(world);
             t1.setDaemon(true);
             t1.start();
             System.out.println("Main Done!");
         }

}
