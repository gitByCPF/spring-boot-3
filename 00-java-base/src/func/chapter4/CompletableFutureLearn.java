package func.chapter4;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/7
 * Description: 异步学习 since 1.8
 */
public class CompletableFutureLearn {
    private static final Logger logger = Logger.getLogger(CompletableFutureLearn.class.getName());

    public static void main(String[] args) throws IOException {
        logger.info("开始任务");
        CompletableFuture.runAsync(CompletableFutureLearn::expensiveAction);
        logger.info("执行其他操作");

        // CompletableFuture创建的都是守护线程，守护线程会随着主线程的消亡而消亡，来不及执行就死掉了，因此主程序要阻塞
        System.out.println(System.in.read());
    }

    private static void expensiveAction(){
        try {
            Thread.sleep(3000);
            System.out.println("昂贵操作完毕");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
