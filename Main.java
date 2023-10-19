public class Main {
    public static void main(String[] args) {
        try {
            IntVector test = new IntVector(-1000);

            System.out.println("Size: " + test.size());

            for(int i = 0; i < test.size(); i++) {

            }
        } catch(IllegalArgumentException ex) {
            System.out.println("thatsnIlligal");
        }
          catch(ArithmeticException ex) {
            System.out.println("bad math");
        }
          catch(Exception ex) {
            System.out.println("Generic Exception");
        }
    }
}