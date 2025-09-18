package multithreading;

public class MultipleConditionQuestion {
    static class MultipleConditions{
        int n =1;
        int MAX =15;
        public synchronized void printThree() throws InterruptedException {
            while (n<=MAX){
                while (n%3!=0){
                    wait();
                }
                System.out.println(Thread.currentThread().getName()+ " "+ n);
                n++;
                notifyAll();
            }
        }
        public synchronized void printFive() throws InterruptedException {
            while (n<=MAX){
                while (n%5!=0){
                    wait();
                }
                System.out.println(Thread.currentThread().getName()+ " "+n);
                n++;
                notifyAll();
            }
        }
        public synchronized void printOther() throws InterruptedException {
            while (n<=MAX)
            {
                while (n%5==0 || n%3==0){
                    wait();
                }
                System.out.println(Thread.currentThread().getName()+ " "+ n);
                n++;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        MultipleConditions multipleConditions = new MultipleConditions();

        Thread t1 = new Thread(()->{
            try {
                multipleConditions.printThree();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"threadThree");

        Thread t2 = new Thread(()->{
            try {
                multipleConditions.printFive();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"threadFive");

        Thread t3 = new Thread(()->{
            try {
                multipleConditions.printOther();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"threadOthers");

        t1.start();
        t2.start();
        t3.start();
    }
}
