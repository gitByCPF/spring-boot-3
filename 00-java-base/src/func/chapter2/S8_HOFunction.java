package func.chapter2;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/20
 * Description: 高阶函数：使用了其他函数对象作为参数或返回值
 * 作用：将通用、复杂的逻辑隐含在高阶函数内，将易变、未定的逻辑放在外部函数对象中
 */
/*
    需求：二叉树遍历、不想自己写二叉树遍历代码、不知道哪种遍历方式更好、对树节点进行只读操作
 */
public class S8_HOFunction {

    public record TreeNode(int value, TreeNode left, TreeNode right) {
        @Override
        public String toString() {
            return "%d".formatted(value);
        }
    }

    public enum Type {PRE, IN, POST}

    public static void main(String[] args) {
        /*
                1
              /   \
             2     3
            /     /  \
           4     5    6
         */
        TreeNode root =
                new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(4, null, null),
                                null),
                        new TreeNode(3,
                                new TreeNode(5, null, null),
                                new TreeNode(6, null, null)));
        traversal(root, Type.PRE, System.out::print);
        System.out.println();
        traversal(root, Type.IN, System.out::print);
        System.out.println();
        traversal(root, Type.POST, System.out::print);
    }

    /**
     * 非递归深度优先搜索策略
     *
     * @param root 根节点
     */
    public static void traversal(TreeNode root, Type type, Consumer<TreeNode> consumer) {
        // 记住来时路
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 当前节点
        TreeNode current = root;
        // 记录最近一次处理过的节点
        TreeNode last = null;
        // 左边还有元素或者还有未归的路
        while (current != null || !stack.isEmpty()) {
            // 左边未走完
            if (current != null) {
                stack.push(current);
                // 这里处理前序遍历的值
                if (type == Type.PRE) {
                    consumer.accept(current);
                }
                current = current.left;
            }
            // 左边已走完
            else {
                // 上次的路
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    // 处理中序遍历、后序遍历的值
                    if (type != Type.PRE) {
                        consumer.accept(peek);
                    }
                    last = stack.pop();

                }
                // 有右子树，但已经走完了
                else if (peek.right == last) {
                    // 处理后序遍历的值
                    if (type == Type.POST) {
                        consumer.accept(peek);
                    }
                    last = stack.pop();
                }
                // 有右子树，未走完
                else {
                    // 处理中序遍历的值
                    if (type == Type.IN) {
                        consumer.accept(peek);
                    }
                    current = peek.right;
                }
            }
        }
    }
}
