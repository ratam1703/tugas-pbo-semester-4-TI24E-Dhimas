public class Arrayindex2 {
    
    public static void main(String[] args) {
        
        try {
            int[] angka ={1,2,3};

            System.out.println(angka[5]);

        } catch( ArrayIndexOutOfBoundsException e) {
            System.out.println("index array tidak tersedia");
        } 
    }
}
