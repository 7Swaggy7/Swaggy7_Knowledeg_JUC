package pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *线程中的通信问题：生产者和消费者的问题 等待唤醒，通知唤醒
 * 线程交替执行A B操作同一个变量 num=0
 * A：num+1
 * B：num-1
 *
 *
 * JUC 即Lock版本！！！！！！
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            for(int i=0;i<=10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"a").start();

        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"b").start();

        new Thread(()->{
            for(int i=0;i<=10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"c").start();

        new Thread(()->{
            for(int i=0;i<=10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"d").start();
    }
}

class Data2{
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //+1
    public  void increment() throws InterruptedException {
        lock.lock();
        try {
            //业务代码
            while(num!=0){
                //等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    //-1
    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while(num==0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            //通知其他线程我-1成功了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
