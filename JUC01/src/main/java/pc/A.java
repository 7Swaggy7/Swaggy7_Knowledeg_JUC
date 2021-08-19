package pc;

/**
 *线程中的通信问题：生产者和消费者的问题 等待唤醒，通知唤醒
 * 线程交替执行A B操作同一个变量 num=0
 * A：num+1
 * B：num-1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

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

class Data{
    private int num = 0;
    //+1
        public synchronized void increment() throws InterruptedException {
            while(num!=0){
                //等待
                this.wait();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            //通知其他线程我+1成功了
            this.notifyAll();
        }


    //-1
        public synchronized void decrement() throws InterruptedException {
            while(num==0){
                this.wait();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            //通知其他线程我-1成功了
            this.notifyAll();
        }
}
