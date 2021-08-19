package demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo1 {
    public static void main(String[] args) {
        final Ticket2 ticket = new Ticket2();

        new Thread(()-> {
            for(int i=1;i<60;i++){
                ticket.Sale();
            }
        },"A").start();

        new Thread(()-> {
            for(int i=1;i<60;i++){
                ticket.Sale();
            }
        },"B").start();

        new Thread(()-> {
            for(int i=1;i<60;i++){
                ticket.Sale();
            }
        },"C").start();


    }
}

//synchronized锁
class Ticket{
    private int number = 50;

    public synchronized void Sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"张票"+",还剩"+number);
        }
    }
}

//Lock锁
class Ticket2{
   private int number = 50;

    Lock lock =new ReentrantLock();

    public void Sale(){

        lock.lock();//加锁

        lock.tryLock();//尝试获得锁

        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"张票"+",还剩"+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}