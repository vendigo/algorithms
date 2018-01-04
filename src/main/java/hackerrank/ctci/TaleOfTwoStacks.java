package hackerrank.ctci;

import java.util.Scanner;
import java.util.Stack;

public class TaleOfTwoStacks {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }

    static class MyQueue<T> {
        private Stack<T> tail = new Stack<>();
        private Stack<T> head = new Stack<>();

        void enqueue(T a) {
            tail.push(a);
        }

        void dequeue() {
            rebalance();
            head.pop();
        }

        T peek() {
            rebalance();
            return head.peek();
        }

        private void rebalance() {
            if (head.isEmpty()) {
                while (!tail.isEmpty()) {
                    T el = tail.pop();
                    head.push(el);
                }
            }
        }
    }
}
