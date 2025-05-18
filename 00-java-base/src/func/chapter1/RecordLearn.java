package func.chapter1;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/16
 * Description: java16引入的record关键字
 */
record RecordLearn(String name){}

/*
上面的类等价于下面的写法：
    1. 生成了一个final类
    2. 生成了一个私有的final属性
    3. 生成了一个全参数构造方法
    4. 生成了一个类似于getter的方法，但是没有动词get，例如name就是Student.name()而不是getName()
    5. 生成了一个toString方法
    6. 生成了一个equals方法
    7. 生成了一个hashCode方法

public final class func.chapter1.RecordLearn {
    private final String name;

    public RecordLearn1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RecordLearn1{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordLearn1)) return false;
        RecordLearn1 that = (RecordLearn1) o;
        return name.equals(that.name);
    }
}
*/
