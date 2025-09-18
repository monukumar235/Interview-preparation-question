package multithreading;

public class FizzBuzzProblem {
    static class FizzBuzz{
        int n=1;
        int max=15;
        public synchronized void printNumber() throws InterruptedException {
            while (n<=max){
                while (n%3==0 || n%5==0) wait();
                System.out.println(n);
                n++;
                notifyAll();
            }
        }
        public synchronized void printFizz() throws InterruptedException {
            while (n<=max)
            {
                while (!(n%3==0 && n%5!=0)) wait();
                System.out.println("Fizz");
                n++;
                notifyAll();
            }
        }

        public synchronized void printBuzz() throws InterruptedException {
            while (n<=max){
                while (!(n%5==0 && n%3!=0)) wait();
                System.out.println("Buzz");
                n++;
                notifyAll();
            }
        }

        public synchronized void printFizBuzz() throws InterruptedException {
            while (n<=max)
            {
                while (!(n%3==0 && n%5==0)) wait();
                System.out.println("FizzBuzz");
                n++;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        Thread t1 = new Thread(()->{
            try {
                fizzBuzz.printNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()->{
            try {
                fizzBuzz.printBuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t3 = new Thread(()->{
            try {
                fizzBuzz.printFizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t4 = new Thread(()->{
            try {
                fizzBuzz.printFizBuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
