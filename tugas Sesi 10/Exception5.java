public class Exception5 {
    
    public static void main(String[] args) {
        int bil=10;
        try
        {
            System.out.println(bil/0);
        }
        catch (ArithmeticException e) 
        {
            System.out.println("Pesan error: ");
            System.out.println(e.getMessage());
            System.out.println("Info stack erase");
            e.printStackTrace();
            e.printStackTrace(System.out);
        }
        catch (Exception e)
        {
            System.out.println("ini menghandle error yang terjadi");
        }
    }
}

//Analisa Penggunaaan try dan catch

//Blok catch menangkap ArithmeticException dan menggunakan method bawaan seperti getMessage() untuk melihat pesan error, serta printStackTrace() untuk melacak di baris mana error tersebut terjadi secara detail.