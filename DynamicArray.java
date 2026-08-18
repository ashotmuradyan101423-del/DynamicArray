package am.pizzeria.palmetto;

public class DynamicArray {

    private Object[] data;
    private int size;

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }

        data = new Object[initialCapacity];
        size = 0;
    }

    public DynamicArray() {
        this(10);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void set(int index, Object value) {
        checkIndex(index);
        data[index] = value;
    }

    public void add(Object value) {
        if (size == data.length) {
            grow();
        }

        data[size] = value;
        size++;
    }

    public void add(int index, Object value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == data.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    public Object remove(int index) {
        checkIndex(index);

        Object removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return removed;
    }

    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (value == null) {
                if (data[i] == null) {
                    return i;
                }
            } else if (value.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
    }

    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < size; i++) {
            result += data[i];

            if (i < size - 1) {
                result += ", ";
            }
        }

        return result + "]";
    }

    private void grow() {
        int newCapacity = data.length == 0 ? 1 : data.length * 2;
        Object[] newData = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Testing DynamicArray ---");

        DynamicArray pizzaToppings = new DynamicArray(2);

        pizzaToppings.add("Pepperoni");
        pizzaToppings.add("Mushrooms");
        pizzaToppings.add("Extra Cheese");

        System.out.println("Current toppings: " + pizzaToppings.toString());
        System.out.println("Array size: " + pizzaToppings.size());

        Object removedItem = pizzaToppings.remove(1);
        System.out.println("Removed: " + removedItem);
        System.out.println("Updated toppings: " + pizzaToppings.toString());
    }
}
