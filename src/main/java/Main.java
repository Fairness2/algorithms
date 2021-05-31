import arrayTools.CustomArray;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size = 10000;
        CustomArray<Integer> arr1 = new CustomArray<>(size);
        randomizeArray(arr1, size);
        //System.out.println(arr1.toString());
        arr1.add(99, 3);
        //System.out.println(arr1.toString());
        //System.out.println(arr1.find(99));
        arr1.remove(3);
        //System.out.println(arr1.toString());

        CustomArray<Integer> arr2 = new CustomArray<>(size);
        CustomArray<Integer> arr3 = new CustomArray<>(size);
        randomizeArray(arr2, size);
        randomizeArray(arr3, size);

        long start = System.currentTimeMillis();
        arr1.bubbleSort();
        System.out.printf("Сортировка пузырьком %d мc.%n", (System.currentTimeMillis() - start));
        //System.out.println(arr1.toString());

        start = System.currentTimeMillis();
        arr2.insertSort();
        System.out.printf("Сортировка выбором %d мc.%n", (System.currentTimeMillis() - start));
        //System.out.println(arr2.toString());

        start = System.currentTimeMillis();
        arr3.insertSort();
        System.out.printf("Сортировка вставкой %d мc.%n", (System.currentTimeMillis() - start));
        //System.out.println(arr3.toString());
    }

    private static void randomizeArray(CustomArray<Integer> arr, int size) {
        Random random = new Random();
        int bound = size * 10;
        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt(bound));
        }
    }
}
