package tree;

import lombok.Data;

@Data
public class Node<T extends Comparable<? super T>> {
    private T value;
    private Node<T> leftNode;
    private Node<T> rightNode;

    public Node(T value) {
        this.value = value;
    }

    public boolean isOneSideNode() {
        return leftNode != null ^ rightNode != null;
    }

    public boolean isLeftNode(T value) {
        return value.compareTo(getValue()) < 0;
    }

    public boolean isLast() {
        return leftNode == null && rightNode == null;
    }
}
