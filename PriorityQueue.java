package hw3;

import java.util.NoSuchElementException;

// пункт 3 ДЗ
public class PriorityQueue {
    private int capacity;
    private ElementPriority[] queue;
    private int head;
    private int tail;
    private int items;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        queue = new ElementPriority[capacity];
        head = 0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == capacity;
    }

    public int size() {
        return items;
    }

    private void swap(ElementPriority a, ElementPriority b) {
        ElementPriority temp = new ElementPriority(a.data, a.priority);
        a.data = b.data;
        a.priority = b.priority;
        b.data = temp.data;
        b.priority = temp.priority;
    }

    public void insert(int value, int priority) {
        if (isFull()) {
            capacity *= 2;
            ElementPriority[] newQ = new ElementPriority[capacity];
            if (tail >= head) {
                System.arraycopy(queue, 0, newQ, 0, queue.length);
            } else {
                System.arraycopy(queue, 0, newQ, 0, tail + 1);
                System.arraycopy(queue, head,
                        newQ, capacity - (queue.length - head),
                        queue.length - head - 1);
            }
            queue = newQ;
        }
        if (tail == capacity - 1)
            tail = -1;
        queue[++tail] = new ElementPriority(value, priority);
        items++;

        // при добавлении элемента в очередь происходит сортировка по приоритету
        int k = tail;
        for (int i = 0; i < items - 1; i++){
            // проверка, был ли хвост очереди перенесен в начало массива
            if (k - i - 1 == -1){
                if (queue[k - i].priority < queue[queue.length - 1].priority){ /* сравнение приоритета с конечным элементом*/
                    swap(queue[k - i], queue[queue.length - 1]);
                }
                k = queue.length + i; // перезапись номера элемента, чтобы продолжать из конца массива, если было перенесение
            } else if (queue[k - i].priority < queue[k - i - 1].priority) { /* сравнение приоритета с соседним элементом */
                swap(queue[k - i], queue[k - i - 1]);
            }
        }
    }

    public ElementPriority remove() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        ElementPriority temp = queue[head++];
        head %= capacity; // if (head == capacity) head = 0;
        items--;
        return temp;
    }

    public ElementPriority peek() {
        return queue[head];
    }

    public int data(int k) {
        return queue[k].data;
    }
}
