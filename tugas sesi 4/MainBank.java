public class MainBank {
    public static void main(String[] args) {

        Bank bank = new Bank();
        BankBNI bni = new BankBNI();
        BankBCA bca = new BankBCA();

        System.out.println("=== METHOD OVERLOADING ===");
        bank.transferUang(100000, "123456");
        bank.transferUang(200000, "987654", "BCA");
        bank.transferUang(300000, "555666", "BNI", "Bayar Hutang");

        bank.sukuBunga();

        System.out.println("\n=== METHOD OVERRIDING ===");
        bni.sukuBunga();
        bca.sukuBunga();

        bni.transferUang(150000, "111222", "ApaAja");
        bca.transferUang(250000, "333444", "ApaAja");

        System.out.println("\n=== BONUS BIAYA TRANSFER ===");
        System.out.println("Biaya ke BNI: Rp" + bank.hitungBiayaTransfer("BNI"));
        System.out.println("Biaya ke BCA: Rp" + bank.hitungBiayaTransfer("BCA"));
        System.out.println("Biaya ke Bank lain: Rp" + bank.hitungBiayaTransfer("Mandiri"));
    }
}