import hashTable.CustomHashTable;
import hashTable.HashTable;

public class Lesson8Main {
    public static void main(String[] args) {
        chainMethod();
    }

    private static void chainMethod() {
        HashTable<String, String> table = new CustomHashTable<>(5);
        table.put("key1", "value1");
        table.put("key2", "value2");
        table.put("key3", "value3");
        table.put("key4", "value4");
        table.put("key5", "value5");
        table.put("key6", "value6");

        System.out.println("Таблица");
        System.out.println(table.toString());

        System.out.println("Удаление key6");
        table.remove("key6");
        System.out.println(table.toString());


    }
}
