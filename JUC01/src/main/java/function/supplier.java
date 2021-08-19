package function;

import java.util.function.Supplier;

/**
 * 供给型接口，没有参数，只有返回值
 */
public class supplier {
    public static void main(String[] args) {
//        Supplier<Integer> objectSupplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("get()");
//                return 1024;
//            }
//        };
        Supplier<Integer> objectSupplier = ()->{
            System.out.println("get()");
            return 1024;
        };
        System.out.println(objectSupplier.get());
    }
}
