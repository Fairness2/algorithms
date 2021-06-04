package linked;

import Queue.Queue;

public class LinkedQueue<T> implements Queue<T> {
    protected CustomTwoSidesLinkedList<T> innerList;

    public LinkedQueue() {
        innerList = new CustomTwoSidesLinkedList<>();
    }

    @Override
    public void insert(T value) {
        innerList.insertFirst(value);
    }

    @Override
    public T popFront() {
        return innerList.removeLast();
    }

    @Override
    public T peekFront() {
        return innerList.getLast();
    }

    @Override
    public int length() {
        return innerList.size;
    }

    @Override
    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toStringAndClear() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (!isEmpty()) {
            sb.append(popFront()).append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}
