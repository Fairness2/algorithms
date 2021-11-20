import graph.CustomGraph;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Lesson7Main {
    public static void main(String[] args) {
        findShortPaths();
    }

    private static void findShortPaths() {
        System.out.println("Поиск коротких путей");

        Graph<String> graph = new CustomGraph<>(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орёл");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");

        graph.addRelations("Москва", "Тула","Рязань", "Калуга");
        graph.addRelations("Тула", "Липецк", "Москва");
        graph.addRelations("Липецк", "Воронеж", "Тула");
        graph.addRelations("Воронеж", "Липецк", "Саратов", "Курск");
        graph.addRelations("Рязань", "Тамбов", "Москва");
        graph.addRelations("Тамбов", "Саратов", "Рязань");
        graph.addRelations("Саратов", "Тамбов", "Воронеж");
        graph.addRelations("Калуга", "Москва", "Орёл");
        graph.addRelations("Орёл", "Калуга", "Курск");
        graph.addRelations("Курск", "Орёл", "Воронеж");

        ArrayList<Stack<String>> shortPaths = graph.findShortPaths("Москва", "Воронеж");
        for (int i = 0; i < shortPaths.size(); i++) {
            System.out.printf("Короткий путь №%s%n", i + 1);
            StringBuilder sb = new StringBuilder();

            while (!shortPaths.get(i).empty()) {
                sb.append(shortPaths.get(i).pop());
                if (!shortPaths.get(i).empty()) {
                    sb.append(" -> ");
                }
            }

            /*Iterator<String> iterator = shortPaths.get(i).iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                if (iterator.hasNext()) {
                    sb.append(" -> ");
                }
            }*/
            System.out.println(sb.toString());
        }

        System.out.println("Обход в ширину");
        System.out.println(Arrays.toString(graph.bfs("Москва").toArray()));
        System.out.println("Обход в глубину");
        System.out.println(Arrays.toString(graph.dfs("Москва").toArray()));

    }

}
