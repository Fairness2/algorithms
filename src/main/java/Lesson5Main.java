import backpack.Backpack;
import backpack.Item;
import backpack.Kit;

import java.util.ArrayList;

public class Lesson5Main {
    public static void main(String[] args) {
        //Возведение в степень
        float number = 2;
        int degree = 4;
        System.out.printf("Возведение числа %s в степерь %s%n", number, degree);
        float val = exponentiation(number, degree);
        System.out.printf("Результат %s%n", val);

        //Задача о рюкзаке
        System.out.println();
        packBackpack();
    }

    private static float exponentiation(float number, int degree) {
        if (degree == 0) {
            return 1;
        }
        float val = processExponentiation(1, number, Math.abs(degree));
        return degree < 0 ? (1 / val) : val;
    }

    private static float processExponentiation(float initVal, float number, int degree) {
        if (degree == 0) {
            return initVal;
        }
        return processExponentiation(initVal * number, number, --degree);
    }


    private static void packBackpack() {

        Item[] set = new Item[] {
                Item.builder().name("Книга").price(600).weight(1).build(),
                Item.builder().name("Бинокль").price(5000).weight(2).build(),
                Item.builder().name("Аптечка").price(1500).weight(4).build(),
                Item.builder().name("Ноутбук").price(40000).weight(2).build(),
                Item.builder().name("Котелок").price(500).weight(1).build(),
        };
        Backpack backpack = new Backpack(6);

        Kit kit = new Kit(set, backpack);
        ArrayList<Backpack> backpacks = kit.getOptimalKits();
        System.out.println("Оптимальыне рбюкзаки:");
        for (Backpack optimalBackpack: backpacks) {
            System.out.println(optimalBackpack.toString());
        }
    }
}





