package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用 CompletableFuture
 * //异步执行
 * //成功回调
 * //异步回调
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //发起一个请求
        //没有返回值的异步回调
//        CompletableFuture<Void> objectCompletableFuture = new CompletableFuture().runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println(Thread.currentThread().getName()+"runAsync=>void");
//            }
//        });
//        System.out.println("1111");
//        objectCompletableFuture.get();//获取执行结果


        //有返回值的异步回调
        CompletableFuture<Integer> objectCompletableFuture = new CompletableFuture().supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"supplyAsync=Integer");
            return 1024;
        });
        objectCompletableFuture.whenComplete((t,u)->{
            System.out.println("t=>"+t);
            System.out.println("u=>"+u);
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 2333;
        }).get();
    }
}
