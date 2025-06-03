package func.chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/3
 * Description: 查找判断流
 */
public class FindStream {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("▶ 执行第 1 组：串行流");
        runThreads(() -> IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(n -> n % 2 == 0)
                .findAny()
                .ifPresentOrElse(
                        n -> System.out.println("找到偶数: " + n),
                        () -> System.out.println("没有找到偶数")
                ));

        System.out.println("▶ 执行第 2 组：并行流");
        runThreads(() -> IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallel()
                .filter(n -> n % 2 == 0)
                .findAny()
                .ifPresentOrElse(
                        n -> System.out.println("找到偶数: " + n),
                        () -> System.out.println("没有找到偶数")
                ));

        System.out.println("▶ 执行第 3 组：并行 + 打乱顺序");
        runThreads(() -> {
            List<Integer> lists = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
            Collections.shuffle(lists);
            lists.parallelStream()
                    .filter(n -> (n & 1) == 0)
                    .findAny()
                    .ifPresentOrElse(
                            n -> System.out.println("找到偶数: " + n),
                            () -> System.out.println("没有找到偶数")
                    );
        });
    }

    /**
     * 启动 5 个线程执行任务，并等待它们执行完成
     */
    public static void runThreads(Runnable task) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        // 启动 5 个线程
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }

        // 等待所有线程执行完
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("✅ 这一组线程执行完毕\n");
    }
}

