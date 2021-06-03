package Queue;

public interface Queue<T> {
    void insert(T value);
    T popFront();
    T peekFront();
    int length();
    boolean isEmpty();
    boolean isFull();
    String toStringAndClear();
}
