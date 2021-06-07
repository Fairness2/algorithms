package linked;

import stack.Stack;

public class LinkedStack<T> implements Stack<T> {
    private CustomLinkedList<T> innerList;

    public LinkedStack() {
        innerList = new CustomLinkedList<>();
    }

    @Override
    public void push(T value) {
        innerList.insertFirst(value);
    }

    @Override
    public T pop() {
        return innerList.removeFirst();
    }

    @Override
    public T peek() {
        return innerList.getFirst();
    }

    @Override
    public int length() {
        return innerList.size();
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
        String stack = innerList.toString();
        innerList = new CustomLinkedList<>();
        return stack;
    }

    @Override
    public String toString() {
        return innerList.toString();
    }
}
