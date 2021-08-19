package unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
       // ArrayList<String> objects = new ArrayList<>(); 这样做会造成线程不安全 因为arraylist不安全
        //List<Object> objects = Collections.synchronizedList(new ArrayList<>()); 安全
        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<>();

        for (int i = 0; i <= 10; i++) {
            new Thread(()->{
                objects.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(objects);
            },String.valueOf(i)).start();
        }
    }
}
