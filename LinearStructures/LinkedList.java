package LinearStructures;

import java.util.Iterator;

public class LinkedList <E> {
    private int size = 0;

    private Node<E> head, tail;

        
    public LinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {

        }
    }

    public void add(E e) {
        add(size, e);
        
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }

    public int indexOf(E e) {
        if(size == 0) return -1;

        int index = 0;
        Node<E> current = head;
        do {
            if(current.element == e) {
                return index;
            }
            current = current.next;
            index++;
        } while (current != null);
        
        return -1;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;

        if(tail == null) {
            tail = head;
        }
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if(tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    public void add(int index, E e) {
        if(index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<E> current = head;
            for(int i = 1; i < index; i++) {
                current = current.next;
            }
            Node <E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }
    }

    public E getFirst() {
        if(size == 0) return null;
        return head.element;
    }

    public E getLast() {
        if (size == 0) return null;
        return tail.element;
    }

    public E removeFirst() {
        if(size == 0) return null;
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if(head == null) tail = null;
            return temp.element;
        }
    }

    public E removeLast() {
        if(size == 0) return null;
        else if(size == 1){
            Node<E> temp = head;
            clear();
            return temp.element;
        } else {
            Node<E> current = head;

            for(int i = 0; i < size -2; i++) {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    public void clear() {
        size = 0;
        head = tail = null;
    }

    private static class Node<E> {
        public static Object current;
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public E remove(int index) {
        checkIndex(index);
        Node<E> current = head;
        Node<E> tail = head;
        if(index ==0){
            return removeFirst();
        }
        if(index == index-1){
            return removeLast();
        }
        for(int i = 0; i < index - 1; i++){
            current = current.next;
            
        }
        Node<E> removed= current.next;
        current.next = current.next.next;
        return removed.element;
    }

    public E get(int Index) {
        checkIndex(Index);
        if(Index == 0){
            return getFirst();
        }
        else if(Index == Index- 1){
            return getLast();
        }else{
              Node<E> current = head;
        
        for(int i = 0; i < Index - 1; i++) {
            current = current.next;
        }
        return current.element;
    }
    }

    public int lastIndexOf(E e) {
        if(size == 0) return -1;
        int index = 0;
        int currIndex = -1;
        Node<E> current = head;
        do {
            if(current.element == e) {
                currIndex = index;
            }
            current = current.next;
            index++;
        } while (current != null);
        
        return currIndex;
    }

    public E set(int Index, E e) {
        checkIndex(Index);
        Node<E> current = head;
        
        for(int i = 0; i < Index - 1; i++) {
            current = current.next;
        }
        Node<E> removed = current;
        current.element = e;
        return removed.element;

    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = head;
        private int num = 0;

        public boolean hasNext() {
            return (num < size);
        }
        
        public E next() {
            if(hasNext()){
                Node<E> og = current;
                current = current.next;
                return og.element;
            }
            return null;
        }

        public void remove() {
            LinkedList.this.remove(num);
        }
    }
}
