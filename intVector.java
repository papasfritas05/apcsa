public class IntVector{

    private final static int INITIAL_CAPACITY = 10;
    private int[] arr = new int[ INITIAL_CAPACITY];
    private int size = 0;

    //default connstructor
    public IntVector() { }

    public IntVector(int capacity) { 
        if(capacity < 0){
            throw new IllegalArgumentException();
        }
        arr = new int[capacity];
    }

    public int size() {return size; }
    private void testBounds(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(int n){

        arr[size++] = n;
    }

    private void resize(){
        if(size >= arr.length -1){

            int[] temp = new int [1 + arr.length * 2];


            for(int i =0; i < size; i++){
                temp[i] = arr[i];
            }

            arr = temp;
        }
    }

    public int get(int index){
        testBounds(index);
        return arr[index];
    }

    public void clear() {
        arr = new int[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean add(int index,int value){
        for(int i = 0; i < size; i++){
            arr[index] = value;
            return true;
        }
        return false;
    }

    public boolean remove (int value){

    }

    @Override
    public String toString(){
        if(size == 0)return "[]";
        String t = "[" + arr [0];
        for(int i = 0; i < size; i++){
            t += "," + arr[i];
        }
        return t + "[" ;
        }
}