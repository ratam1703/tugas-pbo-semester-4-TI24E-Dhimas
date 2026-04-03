class Bank {

    // 1. Transfer ke rekening lain (bank sama)
    void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer sebesar Rp" + jumlah + 
            " ke rekening " + rekeningTujuan + " (Bank sama)");
    }

    // 2. Transfer ke bank lain
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer sebesar Rp" + jumlah + 
            " ke rekening " + rekeningTujuan + 
            " di bank " + bankTujuan);
    }

    // 3. Transfer dengan berita
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println("Transfer sebesar Rp" + jumlah + 
            " ke rekening " + rekeningTujuan + 
            " di bank " + bankTujuan + 
            " dengan berita: " + berita);
    }

    // 4. Suku bunga default
    void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // BONUS: biaya transfer
    int hitungBiayaTransfer(String bankTujuan) {
        if (bankTujuan.equalsIgnoreCase("BNI")) {
            return 4000;
        } else if (bankTujuan.equalsIgnoreCase("BCA")) {
            return 6500;
        } else {
            return 5000;
        }
    }
}