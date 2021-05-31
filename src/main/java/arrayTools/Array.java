package arrayTools;

public interface Array<T> {
    void remove(int index);
    void add(T value);
    void add(T value, int index);
    T get(int index);
    int find(T value);
    void bubbleSort();
    void selectSort();
    void insertSort();
    int length();
    String toString();
}
