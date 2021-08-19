package function;

import java.util.function.Function;

/**
 * Function函数型接口
 * 只要是函数式接口，就可以用lambda表达式简化
 */
public class function {
    public static void main(String[] args) {
        //工具类，输出输入的值
//        Function function = new Function<String,String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };
        Function<String,String> function = (o)->{return o;};
        System.out.println(function.apply("123"));
    }
}
