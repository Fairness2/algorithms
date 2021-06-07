package linked;

public interface LinkedNode<T> {
    LinkedNode<T> getPrevious();
    void setPrevious(LinkedNode<T> previous);
    LinkedNode<T> getNext();
    void setNext(LinkedNode<T> next);
    T getItem();
    void setItem(T item);
}
