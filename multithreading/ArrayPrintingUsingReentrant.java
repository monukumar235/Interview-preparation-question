package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayPrintingUsingReentrant {
    public static class  PrintList{
        int [] arr1;
        int [] arr2;
        int indx1=0;
        int indx2=0;
        int turn=0;
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        public void printFirst(){
            lock.lock();
            try{
                while (turn!=0) condition1.await();
                while(indx1<arr1.length){
                    System.out.println(arr1[indx1]);
                    indx1++;
                    turn=1;
                    condition2.signal();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
        public void printSecond(){
            lock.lock();
            try{
                while(turn!=1) condition2.await();
                while(indx2<arr2.length){
                    System.out.println(arr2[indx2]);
                    indx2++;
                    turn=0;
                    condition1.signal();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
        PrintList(int []arr1,int[]arr2){
            this.arr1=arr1;
            this.arr2=arr2;
        }

    }
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4};
        int [] arr2 ={5,6,7};
        PrintList printList = new PrintList(arr1,arr2);
        Thread list1 = new Thread(printList::printFirst);
        Thread list2 = new Thread(printList::printSecond);
        list1.start();
        list2.start();
    }
}
