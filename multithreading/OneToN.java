package multithreading;

public class OneToN {
    public static class printN{
        int maxNumbers;
        boolean isOdd =true;
        int n=1;
        printN(int maxNumbers){
            this.maxNumbers=maxNumbers;
        }
        public synchronized void printOdd() throws InterruptedException {
            while (n<=maxNumbers){
                if(!isOdd){
                    wait();
                }
                else {
                    System.out.println("Thread odd" + n);
                    isOdd=false;
                    n++;
                    notifyAll();
                }
            }
        }
        public synchronized void printEven() throws InterruptedException {
            while (n<=maxNumbers){
                if(isOdd){
                    wait();
                }else{
                    System.out.println("Thread even"+n);
                    n++;
                    isOdd=true;
                    notifyAll();
                }
            }
        }
    }
    public static void main(String[] args) {
        printN printN = new printN(20);
        Thread oddThread = new Thread(() -> {
            try {
                printN.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                printN.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        oddThread.start();
        evenThread.start();

    }
}
