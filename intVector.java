public class IntVector {
    
    private final static int INITIAL_CAPACITY = 10;
    private int[] arr = new int[INITIAL_CAPACITY];
    private int size = 0;

    public IntVector() { }

    public IntVector(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        arr = new int[capacity];
    }

    public int size() { return size; }

    private void testBounds(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void tryResize() {
        if(size >= arr.length - 1) {
            int[] temp = new int[1 + arr.length * 2];

            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }

            arr = temp;
        }
    }

    public void add(int value) {
        tryResize();
        arr[size++] = value;
    }

    public boolean add(int index, int value) {
        testBounds(index);
        int temp = arr[index];
        arr[index] = value;
        add(arr[size - 1]);
        for (int i = size - 2; i > index; i--) {
            arr[i+1] = arr[i];
        }
        arr[index + 1] = temp;
        if (arr[index] == value) {
            return true;
        }
        return false;
    }

    public boolean remove(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                for (int j = i; j < size - 1; j++) {
                    arr[j] = arr[j+1];
                }
                size--;
                tryResize();
                return true;
            }
        }
        return false;
    }

    public void set(int index, int value) {
        testBounds(index);
        arr[index] = value;
    }

    @Override
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        String t = "[" + arr[0];
        for(int i = 1; i < size; i++) {
            t += ", " + arr[i];
        }
        return t + "]";
    }
}