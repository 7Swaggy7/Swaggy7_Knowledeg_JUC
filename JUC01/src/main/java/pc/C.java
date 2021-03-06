package pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需求：A执行完调用B B执行完调用C。。。。
 */

public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for(int i=0;i<=10;i++){
                data.PrintA();
            }
        },"A").start();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                data.PrintB();
            }
        },"B").start();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                data.PrintC();
            }
        },"C").start();
    }
}

class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;

    public void PrintA(){
        lock.lock();
        try {
            //业务代码, 判断-执行-通知
            while(number!=1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"AAAA");
            //唤醒指定的人
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void PrintB(){
        lock.lock();
        try {
            //业务代码, 判断-执行-通知
            while(number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"BBBB");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void PrintC(){
        lock.lock();
        try {
            //业务代码, 判断-执行-通知
            while(number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"CCCC");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
