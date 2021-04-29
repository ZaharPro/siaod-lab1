package com.example.lists;

import java.util.function.Consumer;

public class SingleLinkedRingList<T> {
    private int size = 0;
    private Node<T> first, last;

    public SingleLinkedRingList() {
    }

    private String outOfBoundsMsg(int index) {
        return "Index = " + index + ", size = " + size;
    }
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private Node<T> getNode(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void linkFirst(T element) {
        Node<T> node = new Node<>();
        node.value = element;

        if (last == null)
            last = node;

        node.next = first;
        first = node;
        last.next = first;
        size++;
    }
    private void linkLast(T element) {
        Node<T> node = new Node<>();
        node.value = element;

        if (first == null)
            first = node;

        if (last != null)
            last.next = node;
        last = node;
        last.next = first;
        size++;
    }
    private void link(T element, Node<T> previous, Node<T> currentNode) {
        Node<T> node = new Node<>();
        node.value = element;

        previous.next = node;
        node.next = currentNode;
        size++;
    }

    private void unlinkFirst() {
        Node<T> removed = first;
        if (removed.next == last) {//one node
            first = last = null;
        } else {
            first = removed.next;
        }
        last.next = first;
        removed.next = null;
        removed.value = null;
        size--;
    }
    private void unlink(Node<T> previous, Node<T> removed) {
        if (removed == first) {
            if (removed.next == first) {//one node
                first = last = null;
            } else {
                first = removed.next;
                last.next = first;
            }
        } else if (removed == last) {
            last = previous;
            last.next = first;
        } else {
            previous.next = removed.next;
        }
        removed.next = null;
        removed.value = null;
        size--;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T element) {
        linkFirst(element);
    }
    public void addLast(T element) {
        linkLast(element);
    }
    public void add(T element) {
        linkLast(element);
    }
    public void add(T element, int index) {
        checkPositionIndex(index);
        if (index == 0) {
            linkFirst(element);
        } else if (index == size) {
            linkLast(element);
        } else {
            Node<T> previous = first;
            Node<T> currentNode = first.next;
            for (int i = 1; i < index; i++) {
                previous = currentNode;
                currentNode = currentNode.next;
            }
            link(element, previous, currentNode);
        }
    }

    public boolean remove(Object o) {
        Node<T> previous = last;
        Node<T> current = first;
        if (current != null) {
            if (o == null) {
                do {
                    if (null == current.value) {
                        unlink(previous, current);
                        return true;
                    }
                    previous = current;
                    current = current.next;
                } while (current != first);
            } else {
                do {
                    if (o.equals(current.value)) {
                        unlink(previous, current);
                        return true;
                    }
                    previous = current;
                    current = current.next;
                } while (current != first);
            }
        }
        return false;
    }
    public T remove(int index) {
        checkElementIndex(index);
        T value;
        if (index == 0) {
            value = first.value;
            unlinkFirst();
        } else {
            Node<T> previous = first;
            Node<T> currentNode = first.next;
            for (int i = 1; i < index; i++) {
                previous = currentNode;
                currentNode = currentNode.next;
            }
            value = currentNode.value;
            unlink(previous, currentNode);
        }
        return value;
    }
    public void clear() {
        Node<T> next;
        for (Node<T> node = first; node != null; node = next) {
            next = node.next;

            node.next = null;
            node.value = null;
        }
        size = 0;
    }

    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).value;
    }
    public T set(int index, T newValue) {
        checkElementIndex(index);
        Node<T> node = getNode(index);
        T oldValue = node.value;
        node.value = newValue;
        return oldValue;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }
    public int indexOf(Object o) {
        Node<T> current = first;
        if (current != null) {
            int i = 0;
            if (o == null) {
                do {
                    if (null == current.value)
                        return i;
                    current = current.next;
                    i++;
                } while (current != first);
            } else {
                do {
                    if (o.equals(current.value))
                        return i;
                    current = current.next;
                    i++;
                } while (current != first);
            }
        }
        return -1;
    }

    public void forEach(Consumer<T> consumer) {
        Node<T> node = first;
        if (node != null) {
            do {
                consumer.accept(node.value);
                node = node.next;
            } while (node != first);
        }
    }

    private static class Node<T> {
        Node<T> next;
        T value;
    }
}