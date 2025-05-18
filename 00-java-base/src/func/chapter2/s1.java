package func.chapter2;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/16
 * Description: lambda表达式的形式
 */
public class s1 {
}
/*
 * 1. (int a, int b) -> a + b
 * 2. (int a, int b) -> {int c = a + b; return c; }
 * 3. (a, b) -> a + b
 * 为什么可以省略参数类型？
 * 因为Lambda表达式是一个函数对象的实现，这个函数对象抽象出一个类型，这个类型也是可以推断的
 * 这个类型表明：参数两个，返回值一个，且返回值类型与参数类型的联系要符合Lambda表达式的逻辑
 * interface Lambda1{int op(int a, int b);} 推断出都是int类型
 * interface Lambda2{double op(double a, double b);} 推断出都是double类型
 * 4. a -> a + 1
 */
