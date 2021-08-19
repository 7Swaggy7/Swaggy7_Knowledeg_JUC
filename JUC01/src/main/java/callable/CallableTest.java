package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * Callable无法直接start，需要通过FutureTask传递callable参数才能使用start
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new Runable()).start();
        //new Thread(new FutureTask<V>()).start();
        new Thread().start();
        MyThread thread = new MyThread();
        //适配类
        FutureTask<String> stringFutureTask = new FutureTask<>(thread);
        new Thread(stringFutureTask,"A").start();
        String s = stringFutureTask.get();
        System.out.println(s);
    }

static class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("call执行了");
        return "456789";
    }
}
}
