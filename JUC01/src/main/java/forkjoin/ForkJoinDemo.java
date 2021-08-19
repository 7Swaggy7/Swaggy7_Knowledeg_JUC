package forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算
 * 如何使用forkJoin
 * 1.forkJoinPool 通过他来执行
 * 2.计算任务 forkJoinPool.execute(ForkJoinTask task)
 *
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    //临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //计算方法
    @Override
    protected Long compute() {
        if ((end-start)>temp){
            //分支合并计算
            Long sum = 0L;
            for (Long i = start; i <end ; i++) {
                sum += i;
            }
             return sum;
        }else {//forkjoin
            long middle = (start+end)/2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, end);
            task2.fork();
            return task1.join()+task2.join();
        }
    }
}
