package vvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 */
public class VDemo02 {
    //volatile不保证原子性
    //所以使用原子类的Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add(){
        //num++;
        num.getAndIncrement();
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
}
