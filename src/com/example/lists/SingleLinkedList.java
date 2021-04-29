package com.example.lists;

import java.util.NoSuchElementException;

public class SingleLinkedList<E> {
    private int size = 0;
    private Node<E> first, last;

    public SingleLinkedList() {
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private Node<E> getNode(int index) {
        Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        return x;
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>( e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        size++;
    }
    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        size--;
        return element;
    }
    private E unlink(Node<E> prev, Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }
    public void addLast(E e) {
        linkLast(e);
    }
    public void add(E e) {
        linkLast(e);
    }
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else if (index == 0)
            linkFirst(element);
        else {
            Node<E> prev = getNode(index - 1);
            prev.next = new Node<>(element, prev.next.next);
        }
    }

    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).item;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = getNode(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }
    public E removeLast() {
        return remove(size - 1);
    }
    public E remove(int index) {
        checkElementIndex(index);
        if (index == 0)
            return unlink(null, getNode(index));
        Node<E> prev = getNode(index - 1);
        return unlink(prev, prev.next);
    }
    public boolean remove(Object o) {
        Node<E> prev = null;
        Node<E> x = first;
        if (o == null) {
            while (x != null) {
                if (x.item == null) {
                    unlink(prev, x);
                    return true;
                }
                prev = x;
                x = x.next;
            }
        } else {
            while (x != null) {
                if (o.equals(x.item)) {
                    unlink(prev, x);
                    return true;
                }
                prev = x;
                x = x.next;
            }
        }
        return false;
    }
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}