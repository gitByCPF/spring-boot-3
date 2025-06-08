package func.chapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/6/7
 * Description: 线程池学习
 */
public class ThreadPoolLearn {
    private static final Logger logger = Logger.getLogger(ThreadPoolLearn.class.getName());

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        logger.info("开始统计");
        service.submit(ThreadPoolLearn::expensiveAction);
        logger.info("执行其他操作");
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
