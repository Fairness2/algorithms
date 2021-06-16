package hashTable;

import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;

public class CustomHashTable<K, V> implements HashTable<K, V> {
    private final Item<K, V> emptyItem = new Item<>(null, null);
    private final LinkedList<Item<K, V>>[] data;
    private int size;

    public CustomHashTable(int initialCapacity) {
        this.data = new LinkedList[initialCapacity];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        if (data[index] == null) {
            data[index] = new LinkedList<>(); //Возможно стоит перенести в коструктор, чтобы было быстрее и избавится от проверок на null
        }
        data[index].add(new Item<>(key, value));
        size++;

        return true;
    }

    private boolean isKeysEqual(Item<K, V> item, K key) {
        if (item == emptyItem) {
            return false;
        }

        return item.getKey() == null ? key == null : item.getKey().equals(key);
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);
        if (data[index] == null) {
            return null;
        }
        int indexInList = indexOf(key, index);

        return indexInList != -1 ? data[index].get(indexInList).getValue() : null;
    }

    private int indexOf(K key, int index) {
        for (int i = 0; i < data[index].size(); i++) {
            if (isKeysEqual(data[index].get(i), key)) {
                return i;
            }
        }

        return -1;
    }


    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        if (data[index] == null) {
            return null;
        }
        int indexInList = indexOf(key, index);
        if (indexInList == -1) {
            return null;
        }
        Item<K, V> item = data[index].remove(indexInList);
        size--;

        return item.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                for (Item<K, V> item: data[i]) {
                    sb.append(String.format("%d = [%s]%n", i, item));
                }

            }
        }

        return sb.toString();
    }

    @Data
    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s -> %s", key, value);
        }
    }
}
