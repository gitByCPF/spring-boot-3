package func.chapter2;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/19
 * Description: 闭包：函数对象与外部变量绑定在一块儿
 * 闭包的作用：给函数执行提供数据的支持
 */
public class s6 {
    public static void main(String[] args) {
        /*
        函数对象与外部的变量x形成了闭包;
        外部变量必须是final或effectively final(行为与final相同)，因为函数对象的逻辑要固定;
         */
        int x = 10;
        highOrder(y -> x + y);

        // 只限制了最外层，但对象内部的变量依然可以变，这里就说明不是严格的函数式编程
        Boy boy = new Boy(6);
        highOrder(y -> boy.x + y);
        boy.x = 20;
    }

    static void highOrder(Lambda lambda) {
        System.out.println(lambda.op(5));
    }

    static class Boy{
        int x = 10;

        public Boy(int x) {
            this.x = x;
        }
    }
}

@FunctionalInterface
interface Lambda {
    int op(int y);
}
