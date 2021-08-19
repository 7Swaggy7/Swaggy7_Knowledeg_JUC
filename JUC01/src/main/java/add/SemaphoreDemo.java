package add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量:停车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <=6 ; i++) {
            new Thread(()->{
                //acquire()得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //release()释放
                   semaphore.release();
                }
            }).start();
        }
    }
}
