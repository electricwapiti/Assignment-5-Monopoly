package org.example;

import java.util.Random;

public class CircularlyLinkedList<E> {
    private Node<E> tail;
    private int size;
    private static final Random RNG = new Random();

    public CircularlyLinkedList() {
        tail = null;
        size = 0;
    }

    // Access methods
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public E first() { return isEmpty() ? null : tail.getNext().getElement(); }
    public E last() { return isEmpty() ? null : tail.getElement(); }

    // Update methods
    public void rotate() { if (tail != null) tail = tail.getNext(); }

    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    public E get(int index) {
        if (isEmpty()) return null;
        index = index % size;
        if (index < 0) index += size;
        Node<E> current = tail.getNext();
        for (int i = 0; i < index; i++) current = current.getNext();
        return current.getElement();
    }

    public Node<E> getTail() { return tail; }

    /** 
     * Roll two six-sided dice, rotate the list that many times, and return the new first element
     */
    public E advanceByDice() {
        int die1 = RNG.nextInt(6) + 1;
        int die2 = RNG.nextInt(6) + 1;
        int steps = die1 + die2;
        for (int i = 0; i < steps; i++) rotate();
        System.out.println("Rolled: " + die1 + " + " + die2 + " = " + steps);
        return first();
    }
}