package tree;

public interface Tree<T extends Comparable<? super T>> {
    boolean add(T value);
    boolean contains(T value);
    boolean remove(T value);
    boolean isEmpty();
    int size();
    String toString();
    boolean isBalanced();
}
