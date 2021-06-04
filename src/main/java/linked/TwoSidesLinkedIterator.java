package linked;

public interface TwoSidesLinkedIterator<T> extends LinkedIterator<T> {
    boolean hasPrevious();
    boolean getPrevious();
}
