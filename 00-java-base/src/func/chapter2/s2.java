package func.chapter2;

import java.util.List;
import java.util.function.IntBinaryOperator;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/16
 * Description: 方法引用
 */
public class s2 {
    public static void main(String[] args) {
        // 写一个可以使用Math::max的例子
        IntBinaryOperator max = Math::max;
        System.out.println(max.applyAsInt(1, 2)); // 2

        // Student::getName
        List<Student> students = List.of(new Student("Alice"), new Student("Bob"));
        students.stream()
                .map(Student::name)
                .forEach(System.out::println); // Alice Bob

        // Student::new
        List<String> names = List.of("Alice", "Bob");
        List<Student> studentList = names.stream()
                .map(Student::new)
                .toList();

    }
}
