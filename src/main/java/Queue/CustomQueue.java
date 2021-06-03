package Queue;

public class CustomQueue<T> implements Queue<T> {
    protected final T[] innerArray;
    protected int size;
    protected int last;
    protected int cursor;
    protected static final int DEFAULT_CURSOR = 0;

    public CustomQueue(int length) {
        innerArray = (T[]) new Object[length];
        size = 0;
        last = DEFAULT_CURSOR - 1;
        cursor = DEFAULT_CURSOR;
    }

    @Override
    public void insert(T value) {
        checkLength();
        if (last == innerArray.length - 1) {
            last = DEFAULT_CURSOR - 1;
        }

        innerArray[++last] = value;
        size++;
    }

    @Override
    public T popFront() {
        checkEmpty();

        if (cursor == innerArray.length) {
            cursor = DEFAULT_CURSOR;
        }

        T value = innerArray[cursor++];
        size--;

        return value;
    }

    @Override
    public T peekFront() {
        checkEmpty();
        int index = cursor == innerArray.length ? DEFAULT_CURSOR : cursor;
        return innerArray[index];
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
        return size == innerArray.length;
    }

    protected void checkEmpty() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Очередь пуста");
        }
    }

    protected void checkLength() {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Очередь переполнена");
        }
    }

    @Override
    public String toStringAndClear() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (!isEmpty()) {
            sb.append(popFront()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
