package func.chapter3;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/3
 * Description: 化简流:最大值，最小值，求和，求个数
 */
public class ReduceStream {
    record Hero(String name, int strength) {
    }

    public static void main(String[] args) {
        Supplier<Stream<Hero>> supplier = () -> Stream.of(
                new Hero("张无忌", 95),
                new Hero("周通", 95),
                new Hero("杨过", 100),
                new Hero("慕容复", 80),
                new Hero("段誉", 85),
                new Hero("虚竹", 90)
        );

        supplier.get()
                .reduce((h1, h2) -> h1.strength() > h2.strength() ? h1 : h2)
                .ifPresent(System.out::println);

        System.out.println(supplier.get()
                .reduce(new Hero("init", -1),
                        BinaryOperator.maxBy(Comparator.comparing(Hero::strength))));

        // 求高手的数量
        System.out.println(supplier.get()
                .map(h -> 1) // 将每个英雄映射为1
                .reduce(0, Integer::sum)); // 使用reduce求和

        // Stream提供了现成的API
        System.out.println(supplier.get().count()); // 直接获取个数
        System.out.println(supplier.get().max(Comparator.comparingInt(Hero::strength))
                .orElse(new Hero("无", 0))); //  获取最大值，若无则返回默认英雄

        supplier.get().mapToInt(Hero::strength)
                .reduce(Integer::sum) // 求和
                .ifPresent(sum -> System.out.println("总力量: " + sum)); // 输出总力量

    }
}
