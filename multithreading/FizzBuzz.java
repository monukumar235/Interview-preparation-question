package multithreading;

public class FizzBuzz {
    public static class FizzBuzzs{
        int n=1;
        int max;
        FizzBuzzs(int max){
            this.max=max;
        }
        public synchronized void printNumber() throws InterruptedException {
            while(n<=max){
                if(n%3!=0 && n%5!=0){
                    System.out.println(n);
                    n++;
                    notifyAll();
                }
                else {
                    wait();
                }
            }
        }
        public synchronized void printBuzz() throws InterruptedException {
            while (n<=max){
                if(n%3==0 && n%5!=0)
                {
                    System.out.println("Buzz");
                    n++;
                    notifyAll();
                }
                else {
                    wait();
                }
            }
        }
        public synchronized void printFuzz() throws InterruptedException {
            while (n<=max)
            {
                if(n%3!=0 && n%5==0)
                {
                    System.out.println("fuzz");
                    n++;
                    notifyAll();
                }
                else {
                    wait();
                }
            }
        }
        public synchronized void printBuzzFuzz() throws InterruptedException {
            while (n<=max){
                if(n%3==0 && n%5==0){
                    System.out.println("FuzzBuzz");
                    n++;
                    notifyAll();
                }
                else {
                    wait();
                }
            }
        }
    }
    public static void main(String[] args) {
        FizzBuzzs fizzBuzzs = new FizzBuzzs(20);
        Thread number = new Thread(() -> {
            try {
                fizzBuzzs.printNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread buzz = new Thread(() -> {
            try {
                fizzBuzzs.printBuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread fuzz = new Thread(() -> {
            try {
                fizzBuzzs.printFuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread fizzBuzz = new Thread(() -> {
            try {
                fizzBuzzs.printBuzzFuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        number.start();
        buzz.start();
        fuzz.start();
        fizzBuzz.start();
    }
}
