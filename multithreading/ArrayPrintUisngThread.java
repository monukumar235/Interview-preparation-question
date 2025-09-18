package multithreading;

public class ArrayPrintUisngThread {
    public static class Print{
        int[] arr1;
        int []arr2;
        int indx1=0;
        int indx2=0;
        boolean turn=true;
        Print(int[]arr1,int[]arr2)
        {
            this.arr1=arr1;
            this.arr2=arr2;
        }
        public synchronized void printList1() throws InterruptedException {
            while (indx1<=arr1.length-1){
                while (!turn) wait();
                System.out.println(arr1[indx1]);
                indx1++;
                turn=false;
                notifyAll();
            }
        }

        public synchronized void printList2() throws InterruptedException {
            while (indx2<=arr2.length-1){
                while (turn) wait();
                System.out.println(arr2[indx2]);
                indx2++;
                turn=true;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        int[] arr1={1,2,3,4};
        int[]arr2 ={5,6,7};
        Print print = new Print(arr1,arr2);
        Thread list1 = new Thread(() -> {
            try {
                print.printList1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread list2 = new Thread(() -> {
            try {
                print.printList2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        list1.start();
        list2.start();
    }
}
