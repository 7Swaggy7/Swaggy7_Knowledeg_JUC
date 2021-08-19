package function;

import java.util.function.Predicate;

/**
 * 断定型接口，有一个输入参数，返回值只能是布尔值
 */
public class predicate {
    public static void main(String[] args) {
//        //判断字符串是否为空
//        Predicate<String> objectPredicate = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };
        Predicate<String> objectPredicate = (o)->{return o.isEmpty();};
        System.out.println(objectPredicate.test("123"));
    }
}
