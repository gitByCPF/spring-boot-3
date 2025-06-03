package func.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/2
 * Description: 构建流的三种方式
 */
public class BuildStream {
    public static void main(String[] args) {
        // 从集合构建，静态方法定义在Collectors类中
        List.of(1, 2, 3).stream().forEach(System.out::println);
        Map.of("a", 1, "b", 2).entrySet().stream()
                .forEach(System.out::println);
        // 从数组构建
        String[] arr = {"a", "b", "c"};
        Arrays.stream(arr).forEach(System.out::println);
        // 从对象构建
        Stream.of("a", "b", "c").forEach(System.out::println);
        //合并
        Stream<String> stream1 = Stream.of("a", "b", "c");
        Stream<String> stream2 = Stream.of("d", "e", "f");
        Stream<String> mergedStream = Stream.concat(stream1, stream2);
        mergedStream.forEach(System.out::println);
        // 满足条件的都入选，遇到不满足条件的就停止入选这种行为
        Stream.of(1, 2, 3, 4, 10, 5, 6, 7, 8, 9).takeWhile(n -> n < 10)
                .forEach(System.out::println);// 输出 1, 2, 3, 4
        // 满足条件就丢弃，遇到不满足条件的就停止丢弃这种行为
        Stream.of(2, 3, 4, 5, 6, 7, 8).dropWhile(n -> (n & 1) == 0)
                .forEach(System.out::println);// 输出 3, 4, 5, 6, 7, 8
    }
}
