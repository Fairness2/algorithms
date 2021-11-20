package graph;

import lombok.Data;

import java.util.*;

public class CustomGraph<T> implements Graph<T> {
    private final ArrayList<Vertex<T>> vertexList;
    private final boolean[][] matrix;

    public CustomGraph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.matrix = new boolean[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(T vertex) {
        vertexList.add(new Vertex<T>(vertex));
    }

    @Override
    public boolean addRelations(T firstObject, T... others) {
        boolean result = true;
        for (T another : others) {
            result &= addEdge(firstObject, another);
        }
        return result;
    }

    private boolean addEdge(T firstObject, T secondObject) {
        int startIndex = indexOf(firstObject);
        int endIndex = indexOf(secondObject);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        matrix[startIndex][endIndex] = true;
        matrix[endIndex][startIndex] = true;
        return true;
    }

    private int indexOf(T object) {
        for (int i = 0; i < vertexList.size(); i++) {
            Vertex<T> vertex = vertexList.get(i);
            if (vertex.getValue().equals(object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public ArrayList<T> dfs(T startVertex) {
        int startIndex = indexOf(startVertex);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start vertex");
        }

        Stack<Vertex<T>> stack = new Stack<>();
        Vertex<T> vertex = vertexList.get(startIndex);
        ArrayList<T> graphVertices = new ArrayList<>();
        graphVertices.add(visitVertex(stack, vertex));
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                graphVertices.add(visitVertex(stack, vertex));
            } else {
                stack.pop();
            }
        }
        resetVertexState();

        return graphVertices;
    }

    private void resetVertexState() {
        for (Vertex<T> vertex : vertexList) {
            vertex.setVisited(false);
            vertex.setPreviousVertex(null);
        }
    }

    private Vertex<T> getNearUnvisitedVertex(Vertex<T> current) {
        int currentIndex = vertexList.indexOf(current);
        for (int i = 0; i < getSize(); i++) {
            if (matrix[currentIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private T visitVertex(Stack<Vertex<T>> stack, Vertex<T> vertex) {
        if (!stack.empty()) {
            vertex.setPreviousVertex(stack.peek());
        }
        vertex.setVisited(true);
        stack.push(vertex);
        return vertex.getValue();
    }

    private T visitVertex(Queue<Vertex<T>> queue, Vertex<T> vertex) {
        if (!queue.isEmpty()) {
            vertex.setPreviousVertex(queue.peek());
        }
        vertex.setVisited(true);
        queue.add(vertex);
        return vertex.getValue();
    }

    @Override
    public ArrayList<T> bfs(T startVertex) {
        int startIndex = indexOf(startVertex);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start vertex");
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex = vertexList.get(startIndex);
        ArrayList<T> graphVertices = new ArrayList<>();
        graphVertices.add(visitVertex(queue, vertex));
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                graphVertices.add(visitVertex(queue, vertex));
            } else {
                queue.remove();
            }
        }
        resetVertexState();

        return graphVertices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(vertexList.get(i).toString());
            for (int j = 0; j < getSize(); j++) {
                if (matrix[i][j]) {
                    sb.append(" -> ").append(vertexList.get(j).toString());
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public ArrayList<Stack<T>> findShortPaths(T startVertex, T finishVertex) {
        int startIndex = indexOf(startVertex);
        int destinationIndex = indexOf(finishVertex);
        if (startIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Invalid vertices");
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex = vertexList.get(startIndex);

        ArrayList<Stack<T>> possiblePaths = new ArrayList<>();
        int currentCost = -1;

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
                if (vertex.getValue().equals(finishVertex)) {
                    Stack<T> currentPath = new Stack<>();
                    int cost = getReversePath(vertex, currentPath);
                    if (currentCost > cost && currentCost != -1) {
                        return possiblePaths;
                    }
                    else {
                        possiblePaths.add(currentPath);
                        currentCost = cost;
                    }
                }
            } else {
                queue.remove();
            }
        }

        resetVertexState();
        return possiblePaths;
    }

    public int getReversePath(Vertex<T> vertex, Stack<T> stack) {
        int cost = 0;
        Vertex<T> current = vertex;
        while(current != null) {
            stack.push(current.getValue());
            current = current.getPreviousVertex();
            cost++;
        }

        return cost;
    }

    @Data
    private static class Vertex<T> {
        private final T value;
        private boolean visited;
        private Vertex<T> previousVertex;

        public Vertex(T value) {
            this(value, null);
        }

        public Vertex(T value, Vertex<T> previousVertex) {
            this.value = value;
            this.previousVertex = previousVertex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Objects.equals(value, ((Vertex<T>) o).getValue());
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return value.toString();
        }

    }
}
