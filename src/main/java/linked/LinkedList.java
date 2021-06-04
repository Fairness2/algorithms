package linked;

public interface LinkedList<T> extends Iterable<T> {
    void insertFirst(T value);
    T removeFirst();
    void remove(T value);
    boolean contains(T value);
    int size();
    boolean isEmpty();
    String toString();
    T getFirst();
}
