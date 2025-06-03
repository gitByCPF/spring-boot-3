package func.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/29
 * Description: Stream学习
 */
public class StreamLearn {
    record Fruit(String cname, String name, String category, String color) {
    }

    public static void main(String[] args) {
        // filter是过滤
        // map是映射
        Stream.of(
                        new Fruit("苹果", "Apple", "水果", "红色"),
                        new Fruit("香蕉", "Banana", "水果", "黄色"),
                        new Fruit("橙子", "Orange", "水果", "橙色"),
                        new Fruit("葡萄", "Grape", "水果", "紫色"),
                        new Fruit("草莓", "Strawberry", "水果", "红色"),
                        new Fruit("西瓜", "Watermelon", "水果", "绿色"),
                        new Fruit("杏仁", "Almond", "坚果", "棕色"),
                        new Fruit("核桃", "Walnut", "坚果", "棕色"),
                        new Fruit("腰果", "Cashew", "坚果", "黄色"),
                        new Fruit("开心果", "Pistachio", "坚果", "绿色"))
                .filter(fruit -> "水果".equals(fruit.category))
                .map(Fruit::cname)
                .forEach(System.out::println);

        // 扁平化 降维
        Stream.of(
                        List.of(
                                new Fruit("苹果", "Apple", "水果", "红色"),
                                new Fruit("香蕉", "Banana", "水果", "黄色"),
                                new Fruit("橙子", "Orange", "水果", "橙色"),
                                new Fruit("葡萄", "Grape", "水果", "紫色"),
                                new Fruit("草莓", "Strawberry", "水果", "红色")
                        ),
                        List.of(
                                new Fruit("西瓜", "Watermelon", "水果", "绿色"),
                                new Fruit("杏仁", "Almond", "坚果", "棕色"),
                                new Fruit("核桃", "Walnut", "坚果", "棕色"),
                                new Fruit("腰果", "Cashew", "坚果", "黄色"),
                                new Fruit("开心果", "Pistachio", "坚果", "绿色")
                        )
                )
                .flatMap(List::stream)
                .forEach(System.out::println);
        // 写一个Integer类型的二维数组
        Integer[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        // 扁平化二维数组
        Arrays.stream(array)
                .flatMap(Arrays::stream)
                .forEach(System.out::print);
    }
}
