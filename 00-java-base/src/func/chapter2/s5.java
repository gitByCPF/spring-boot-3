package func.chapter2;

import java.io.File;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/17
 * Description: 方法引用深入，s2只是一个简单入门
 * 1.静态方法       Integer::parseInt
 * 2.非静态方法      Student::getName
 *                 对象名::方法名
 *                 this::非静态方法
 *                 super::非静态方法
 * 3.构造方法       Student::new
 * 本质上，只要参数和返回值对应得上，就可以使用方法引用，只是形式有区别
 */
public class s5 {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("3"),
                new Student("4"),
                new Student("5"));

        List<Student> studentList = students.stream()
                .map(Student::name)// 非静态方法
                .map(Integer::parseInt) // 静态方法
                .map(s -> s * 2)
                .map(String::valueOf)
                .map(Student::new)//构造方法
                .toList();
        System.out.println(studentList);

        // 练习题
        Function<String, Integer> lambda1 = Integer::parseInt;
        BiPredicate<List<String>,String> lambda2 = List::contains;
        BiPredicate<Student, Object> lambda3 = Student::equals;
        Predicate<File> lambda4 = File::exists;
        Supplier<Runtime> runtimeSupplier = Runtime::getRuntime;
        Supplier<Long> lambda5 = runtimeSupplier.get()::freeMemory;
    }
}
