package func;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/15
 * Description: 接收函数作为参数
 */
public class Demo1 {
    public static void main(String[] args) {
        // 只有一个方法的接口可以接收同参数的方法
        MathOperation subtract = Demo1::subtract;
        subtract = (a, b) -> a - b;
        subtract = new MathOperation() {
            @Override
            public int apply(int a, int b) {
                return a - b;
            }
        };

        System.out.println(calculate(10, 5, subtract));
        System.out.println(calculate(6, 3, subtract));

    }

    static int subtract(int a, int b) {
        return a - b;
    }

    static int calculate(int a, int b, MathOperation operation) {
        return operation.apply(a, b);
    }
}

interface MathOperation {
    int apply(int a, int b);
}

