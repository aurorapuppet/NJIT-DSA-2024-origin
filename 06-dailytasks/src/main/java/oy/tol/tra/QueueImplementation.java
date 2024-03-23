package oy.tol.tra;

import java.util.Arrays;

@SuppressWarnings("unused")
public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] array;
    private int size = 0;
    private int capacity;

    public QueueImplementation(int capacity) throws QueueAllocationException{
        if(capacity < 2){
            throw new QueueAllocationException("Queue capacity must be at least 2");
        }
        try{
            array = new Object[capacity];
        }catch(OutOfMemoryError e){
            throw new QueueAllocationException("Failed room for the internal array");
        }
        this.capacity = capacity;
    }
    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i = 0; i <size; i++){
            array[i] = null;
        }
        size = 0;
    }


    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException{
        if(element == null){
            throw new NullPointerException("element can not be null");
        }
        if (size >= capacity) {
            int newCapacity = capacity * 2;
            Object[] newArray;
            try{
                newArray = new Object[newCapacity];
                for(int i = 0; i < size; i++){
                    newArray[i] = array[i];
                }
                array = newArray;
                capacity = newCapacity;
            }catch(Exception e){
                throw new QueueAllocationException("Cannot allocate room for the internal array");
            }
        }
        array[size++] = element;

    }
    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = (E) array[0];
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
        return element;

    }
    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E) array[0];
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
