package func.chapter2;

import java.util.function.Function;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/20
 * Description: 柯里化的实际应用
 * 假设需要根据用户等级（普通/VIP）和促销活动动态计算商品最终价格。
 */
public class s7_Exercise {
    public static void main(String[] args) {
        double basePrice = 1399.9;
        // 传统方法
        System.out.println(calculatePrice(basePrice, "USER", true));
        System.out.println(calculatePrice(basePrice, "VIP", true));
        // 柯里化尝试
        // 业务逻辑固定
        Function<Double, Function<String, Function<Boolean, Double>>> discount =
                finalBasePrice -> userLevel -> isPromotion ->
                        calculatePrice(finalBasePrice, userLevel, isPromotion);

        Function<String, Function<Boolean, Double>> baseCalculator = discount.apply(basePrice);
        // 能延迟决定是否是VIP
        Function<Boolean, Double> vipCalculator = baseCalculator.apply("VIP");
        // 能延迟决定是否促销
        Double finalPrice = vipCalculator.apply(true);
        System.out.println(finalPrice);
    }

    // 传统方法
    static double calculatePrice(double basePrice, String userLevel, boolean isPromotion) {
        double discount = userLevel.equals("VIP") ? 0.2 : 0.1;
        if (isPromotion) discount += 0.05;
        return basePrice * (1 - discount);
    }
}
