package multithreading;

public class OneZeroZigZag {
    static class OneZero{
        int n=1;
        int max =6;
        public synchronized void printZero() throws InterruptedException {
            while (n<max){
                while (n%2!=0) wait();
                System.out.println(Thread.currentThread().getName()+ " 0 ");
                n++;
                notifyAll();
            }
        }
        public synchronized void printOne() throws InterruptedException {
            while (n<max)
            {
                while (n%2==0) wait();
                System.out.println(Thread.currentThread().getName()+" 1");
                n++;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        OneZero oneZero = new OneZero();
        Thread t1 = new Thread(()->{
            try {
                oneZero.printZero();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"zeroThread");
        Thread t2 = new Thread(()->{
            try {
                oneZero.printOne();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"oneThread");
        t1.start();
        t2.start();
    }
}
