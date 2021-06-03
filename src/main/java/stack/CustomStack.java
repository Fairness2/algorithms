package stack;

public class CustomStack<T> implements Stack<T> {
    private final T[] innerArray;
    private int size;

    public CustomStack(int length) {
        innerArray = (T[]) new Object[length];
        size = 0;
    }

    @Override
    public void push(T value) {
        checkLength();
        innerArray[size++] = value;
    }

    private void checkLength() {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Стек переполнен");
        }
    }

    @Override
    public T pop() {
        T value = peek();
        innerArray[--size] = null;
        return value;
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Стек пуст");
        }
    }

    @Override
    public T peek() {
        checkEmpty();
        return innerArray[size - 1];
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return innerArray.length == size;
    }

    @Override
    public String toStringAndClear() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (!isEmpty()) {
            sb.append(pop()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
