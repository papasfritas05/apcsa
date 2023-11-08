package LinearStructures;

import java.lang.Iterable;
import java.util.Iterator;


// this is a "generic" class
public class ArrayList<E> implements Iterable<E> {
    // what is protected?
    // protected is... public in the package, private outside the package
    protected static final int INITIAL_CAPACITY = 10;

    private int size = 0;

    @SuppressWarnings("unchecked") // java annotation
    private E[] data = (E[])new Object[INITIAL_CAPACITY];

    public ArrayList() { } // default constructor

    public ArrayList(E[] objects) {
        for(int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if(size >= data.length) {
            E[] newData = (E[])new Object[size * 2 + 1];
            // manually loop through and copy the old to the new
            System.arraycopy(data, 0, newData, 0, size); // crazy, huh
            data = newData;
        }
    }

    public void add(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        ensureCapacity();
        for(int i = size - 1; i >= index; i--) {
            data[i+1] = data[i]; // right shift
        }
        data[index] = e; // insert
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }

    @SuppressWarnings("unchecked")
    public void  clear() {
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean contains(E e) {
        for(int i = 0; i < size; i++) {
            if(e.equals(data[i])) return true;
        }
        return false;
    }

    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    public int indexOf(E e) {
        for(int i = 0; i < size; i++) {
            if(e.equals(data[i])) return i;
        }
        return -1;
    }

    public int lastIndexOf(E e) {
        for(int i = size - 1; i >= 0; i--) {
            if(e.equals(data[i])) return i;
        }
        return -1;
    }

    // removes a numerical index position
    public E remove(int index) {
        checkIndex(index);
        E e = data[index]; // save the original
        for(int j = index; j < size; j++) {
            data[j] = data[j+1]; // shift values left, overwriting the original
        }
        data[size - 1] = null; // erase the last (duplicate)
        size--; // reduce the size, of course
        return e; // return the original (it may still be useful)
    }

    // removes a matching type value e
    public boolean remove(E e) {
        if(indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        return false;
    }

    // sub in the new value, return the old value
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < size; i++) {
            result.append(data[i]);
            if(i < size - 1) result.append(", ");
        }
        result.append("]");
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if(size != data.length) {
            E[] newData = (E[])(new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }


    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {
        private int current = 0;

        public boolean hasNext() {
            return (current < size);
        }
        
        public E next() {
            return data[current++];
        }

        public void remove() {
            ArrayList.this.remove(current);
        }
    }
}

