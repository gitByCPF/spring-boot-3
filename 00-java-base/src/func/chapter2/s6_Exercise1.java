package func.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/20
 * Description: 练习闭包，闭包的作用
 */
public class s6_Exercise1 {
    public static void main(String[] args) {
        // 创建十个任务对象
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int index = i + 1; // 这里的index是一个局部变量，必须是final或effectively final
            Runnable task = () -> System.out.println("执行任务" + index);
            tasks.add(task);
        }
        tasks.forEach(Runnable::run); // 执行所有任务
    }
}
