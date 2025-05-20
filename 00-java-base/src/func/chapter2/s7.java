package func.chapter2;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/20
 * Description: 柯里化：将多个参数的函数对象，转化为一系列只有一个参数的函数对象
 * 实现原理：外层函数对象的返回值是一个函数对象，外层函数的对象的参数作为闭包数据传入内层函数对象中
 */
public class s7 {
    @FunctionalInterface
    interface Fa {
        Fb op(int a);
    }

    @FunctionalInterface
    interface Fb {
        int op(int b);
    }

    @FunctionalInterface
    interface F2{
        int op(int a, int b);
    }

    public static void main(String[] args) {
        F2 f2 = Integer::sum;
        System.out.println(f2.op(1, 2));

        // Fa fa = a -> fb;
        // Fb fb = b -> int
        Fa fa = a -> b -> a + b;
        Fb fb = fa.op(10);
        int r = fb.op(20);
        System.out.println(r);
    }
}
