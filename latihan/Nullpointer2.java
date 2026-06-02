public class Nullpointer2 {
    
    public static void main(String[] args) {
        
        try{
            String nama = null;

            System.out.println(nama.length());
        } catch (NullPointerException e){
            System.out.println("objek masih null");
        } 
        
        System.out.println("program selesai");
    }
}
