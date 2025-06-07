package func.chapter3;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/4
 * Description: 收集流、收集器
 */
public class CollectStream {
    record Hero(String name, int strength) {
    }

    public static void main(String[] args) {
        Supplier<Stream<String>> ss = () -> Stream.of("令狐冲", "无妄", "岳灵珊", "东方不败", "任我行",
                "左冷禅", "孤独求败", "风清扬", "方正", "岳不群", "出虚");
        ArrayList<String> collect = ss.get().collect(ArrayList::new, ArrayList::add, (l1, l2) -> {
        });
        System.out.println(collect);
        LinkedHashSet<String> hashSet = ss.get().collect(LinkedHashSet::new, LinkedHashSet::add, (l1, l2) -> {
        });
        System.out.println(hashSet);

        StringBuilder builder = ss.get().collect(StringBuilder::new, StringBuilder::append, (a, b) -> {
        });
        System.out.println(builder);

        StringJoiner joiner = ss.get().collect(() -> new StringJoiner(","), StringJoiner::add, (a, b) -> {
        });
        System.out.println(joiner);

        // 收集器
        List<String> collectorList = ss.get().collect(Collectors.toList());
        System.out.println(collectorList);
        Set<String> collectorSet = ss.get().collect(Collectors.toSet());
        System.out.println(collectorSet);
        Map<String, Integer> collectorMap = ss.get().distinct().collect(Collectors.toMap(name -> name, String::length));
        System.out.println(collectorMap);

        /*
        分组：
        groupingBy是上游收集器，toList是下游收集器
         */
        Map<Integer, List<String>> group1 = ss.get().collect(Collectors.groupingBy(String::length, Collectors.toList()));
        System.out.println(group1);
        Map<Integer, String> group2 = ss.get().collect(Collectors.groupingBy(String::length, Collectors.joining(",")));
        System.out.println(group2);

        // 1.需求：根据名字长度分组，分组后组内只保留他们的武力值
        Supplier<Stream<Hero>> supplier = () -> Stream.of(
                new Hero("张无忌", 95),
                new Hero("周通", 95),
                new Hero("杨过", 100),
                new Hero("慕容复", 80),
                new Hero("段誉", 85),
                new Hero("虚竹", 90)
        );
        Map<Integer, List<Integer>> practice = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.mapping(Hero::strength, Collectors.toList())));
        System.out.println(practice);

        // 2.需求：根据名字长度分组，分组后组内过滤掉武力值小于90的
        Map<Integer, List<Hero>> groupFilter = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.filtering(h -> h.strength() >= 90, Collectors.toList())));
        System.out.println(groupFilter);

        Map<Integer, List<Hero>> filerGroup = supplier.get().filter(h -> h.strength() >= 90)
                .collect(Collectors.groupingBy(h -> h.name().length(), Collectors.toList()));
        System.out.println(filerGroup);

        // 3.需求：根据名字长度分组，分组后组内保留人名，并且人名切分成单个字符
        Map<Integer, List<String>> practice3 = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.mapping(Hero::name,
                                Collectors.flatMapping(n -> n.chars().mapToObj(Character::toString), Collectors.toList()))));
        System.out.println(practice3);

        // 4.需求：根据名字长度分组，分组后求每组个数
        Map<Integer, Long> groupCount = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.counting()));
        System.out.println(groupCount);

        // 5.需求：根据名字长度分组，分组后求每组武功最低的人
        Map<Integer, Optional<Hero>> groupMin = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.minBy(Comparator.comparingInt(Hero::strength))));
        System.out.println(groupMin);

        // 6.需求，根据名字长度分组，分组后求每组武力和/武力平均值
        Map<Integer, Integer> groupSum = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.summingInt(Hero::strength)));
        System.out.println(groupSum);
        // 另一种求和
        Map<Integer, Integer> groupSum2 = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.mapping(Hero::strength,
                                Collectors.reducing(0, Integer::sum))));
        System.out.println(groupSum2);

        Map<Integer, Double> groupAverage = supplier.get()
                .collect(Collectors.groupingBy(h -> h.name().length(),
                        Collectors.averagingDouble(Hero::strength)));
        System.out.println(groupAverage);


    }
}
