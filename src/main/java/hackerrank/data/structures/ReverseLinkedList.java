package hackerrank.data.structures;

public class ReverseLinkedList {

    class Node {
        int data;
        Node next;
    }

    Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
