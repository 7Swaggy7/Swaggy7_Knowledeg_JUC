package vvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    private volatile static int num = 0;
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{//线程1
            while(num==0){

            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        num = 1;

        System.out.println(num);
    }
}
