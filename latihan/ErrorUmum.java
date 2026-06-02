public class ErrorUmum {
    
    public static void main(string[] args){

        try{
            int hasil = 10 / 0 ;  
        } catch (Exception e) {
            System.out.println("pesan untuk semua jenis error: " + e.getMessage());
        }
    }
}
