package func.chapter3;

import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/3
 * Description: 去重与排序
 */
public class SortStream {
    record Hero(String name, int strength) {
    }

    public static void main(String[] args) {
        Supplier<Stream<Hero>> heroSupplier = () -> Stream.of(
                new Hero("张无忌", 95),
                new Hero("周通", 95),
                new Hero("杨过", 100),
                new Hero("段誉", 85),
                new Hero("虚竹", 90),
                new Hero("虚竹", 90)
        );
        // 去重
        heroSupplier.get()
                .distinct()
                .forEach(hero -> System.out.println("去重: " + hero.name() + ", 力量: " + hero.strength));
        System.out.println("====================================");
        // 排序
        heroSupplier.get()
                .distinct()
                .sorted(Comparator.comparingInt(Hero::strength))
                .forEach(hero -> System.out.println("排序: " + hero.name() + ", 力量: " + hero.strength));
        System.out.println("====================================");
        // 排序（降序）
        heroSupplier.get()
                .distinct()
                .sorted(Comparator.comparingInt(Hero::strength).reversed())
                .forEach(hero -> System.out.println("降序排序: " + hero.name() + ", 力量: " + hero.strength));
        System.out.println("====================================");
        // 排序（自定义比较器）
        heroSupplier.get()
                .distinct()
                .sorted(Comparator.comparing(Hero::strength).thenComparingInt(hero -> hero.name().length()).reversed())
                .forEach(hero -> System.out.println("自定义排序: " + hero.name() + ", 力量: " + hero.strength));
    }
}
