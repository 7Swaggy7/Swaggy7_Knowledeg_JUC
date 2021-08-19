package cas;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {

    //CAS
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("a1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a2=>"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());

            },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("b1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 106, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("b2=>"+atomicStampedReference.getStamp());


        },"b").start();




//        //如果期望的值达到了，那么就更新，否则不更新
//        //捣乱的线程
//        System.out.println(atomicInteger.compareAndSet(2020,2021));
//        System.out.println(atomicInteger.get());
//
//        System.out.println(atomicInteger.compareAndSet(2021,2020));
//        System.out.println(atomicInteger.get());
//
//        //期望的线程
//        atomicInteger.compareAndSet(2020,2021);
//        System.out.println(atomicInteger.get());
    }
}
