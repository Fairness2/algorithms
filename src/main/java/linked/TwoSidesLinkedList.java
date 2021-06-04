package linked;

public interface TwoSidesLinkedList<T> extends LinkedList<T> {
    void insertLast(T value);
    T getLast();
    T removeLast();
}
