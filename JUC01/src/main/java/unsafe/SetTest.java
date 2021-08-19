package unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *可以预见Set也是线程不安全的
 */
public class SetTest {
    public static void main(String[] args) {
        //HashSet<String> objects = new HashSet<>();  //可以预见Set也是线程不安全的
       // Set<Object> objects = Collections.synchronizedSet(new HashSet<>()); 工具类 安全
        CopyOnWriteArraySet<String> objects = new CopyOnWriteArraySet<>();
        for (int i = 0; i <= 40; i++) {
            new Thread(()->{
                objects.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(objects);
            },String.valueOf(i)).start();
        }
    }
}
