package func.chapter2;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/19
 * Description: 练习题
 */
public class s5_Exercise1 {
    public static void main(String[] args) {
        // Color::new来构造对象
        IntTriFunction<Color> constructor = Color::new;
        Color color = constructor.apply(1, 2, 3);
        System.out.println(color);

    }

}

record Color(Integer red, Integer green, Integer blue) {
}

@FunctionalInterface
interface IntTriFunction<R> {
    R apply(Integer first, Integer second, Integer third);
}
