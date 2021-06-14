package tree;

import java.util.Stack;

public class CustomTree<T extends Comparable<? super T>> implements Tree<T> {
    private int size;
    private Node<T> root;
    private Node<T> current;
    private Node<T> previous;
    private final int deep;

    public CustomTree(int deep) {
        this.deep = deep;
    }

    @Override
    public boolean add(T value) {
        Node<T> node = new Node<>(value);

        int placeDeep = findPlace(value);
        if (current != null || placeDeep > deep) {
            return false;
        }
        Node<T> previous = this.previous;
        if (previous == null) {
            root = node;
        } else if (previous.isLeftNode(value)) {
            previous.setLeftNode(node);
        } else {
            previous.setRightNode(node);
        }
        size++;
        return true;
    }

    private int findPlace(T value) {
        this.current = null;
        this.previous = null;
        Node<T> current = root;
        Node<T> previous = null;
        int deep = 0;
        while (current != null) {
            if (current.getValue().equals(value)) {
                this.current = current;
                this.previous = previous;
                return deep;
            }

            previous = current;
            if (current.isLeftNode(value)) {
                current = current.getLeftNode();
            } else {
                current = current.getRightNode();
            }
            deep++;
        }

        this.previous = previous;
        return deep;
    }

    @Override
    public boolean contains(T value) {
        findPlace(value);
        return current != null;
    }

    @Override
    public boolean remove(T value) {
        this.previous = null;
        this.current = null;

        findPlace(value);
        if (this.current == null) {
            return false;
        }

        if (current.isLast()) {
            removeLastNode(current, previous);
        } else if (current.isOneSideNode()) {
            removeNodeWithOnlyOneSide(current, previous);
        } else {
            removeNodeWithAllSide(current, previous);
        }

        size--;
        return true;
    }

    private void removeNodeWithAllSide(Node<T> removedNode, Node<T> parentNode) {
        Node<T> successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        } else if (parentNode.isLeftNode(removedNode.getValue())) {
            parentNode.setLeftNode(successor);
        } else {
            parentNode.setRightNode(successor);
        }

        successor.setLeftNode(removedNode.getLeftNode());
    }

    private Node<T> getSuccessor(Node<T> removedNode) {
        Node<T> successor = removedNode;
        Node<T> successorParent = null;
        Node<T> current = removedNode.getRightNode();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftNode();
        }

        if (successor != removedNode.getRightNode() && successorParent != null) {
            successorParent.setLeftNode(successor.getRightNode());
            successor.setRightNode(removedNode.getRightNode());
        }

        return successor;
    }

    private void removeNodeWithOnlyOneSide(Node<T> removedNode, Node<T> parentNode) {
        Node<T> childNode = removedNode.getLeftNode() != null
                ? removedNode.getLeftNode()
                : removedNode.getRightNode();

        if (removedNode == root) {
            root = childNode;
        } else if (parentNode.isLeftNode(removedNode.getValue())) {
            parentNode.setLeftNode(childNode);
        } else {
            parentNode.setRightNode(childNode);
        }
    }

    private void removeLastNode(Node<T> removedNode, Node<T> parentNode) {
        if (removedNode == root) {
            root = null;
        } else if (parentNode.isLeftNode(removedNode.getValue())) {
            parentNode.setLeftNode(null);
        } else {
            parentNode.setRightNode(null);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stack<Node<T>> globalStack = new Stack<>();
        globalStack.push(root);
        int potentialItemsCount = (int)Math.pow(2, deep);

        boolean isRowEmpty = false;

        while (!isRowEmpty) {
            Stack<Node<T>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < potentialItemsCount; i++) {
                sb.append(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<T> node = globalStack.pop();
                if (node != null) {
                    sb.append(node.getValue());
                    localStack.push(node.getLeftNode());
                    localStack.push(node.getRightNode());
                    if (!node.isLast()) {
                        isRowEmpty = false;
                    }
                } else {
                    sb.append("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < potentialItemsCount * 2 - 2; j++) {
                    sb.append(" ");
                }
            }
            sb.append("\n");

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            potentialItemsCount /= 2;
        }

        return sb.toString();
    }

    @Override
    public boolean isBalanced() {
        return balanceStep(root);
    }

    private boolean balanceStep(Node<T> current) {
        if (current == null || current.isLast()) {
            return true;
        }

        int leftDeep = 0;
        int rightDeep = 0;
        if (current.getLeftNode() != null) {
            leftDeep = this.getSubTreeDeep(current.getLeftNode(), 1);
        }
        if (current.getRightNode() != null) {
            rightDeep = this.getSubTreeDeep(current.getRightNode(), 1);
        }

        return Math.abs(leftDeep - rightDeep) <= 1
                && (current.getLeftNode() == null || balanceStep(current.getLeftNode()))
                && (current.getRightNode() == null || balanceStep(current.getRightNode()));
    }

    private int getSubTreeDeep(Node<T> current, int initialDeep) {
        int leftDeep = initialDeep;
        int rightDeep = initialDeep;

        if (current.getLeftNode() != null) {
            leftDeep = this.getSubTreeDeep(current.getLeftNode(), initialDeep + 1);
        }
        if (current.getRightNode() != null) {
            rightDeep = this.getSubTreeDeep(current.getRightNode(), initialDeep + 1);
        }

        return Math.max(leftDeep, rightDeep);
    }

}
