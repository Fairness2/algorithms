import dequeue.CustomDequeue;
import dequeue.Dequeue;
import linked.*;

import java.util.Iterator;

public class Lesson4Main {
    public static void main(String[] args) {
        //Првоерка стека
        System.out.println("Проверка стека");
        testStack();
        //Првоерка очереди
        System.out.println();
        System.out.println("Проверка очереди");
        testQueue();
        //Првоерка итератора
        System.out.println();
        System.out.println("Првоерка итератора");
        linkIteratorApp();
        //Првоерка деки
        System.out.println();
        System.out.println("Првоерка деки");
        testDequeue();
    }

    private static void testStack() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(4);
        stack.push(5);
        System.out.println(stack.toString());
        System.out.println(stack.toStringAndClear());
        System.out.println(stack.toString());
    }

    private static void testQueue() {
        LinkedQueue<Integer> stack = new LinkedQueue<>();
        stack.insert(1);
        stack.insert(2);
        stack.insert(3);
        System.out.println(stack.popFront());
        System.out.println(stack.peekFront());
        stack.insert(4);
        stack.insert(5);
        System.out.println(stack.toStringAndClear());
    }

    private static void linkIteratorApp() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        list.insertFirst(5);
        StringBuilder sb = new StringBuilder();
        sb.append("Проверка форича: ");
        for (Integer i: list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
        LinkedIterator<Integer> iterator = (LinkedIterator<Integer>) list.iterator();
        sb = new StringBuilder();
        sb.append("\nПроверка прохода итератора: ");
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }
        System.out.println(sb.toString());
        System.out.println("Проверка вставки и удаления");
        iterator = (LinkedIterator<Integer>) list.iterator();
        iterator.next();
        iterator.next();
        iterator.insertBefore(7);
        iterator.insertAfter(6);
        iterator.next();
        iterator.next();
        iterator.remove();

        System.out.println(list.toString());
    }

    private static void testDequeue() {
        LinkedDequeue<Integer> dequeue = new LinkedDequeue<>();
        dequeue.insert(1);
        dequeue.insert(2);
        dequeue.insert(3);
        dequeue.leftInsert(5);
        dequeue.leftInsert(6);
        System.out.println(dequeue.toStringAndClear());
        dequeue.insert(1);
        dequeue.insert(2);
        dequeue.insert(3);
        dequeue.leftInsert(5);
        dequeue.leftInsert(6);
        System.out.println(dequeue.toReverseStringAndClear());
    }

}
