package function;

import java.util.function.Consumer;

/**
 * 消费型接口：只有输入没有返回值
 */
public class consumer {
    public static void main(String[] args) {
//        Consumer<String> objectConsumer = new Consumer<String>() {
//            @Override
//            public void accept(String o) {
//                System.out.println(o);
//            }
//        };
        Consumer<String> objectConsumer = (o)->{System.out.println(o);};
        objectConsumer.accept("asdf");
    }
}
