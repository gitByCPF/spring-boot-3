package func.chapter2;

import java.util.List;
import java.util.function.Function;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/16
 * Description: 函数式接口
 */
public class s3 {
    public static void main(String[] args) {
        Type4 type4 = Student::new;
        Type5 type5 = names -> names.stream().map(Student::new).toList();
        // 都可以通过泛型归到Type6类型
        Type6<String, Student> type61 = Student::new;
        Type6<List<String>, List<Student>> type62 = names -> names.stream().map(Student::new).toList();
        // 也可以使用Function接口
        Function<String, Student> function1 = Student::new;
        Function<List<String>, List<Student>> function2 = names -> names.stream().map(Student::new).toList();
    }
}

@FunctionalInterface
interface Type1 {
    boolean op(int a);
}

@FunctionalInterface
interface Type2 {
    int op(int a, int b, int c);
}

@FunctionalInterface
interface Type3 {
    int op(int a, int b);
}

@FunctionalInterface
interface Type4 {
   Student  op(String name);
}

@FunctionalInterface
interface Type5 {
    List<Student> op(List<String> names);
}

@FunctionalInterface
interface Type6<T, R> {
    R op(T t);
}
