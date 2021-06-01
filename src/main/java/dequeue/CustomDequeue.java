package dequeue;

import Queue.CustomQueue;

public class CustomDequeue<T> extends CustomQueue<T> implements Dequeue<T> {

    public CustomDequeue(int length) {
        super(length);
    }

    @Override
    public void leftInsert(T value) {
        checkLength();
        if (cursor == 0) {
            cursor = innerArray.length;
        }

        innerArray[--cursor] = value;
        size++;
    }

    @Override
    public T popRight() {
        checkEmpty();

        if (last == DEFAULT_CURSOR - 1) {
            last = innerArray.length - 1;
        }

        T value = innerArray[last--];
        size--;

        return value;
    }

    @Override
    public T peekRight() {
        checkEmpty();
        int index = last == DEFAULT_CURSOR - 1 ?  innerArray.length - 1 : last;
        return innerArray[index];
    }


    @Override
    public String toReverseStringAndClear() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while (!isEmpty()) {
            sb.append(popRight()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
