package coursera.alg1.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> head;
    private Node<Item> tail;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        notNull(item);
        size++;
        Node<Item> newNode = new Node<>();
        newNode.data = item;
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        if (tail == null) {
            tail = newNode;
        }

        head = newNode;
    }

    public void addLast(Item item) {
        notNull(item);
        size++;
        Node<Item> newNode = new Node<>();
        newNode.data = item;
        newNode.prev = tail;
        if (tail != null) {
            tail.next = newNode;
        }
        if (head == null) {
            head = newNode;
        }

        tail = newNode;
    }

    public Item removeFirst() {
        hasElement(head);
        size--;

        Item data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        if (size == 0) {
            tail = null;
        }

        return data;
    }

    public Item removeLast() {
        hasElement(tail);
        size--;

        Item data = tail.data;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        if (size == 0) {
            head = null;
        }

        return data;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<>(head);
    }

    private void notNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    private static void hasElement(Object arg) {
        if (arg == null) {
            throw new NoSuchElementException("Arg should be not null");
        }
    }

    private static class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;
    }

    private static class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        DequeIterator(Node<Item> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            hasElement(current);
            Item data = current.data;
            current = current.next;
            return data;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(4);
        deque.addLast(5);
        deque.addFirst(3);
        deque.addLast(6);
        deque.addFirst(2);
        deque.addLast(7);
        deque.addFirst(1);
        deque.addFirst(0);
        System.out.println("Removed first: " + deque.removeFirst());
        System.out.println("Removed last: " + deque.removeLast());
        System.out.println("Removed last: " + deque.removeLast());
        System.out.println("Size: " + deque.size());
        System.out.println("Elements:");
        for (Integer i : deque) {
            System.out.println(i);
        }
    }
}
