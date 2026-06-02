public class ThrowSederhana {

    public static void main(String[] args) {

        int umur = 15;

        if (umur < 17) {

            throw new ArithmeticException("umur belum cukup");

        }

        System.out.println("Boleh Membuat Sim");
    }
}