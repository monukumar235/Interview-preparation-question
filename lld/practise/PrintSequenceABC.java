package lld.practise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintSequenceABC {
    public static class PrintABC{
        int turn=0;
        ReentrantLock lock = new ReentrantLock();
        Condition aTurn = lock.newCondition();
        Condition bTurn = lock.newCondition();
        Condition cTurn = lock.newCondition();

        public void printA(){
            lock.lock();
            try{
                if(turn!=0){
                    aTurn.await();
                }else{
                    System.out.println(Thread.currentThread().getName()+" "+"A");
                    turn=1;
                    bTurn.signal();
                }
            }
            catch (Exception e){
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
        public void printB(){
            lock.lock();
            try{
                if(turn!=1){
                    bTurn.await();
                }
                else{
                    System.out.println(Thread.currentThread().getName()+" "+"B");
                    turn=2;
                    cTurn.signal();
                }
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
        public void printC()
        {
            lock.lock();
            try{
                if(turn!=2){
                    cTurn.await();
                }else {
                    System.out.println(Thread.currentThread().getName()+" "+"C");
                    turn=0;
                    aTurn.signal();
                }
            }catch (Exception e)
            {
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();
        Thread t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                printABC.printA();
            }
        },"A-Thread");

        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                printABC.printB();
            }
        },"B-Thread");

        Thread t3 = new Thread(()->{
            for(int i=0;i<10;i++){
                printABC.printC();
            }
        },"C-Thread");

        t1.start();
        t2.start();
        t3.start();
    }

}
