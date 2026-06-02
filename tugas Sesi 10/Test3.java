 import java.io.*;

public class Test3 {
    
    public void methodA() {
        System.out.println("Method A");
    }

    public void methodB() throws IOException {
        System.out.println(20 / 0);  
        System.out.println("Method B");
    }

     
    public static void main(String[] args) {
        Test3 o = new Test3();
        o.methodA();
        try 
        {
            o.methodB();
        } 
        catch (Exception e) 
        {
            System.out.println("Error di Method B");
        } 
         
        finally 
        {
            System.out.println("Ini selalu dicetak");
        }
    }
}

//* Keyword throws diletakkan di signature method untuk mendeklarasikan bahwa method tersebut berpotensi menghasilkan error tertentu (misal IOException), sehingga pemanggil method wajib menangani error tersebut.

//Blok finally akan selalu dieksekusi, baik terjadi exception maupun tidak.