import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<T> {
    private Object[] elementData;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;

    public MyStack(int size) {
        if (size > 0) {
            elementData = new Object[size];
        } else if (size == 0) {
            elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Stack size can't be negative");
        }
    }

    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    public void push(T data) {
        if (!isLargeEnough()) {
            increaseCapacity();
        }
        elementData[size++] = data;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0)
            throw new EmptyStackException();

        Object elementToPop = elementData[(size - 1)];

        size--;
        return (T) elementToPop;
    }

    private void increaseCapacity() {
        int newSize = size + (size / 2);
        Object[] temp = Arrays.copyOf(elementData, newSize);
        this.elementData = temp;
    }

    private boolean isLargeEnough() {
        return elementData.length > size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            sb.append(elementData[i]).append(", ");
        }
        sb.append(elementData[size - 1]).append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>(20);
        for (int i = 1; i < 100; i++) {
            if (i % 5 == 0)
                stack.push(i);
        }

        while (stack.size() != 1) {
            Integer popped = stack.pop();
            System.out.println(popped + " has been popped!");
        }

        System.out.println(stack);
    }
}