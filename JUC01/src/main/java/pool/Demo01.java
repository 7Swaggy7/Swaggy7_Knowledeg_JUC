package pool;

import java.util.concurrent.*;


//Executors工具类有三大方法
//使用了线程池后，要用线程池来创建线程

public class Demo01 {
    public static void main(String[] args) {
        //最大线程该如何定义
        //1.cpu密集型，几核系统就是几，可以保持CPU运行效率最高，这行代码可以获得核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //2.IO密集型：判断程序中十分耗io的线程，
            //例如15个大型任务，io十分占用资源

        //自定义线程池
        ExecutorService threadpool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
//        ExecutorService threadpool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadpool = Executors.newFixedThreadPool(5);//创建一个固定的线程池大小
//        ExecutorService threadpool = Executors.newCachedThreadPool();//可以伸缩的线程池

        try {
            for (int i = 0; i <9 ; i++) {
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完要关闭
            threadpool.shutdown();
        }
    }
    
}
