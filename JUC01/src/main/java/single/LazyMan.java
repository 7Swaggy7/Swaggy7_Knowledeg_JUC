package single;
//懒汉式单例

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class LazyMan {

    private static boolean liuhao = false;

    private LazyMan(){
        synchronized (LazyMan.class) {
            if (liuhao == false) {
                liuhao = true;
            } else {
                throw new RuntimeException("不要试图通过反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName()+"ok");
    }
    public volatile static LazyMan lazyMan;

    public static LazyMan getInstance(){
        //双重检测锁模式的懒汉式单例 简称DCL懒汉式
        if(lazyMan==null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }

//    //多线程并发
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
//    }

    //反射
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //LazyMan lazyMan1 = LazyMan.getInstance();

        Field liuhao = LazyMan.class.getDeclaredField("liuhao");
        liuhao.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        LazyMan lazyMan1 = declaredConstructor.newInstance();

        liuhao.set(lazyMan1,false);

        LazyMan lazyMan2 = declaredConstructor.newInstance();

        System.out.println(lazyMan1);
        System.out.println(lazyMan2);
    }
}
