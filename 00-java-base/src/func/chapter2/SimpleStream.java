package func.chapter2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/28
 * Description: 模仿Stream的简单实现
 */
public class SimpleStream<T> {
    private final Collection<T> collection;

    private SimpleStream(Collection<T> collection) {
        this.collection = collection;
    }

    public static <T> SimpleStream<T> of(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return new SimpleStream<>(new ArrayList<>());
        }
        return new SimpleStream<>(new ArrayList<>(collection));
    }

    public SimpleStream<T> filter(Predicate<T> predicate) {
        collection.removeIf(item -> !predicate.test(item));
        return this;
    }

    public <U> SimpleStream<U> map(Function<T, U> function) {
        List<U> mappedList = collection.stream()
                .map(function)
                .collect(Collectors.toList());
        return new SimpleStream<>(mappedList);
    }

    public void forEach(Consumer<? super T> action) {
        collection.forEach(action);
    }

    public T reduce(BinaryOperator<T> operator) {
        Iterator<T> iterator = collection.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = operator.apply(result, iterator.next());
        }
        return result;
    }

    /**
     * 使用提供的supplier创建一个新的集合，并使用consumer对每个元素进行操作
     *
     * @param supplier 供应商，用于创建新的集合
     * @param consumer 消费者，对每个元素进行操作
     * @param <C>      集合类型
     * @return 新的集合
     */
    public <C> C collection(Supplier<C> supplier, BiConsumer<C, T> consumer) {
        C c = supplier.get();
        for (T item : collection) {
            consumer.accept(c, item);
        }
        return c;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 使用自定义的SimpleStream类进行链式操作
        SimpleStream.of(list)
                .filter(x -> (x & 1) == 1) // 留下奇数
                .map(x -> x * x) // 平方
                .forEach(System.out::println); // 打印结果

        System.out.println("Sum: " + SimpleStream.of(list).reduce(Integer::sum));
        System.out.println("Max: " + SimpleStream.of(list).reduce(Integer::max));
        System.out.println("Min: " + SimpleStream.of(list).reduce(Integer::min));

        List<Integer> repeatList = List.of(1, 3, 6, 3, 2, 5, 3, 6, 2, 1, 3, 7);
        // 使用自定义的SimpleStream类进行去重操作
        HashSet<Object> dupRemoval = SimpleStream.of(repeatList).collection(HashSet::new, HashSet::add);
        System.out.println("去重后的集合: " + dupRemoval);

        StringJoiner joinerCollection = SimpleStream.of(repeatList)
                .collection(() -> new StringJoiner(", ", "[", "]"),
                        (joiner, item) -> joiner.add(item.toString()));
        // 第二种方式
        joinerCollection = SimpleStream.of(repeatList)
                .map(Object::toString)
                .collection(() -> new StringJoiner(", ", "[", "]"), StringJoiner::add);

        System.out.println("使用StringJoiner连接的集合: " + joinerCollection);

        // 统计每个数字出现的次数
        HashMap<Integer, Integer> countResult = SimpleStream.of(repeatList)
                .collection(HashMap::new, (hashMap, integer) -> {
                    if (hashMap.containsKey(integer)) {
                        hashMap.put(integer, hashMap.get(integer) + 1);
                    } else {
                        hashMap.put(integer, 1);
                    }
                });
        System.out.println(countResult);

        // 简便写法
        HashMap<Integer, AtomicInteger> simpleWay = SimpleStream.of(repeatList)
                .collection(HashMap::new,
                        (map, item) -> map.computeIfAbsent(item, k -> new AtomicInteger()).getAndIncrement());
        System.out.println(simpleWay);


    }
}
