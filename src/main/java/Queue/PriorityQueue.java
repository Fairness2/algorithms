package Queue;

public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T> {
    protected final T[] innerArray;
    protected int size;

    public PriorityQueue(int length) {
        innerArray = (T[]) new Comparable[length];
        size = 0;
    }

    // O(N)
    @Override
    public void insert(T value) {
        checkLength();

        int index;
        for (index = size - 1; index >= 0; index--) {
            if (value.compareTo(innerArray[index]) > 0) {
                innerArray[index + 1] = innerArray[index];
            } else {
                break;
            }
        }

        innerArray[index + 1] = value;
        size++;
    }

    @Override
    public T popFront() {
        checkEmpty();
        return innerArray[--size];
    }

    @Override
    public T peekFront() {
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
