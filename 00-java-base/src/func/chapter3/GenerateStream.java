package func.chapter3;


import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/3
 * Description: 生成流
 */
public class GenerateStream {
    public static void main(String[] args) {
        // int流 范围
//        IntStream.range(1, 10).forEach(System.out::println); // 输出1-9
//        IntStream.rangeClosed(1, 10).forEach(System.out::println); // 输出1-10

        // 等差数列（迭代）
//        IntStream.iterate(1, n -> n * n < 100, n -> n + 2)
//                .forEach(System.out::println); // 输出1, 3, 5, 7, 9

        // 无限流（生成）
        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100))
                .limit(10) // 限制生成10个元素
                .forEach(System.out::println); // 输出10个随机整数
        // 更简便方式
        ThreadLocalRandom.current().ints(10, 0, 100) // 生成10个0-100之间的随机整数
                .forEach(System.out::println); // 输出10个随机整数

    }
}
