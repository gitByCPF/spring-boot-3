package func.chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/7
 * Description: 并行流:数据量大才用，否则是浪费资源，效率也不高，线程间交互也需要时间
 * 1.Supplier 创建容器
 * 2.Accumulator 累加
 * 3.Combiner 合并
 * 4.Finish 收尾
 * * * * * * * * * * * * * *
 * 效率比较：
 * 大数据使用并发流效率高，数据少直接用基础流比较好
 * 并行流（不并发）的效率较低，尽量避免使用
 */
public class ParallelStream {
    public static void main(String[] args) {
        List<Integer> collected = Stream.of(1, 2, 3, 4)
                .parallel()
                .collect(Collector.of(
                        () -> {
                            System.out.printf("%-12s %s%n", simple(), "create");
//                            return new ArrayList<Integer>();
                            return new Vector<Integer>();
                        }, // 如何创建容器
                        (list, x) -> {
                            ArrayList<Integer> old = new ArrayList<>(list);
                            list.add(x);
                            System.out.printf("%-12s %s.add(%d)=>%s%n", simple(), old, x, list);
                        }, // 如何向容器中添加数据
                        (list1, list2) -> {
                            ArrayList<Integer> old = new ArrayList<>(list1);
                            list1.addAll(list2);
                            System.out.printf("%-12s %s.add(%s)=>%s%n", simple(), old, list2, list1);
                            return list1;
                        }, // 如何合并两个容器的数据
                        list -> {
                            System.out.printf("%-12s finish: %s=>%s%n", simple(), list, list);
                            return Collections.unmodifiableList(list);
                        } // 收尾
                        // 特性：并发、是否需要收尾、是否要保证收集顺序(默认不支持并发，需要收尾，要保证收集顺序)
                        , Collector.Characteristics.IDENTITY_FINISH // 不需要收尾
                        // 后两者一般是配合使用的，如果要保证顺序，一定是不支持并发的
                        , Collector.Characteristics.UNORDERED // 不需要保证顺序
                        , Collector.Characteristics.CONCURRENT // 支持并发
                ));
        System.out.println(collected);

        // 不可变的集合无法添加元素
         collected.add(10);
        System.out.println(collected);

    }

    public static String simple() {
        String threadName = Thread.currentThread().getName();
        // ForkJoinPool.commonPool-worker-1
        // main
        int start = threadName.indexOf('-');
        return threadName.substring(Math.max(start + 1, 0));
    }
}
