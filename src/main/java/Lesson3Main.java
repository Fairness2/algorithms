import Queue.Queue;
import Queue.CustomQueue;
import Queue.PriorityQueue;
import dequeue.CustomDequeue;
import dequeue.Dequeue;
import stack.CustomStack;
import stack.Stack;

public class Lesson3Main {
    public static void main(String[] args) {
        System.out.println("Stack");
        stackTest();
        System.out.println();
        System.out.println("Queue");
        queueTest();
        System.out.println();
        System.out.println("Priority queue");
        priorityQueueTest();
        System.out.println();
        System.out.println("Revers string");
        stringRevers();
        System.out.println();
        System.out.println("Dequeue");
        dequeueTest();
    }

    //Тестирование стек
    private static void stackTest() {
        Stack<Integer> stack = new CustomStack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.toStringAndClear());
    }

    //Тестирование очереди
    private static void queueTest() {
        Queue<Integer> queue = new CustomQueue<Integer>(10);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        System.out.println(queue.toStringAndClear());
    }

    //Тестирование сортированной очереди
    private static void priorityQueueTest() {
        Queue<Integer> queue = new PriorityQueue<Integer>(10);
        queue.insert(2);
        queue.insert(1);
        queue.insert(7);
        queue.insert(4);
        queue.insert(5);
        System.out.println(queue.toStringAndClear());
    }

    //Переворот строки
    private static void stringRevers() {
        String st = "Эту строку нужно перевернуть";
        Stack<Character> stack = new CustomStack<>(st.length());
        for (char c: st.toCharArray()) {
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.printf("Reversed string: %s%n", sb.toString());
    }

    //Тестирование деку
    private static void dequeueTest() {
        Dequeue<Integer> dequeue = new CustomDequeue<>(10);
        dequeue.insert(1);
        dequeue.insert(2);
        dequeue.insert(3);
        dequeue.leftInsert(5);
        dequeue.leftInsert(6);
        System.out.println(dequeue.toStringAndClear());
    }
}
