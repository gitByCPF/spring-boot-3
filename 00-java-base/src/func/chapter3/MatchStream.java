package func.chapter3;

import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/3
 * Description: 判断流
 */
public class MatchStream {
    public static void main(String[] args) {
        Supplier<IntStream> ss = () -> IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println(ss.get().anyMatch(n -> (n & 1) == 0)); // True

        System.out.println(ss.get().allMatch(n -> (n & 1) == 0)); // False

        System.out.println(ss.get().noneMatch(n -> (n & 1) == 0)); // False
    }
}
