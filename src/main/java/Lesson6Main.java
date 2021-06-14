import tree.CustomTree;

import java.util.ArrayList;
import java.util.Random;

public class Lesson6Main {
    public static void main(String[] args) {
        makeTrees();
    }

    private static void makeTrees() {
        Random random = new Random();
        int treesNumber = 20; //Кол-во деревьев в выборке
        int deep = 4; //Глубина дерева, корень нулевой уровень
        int itemsNumber = (int)Math.pow(2, deep); //Кол-во элементов которыми заполняем
        int balancedTreesNumber = 0;
        ArrayList<CustomTree<Integer>> list = new ArrayList<>(treesNumber);
        for (int i = 0; i < treesNumber; i++) {
            CustomTree<Integer> tree = new CustomTree<>(deep);
            for (int j = 0; j < itemsNumber; j++) {
                tree.add(random.nextInt(50) - 25);
            }
            System.out.println(tree.toString());
            boolean isBalancedTree = tree.isBalanced();
            System.out.printf("Сбалансированное дерево: %s%n", isBalancedTree);
            if (isBalancedTree) {
                balancedTreesNumber++;
            }
            list.add(tree);
        }
        System.out.printf("Количество сбалансированных деревьев: %s, процент полученных сбалансированных деревьев: %s%n", balancedTreesNumber, ((double)balancedTreesNumber / treesNumber) * 100);
    }
}
