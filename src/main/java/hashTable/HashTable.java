package hashTable;

public interface HashTable<K, V> {

    boolean put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

    String toString();

    interface Entry<K, V> {

        K getKey();

        V getValue();

        void setValue(V value);
    }

}