public class aritmatik2 {
    
    public static void main(string[] args){

        try{
            int hasil = 10 / 0 ;  
        } catch (Exception e) {
            System.out.println(hasil);

        } catch (ArithmeticException e) {
            System.out.println();
        }
    }
}
