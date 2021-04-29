package com.example.project;

import java.util.function.Consumer;

public class MyList<T> {
    private int size = 0;
    private Node<T> first, last;

    public MyList() {
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
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
    public void add(T element, int index) {
        Node<T> node = new Node<>();
        node.value = element;

        if (first == null) {
            first = last = node;
            last.next = first;
        } else {
            Node<T> previous = last;
            Node<T> next = first;
            for (int i = 0; i < index; i++) {
                previous = next;
                next = next.next;
            }
            if (next == first) {
                node.next = first;
                first = node;
                last.next = first;
            } else {
                previous.next = node;
                node.next = next;
            }
        }
        size++;
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
        if (first == null) {
            return null;
        } else {
            Node<T> previous = last;
            Node<T> currentNode = first;
            for (int i = 0; i < index; i++) {
                previous = currentNode;
                currentNode = currentNode.next;
            }
            T value = currentNode.value;
            unlink(previous, currentNode);
            return value;
        }
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

    private Node<T> getNode(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public T get(int index) {
        return getNode(index).value;
    }
    public T set(int index, T newValue) {
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

    public void myRemoving(Consumer<T> consumer, int k) {
        if (first != null) {
            Node<T> previous = last;
            Node<T> currentNode = first;
            for (int i = 1; i < k; i++) {
                previous = currentNode;
                currentNode = currentNode.next;
            }
            while (currentNode != null) {
                Node<T> next = currentNode.next;
                consumer.accept(currentNode.value);
                unlink(previous, currentNode);

                currentNode = next;
                for (int i = 1; i < k && currentNode != null; i++) {
                    previous = currentNode;
                    currentNode = currentNode.next;
                }
            }
        }
    }

    private static class Node<T> {
        Node<T> next;
        T value;
    }
}