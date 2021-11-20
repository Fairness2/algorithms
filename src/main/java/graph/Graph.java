package graph;


import java.util.ArrayList;
import java.util.Stack;

public interface Graph<T> {
    void addVertex(T vertex);

    boolean addRelations(T firstObject, T... others);
    int getSize();
    String toString();
    ArrayList<T> dfs(T startVertex);
    ArrayList<T> bfs(T startVertex);
    ArrayList<Stack<T>> findShortPaths(T startVertex, T finishVertex);
}
