package func.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/16
 * Description: 常见的函数式接口
 */
public class s4 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        // 例子2
        consumer(list, System.out::println);
        // 例子3
        List<Integer> supply = supply(5, ThreadLocalRandom.current()::nextInt);
        System.out.println(supply);
    }
    // 例子1
    static List<String> map(List<Integer> list){
        List<String> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(String.valueOf(i));
        }
        return result;
    }
    // 改写
    static List<String> map(List<Integer> list, Function<Integer, String> function){
        List<String> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(function.apply(i));
        }
        return result;
    }

    // 例子2
    static void consumer(List<Integer> list){
        for (Integer i : list) {
            System.out.println(i);
        }
    }
    // 改写
    static void consumer(List<Integer> list, IntConsumer consumer){
        for (Integer i : list) {
            consumer.accept(i);
        }
    }

    // 例子3
    static List<Integer> supply(int count){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(ThreadLocalRandom.current().nextInt());
        }
        return result;
    }
    // 改写
    static List<Integer> supply(int count, IntSupplier supplier){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(supplier.getAsInt());
        }
        return result;
    }


}
/*
1. Runnable
    () -> void
2. Callable
    () -> T
3. Comparator
    (T, T) -> int
4.  Consumer         BiConsumer      IntConsumer     DoubleConsumer      LongConsumer
    (T) -> void     (T, U) -> void  (int) -> void   (double) -> void   (long) -> void
5. Function         BiFunction      IntFunction     DoubleFunction      LongFunction
    (T) -> R        (T, U) -> R     (int) -> R      (double) -> R      (long) -> R
6. Predicate        BiPredicate          IntPredicate       DoublePredicate         LongPredicate
(T) -> boolean  (T, U) -> boolean   (int) -> boolean    (double) -> boolean      (long) -> boolean
7. Supplier        BiSupplier      IntSupplier     DoubleSupplier      LongSupplier
    () -> T         () -> U        () -> int      () -> double       () -> long
8. UnaryOperator    BinaryOperator   IntBinaryOperator      DoubleBinaryOperator    LongBinaryOperator
    (T) -> T        (T, T) -> T     (int, int) -> int   (double, double) -> double  (long, long) -> long
*/
