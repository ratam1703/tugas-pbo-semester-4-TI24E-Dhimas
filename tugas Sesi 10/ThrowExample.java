public class ThrowExample {

    static void demo()
    {
        NullPointerException t;
        t=new NullPointerException("Coba Throw");
        throw t;

         
    }

    public static void main(String[] args) {
        try
        {
            demo ();
            System.out.println("Selesai");
        }
        catch (NullPointerException e)
        {
            System.out.println("Ada pesan error: "+e);
        }
    }
    
}

//Keyword throw digunakan untuk melemparkan secara sengaja sebuah objek exception buatan kita atau instansiasi manual (misal NullPointerException atau Exception) ke blok penangkap. Baris kode yang berada langsung di bawah keyword throw di dalam blok yang sama tidak akan dieksekusi.
