package com.company.service;

import com.company.exeption.EmptySingleLinkedListException;
import com.company.model.Node;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SingleList<T> implements Iterable<T> {

    private int size;
    private Node<T> head;

    public SingleList() {
    }

    public SingleList(T data) {
        if (data != null) {
            size = 1;
            head = new Node<>(data, null);
        }
    }

    public SingleList(T[] arr) {
        for (T t : arr) {
            addLast(t);
        }
    }

    public void addLast(T data) {
        if (head != null) {
            getByIndex(size - 1).setNextNode(new Node<>(data, null));
            size++;
        } else {
            addFirst(data);
        }
    }

    public void addFirst(T data) {
        head = new Node<>(data, head);
        size++;
    }

    public void delFirst() throws EmptySingleLinkedListException {
        isEmpty();
        head = head.getNextNode();
        size--;
    }

    public void delLast() throws EmptySingleLinkedListException {
        isEmpty();
        if (size > 1) {
            getByIndex(size - 2).setNextNode(null);
            size--;
        } else {
            delFirst();
        }
    }

    private Node<T> getByIndex(int id) {
        Node<T> actual = head;
        for (int i = 0; i < id; i++) {
            actual = actual.getNextNode();
        }
        return actual;
    }

    public void insertByIndex(int index, T data) throws EmptySingleLinkedListException {
        isEmpty();
        isIndexCorrect(index);
        if (index == 0) {
            addFirst(data);
        } else {
            Node<T> temp = getByIndex(index);
            getByIndex(index - 1).setNextNode(new Node<>(data, temp));
            size++;
        }
    }

    public void deleteByIndex(int index) throws EmptySingleLinkedListException {
        isEmpty();
        isIndexCorrect(index);
        if (index == 0) {
            delFirst();
        } else if (index == getSize() - 1) {
            delLast();
        } else {
            getByIndex(index - 1).setNextNode(getByIndex(index - 1).getNextNode().getNextNode());
            size--;
        }
    }

    public void changeByIndex(int firstIndex, int secondIndex) throws EmptySingleLinkedListException {
        isEmpty();
        isIndexCorrect(firstIndex);
        isIndexCorrect(secondIndex);
        if (firstIndex != secondIndex) {
            Node<T> firstIndexNode = getByIndex(firstIndex);
            Node<T> secondIndexNode = getByIndex(secondIndex);
            if (size == 2) {
                firstIndexNode.setNextNode(null);
                secondIndexNode.setNextNode(firstIndexNode);
                head = secondIndexNode;
            } else if (firstIndex == 0) {
                Node<T> temp = firstIndexNode.getNextNode();
                getByIndex(secondIndex - 1).setNextNode(firstIndexNode);
                firstIndexNode.setNextNode(secondIndexNode.getNextNode());
                head = secondIndexNode;
                secondIndexNode.setNextNode(temp);
            } else if (secondIndex == 0) {
                Node<T> temp = secondIndexNode.getNextNode();
                getByIndex(firstIndex - 1).setNextNode(secondIndexNode);
                secondIndexNode.setNextNode(firstIndexNode.getNextNode());
                head = firstIndexNode;
                firstIndexNode.setNextNode(temp);
            } else {
                Node<T> temp = firstIndexNode.getNextNode();
                firstIndexNode.setNextNode(secondIndexNode.getNextNode());
                secondIndexNode.setNextNode(temp);
                getByIndex(firstIndex - 1).setNextNode(secondIndexNode);
                getByIndex(secondIndex - 1).setNextNode(firstIndexNode);
            }
        }
    }

    private void isEmpty() throws EmptySingleLinkedListException {
        if (head == null) {
            throw new EmptySingleLinkedListException();
        }
    }

    private void isIndexCorrect(int index) {
        if (!(index >= 0 && index < getSize())) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public boolean isListEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        String str = "SingleList [ ";
        Node<T> actual = head;
        while (actual != null) {
            if (actual.getNextNode() == null) {
                str += actual.getData().toString();
            } else {
                str += actual.getData().toString() + ", ";
            }
            actual = actual.getNextNode();
        }
        str += " ]";
        return str;
    }
}
