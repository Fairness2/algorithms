package dequeue;

import Queue.Queue;

public interface Dequeue<T> extends Queue<T> {
    void leftInsert(T value);
    T popRight();
    T peekRight();
    String toReverseStringAndClear();
}
