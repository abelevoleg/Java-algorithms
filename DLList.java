package hw4;

import java.util.Objects;

public class DLList {
    protected class Node {
        Cat c;
        DLList.Node next;
        DLList.Node previous;

        public Node(Cat c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return c.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DLList.Node node = (DLList.Node) o;
            return c.equals(node.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }

    protected DLList.Node head;
    protected DLList.Node tail;
    protected int size;

    public DLList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(Cat c) {
        DLList.Node n = new DLList.Node(c);
        n.previous = tail;
        if (tail != null)
            tail.next = n;
        tail = n;
        if (head == null){
            n.next = head;
            head = n;
        }
        size++;
    }

    public Node NodeNew(Cat c){
        DLList.Node n = new DLList.Node(c);
        return n;
    }

    public Cat pop() {
        // удаление через очередь - из head
        if (isEmpty()) return null;
        Cat temp = head.c;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public String toString() {
        DLList.Node current = head;
        StringBuilder sb = new StringBuilder("[");
        while (current != null) {
            sb.append(current);
            current = current.next;
            sb.append((current == null) ? "]" : ", ");
        }
        return sb.toString();
    }

    public boolean contains(Cat c) {
        return find(c) != null;
    }

    private DLList.Node find(Cat c) {
        if (isEmpty()) return null;
        DLList.Node current = head;
        while (!current.c.equals(c)) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }

    public boolean delete(Cat c) {
        DLList.Node current = head;
        DLList.Node previous = head;
        while (!current.c.equals(c)) {
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        return true;
    }
}
