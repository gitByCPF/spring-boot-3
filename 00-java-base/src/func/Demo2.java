package func;

import java.util.function.Function;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/15
 * Description: 返回函数作为结果
 */
public class Demo2 {
    public static void main(String[] args) {
        Function<String, String> transformer = createTransformer(s -> s.substring(0, 2));
        System.out.println(transformer.apply("abcdef")); // AB

        Function<String, String> transformer2 = createTransformer(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.substring(0, 2);
            }
        });


    }

    static Function<String,String > createTransformer(Function<String, String> f) {
        return f.andThen(String::toUpperCase);
    }
}
