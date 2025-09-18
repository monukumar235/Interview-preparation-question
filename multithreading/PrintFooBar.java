package multithreading;

public class PrintFooBar {
    public static class FooBar{
        int turn=0;
        public synchronized void printFoo() throws InterruptedException {
            while (turn%2!=0) wait();
            System.out.println("Foo");
            turn++;
            notifyAll();
        }
        public synchronized void printBar() throws InterruptedException {
            while (turn%2!=1) wait();
            System.out.println("Bar");
            turn++;
            notifyAll();
        }
    }
    public static void main(String[] args) {
        FooBar fooBar = new FooBar();
        Thread foo = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    fooBar.printFoo();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread bar = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    fooBar.printBar();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        foo.start();
        bar.start();
    }
}
