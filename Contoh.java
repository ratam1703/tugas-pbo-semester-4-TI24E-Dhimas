class Tabungan {
    double saldo; //tidak dienkapsulasi

    void tambah(double jumlah) {
        saldo += jumlah;
    }

    void ambil(double jumlah) {
        saldo -= jumlah;   
    }

    void infosaldo() {

    }
 }

 public class Contoh {
    public static void main(String[] args) {
        Tabungan yulhan = new Tabungan();
        // yulhan.saldo = -100000;
        yulhan.tambah(100000);
        yulhan.ambil(50000);
        yulhan.infosaldo();

    }
 }
