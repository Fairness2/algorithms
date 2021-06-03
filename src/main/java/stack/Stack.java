package stack;

public interface Stack<T> {
    void push(T value);
    T pop();
    T peek();
    int length();
    boolean isEmpty();
    boolean isFull();
    String toStringAndClear();
}
