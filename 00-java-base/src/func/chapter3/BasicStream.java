package func.chapter3;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/6
 * Description: 三种基本流 ： 基本流的效率更高
 */
public class BasicStream {
    record Hero(String name, int strength) {
    }

    public static void main(String[] args) {
        IntStream intStream = IntStream.of(97, 98, 99);
        // LongStream longStream = LongStream.of(1L, 2L, 3L);
        // DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);

        intStream.mapToObj(Character::toString).forEach(System.out::println);

        IntSummaryStatistics stat = IntStream.of(1, 2, 3)
                .summaryStatistics();
        System.out.println("Count: " + stat.getCount() +
                ", Sum: " + stat.getSum() +
                ", Min: " + stat.getMin() +
                ", Max: " + stat.getMax() +
                ", Average: " + stat.getAverage());

        Stream.of(
                        new Hero("张无忌", 90),
                        new Hero("周通", 80),
                        new Hero("杨过", 100)
                ).mapToInt(Hero::strength)
                .forEach(System.out::println);
    }
}
