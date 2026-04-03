class BankBNI extends Bank {

    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga BNI adalah 4%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BNI";
        System.out.println("Transfer sebesar Rp" + jumlah + 
            " ke rekening " + rekeningTujuan + 
            " di bank " + bankTujuan);
    }
}