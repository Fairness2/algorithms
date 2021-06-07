package linked;

import dequeue.Dequeue;

public class LinkedDequeue<T> extends LinkedQueue<T> implements Dequeue<T> {
    @Override
    public void leftInsert(T value) {
        innerList.insertLast(value);
    }

    @Override
    public T popRight() {
        return innerList.removeFirst();
    }

    @Override
    public T peekRight() {
        return innerList.getFirst();
    }

    @Override
    public String toReverseStringAndClear() {
        String queue = innerList.toString();
        innerList = new CustomTwoSidesLinkedList<>();
        return queue;
    }
}
