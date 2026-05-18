public class GenericBanyakParameter {

    static class Data<T, U> {
        private T data1;
        private U data2;

        public Data(T data1, U data2) {
            this.data1 = data1;
            this.data2 = data2;
        }

        public T getData1() {
            return data1;
        }

        public U getData2() {
            return data2;
        }
    }

    public static void main(String[] args) {
        // untuk transaksi (String, Double)
        Data<String, Double> transaksi = new Data<>("TRX-1001", 150000.0);

        // untuk pelanggan (String, Integer)
        Data<String, Integer> pelanggan = new Data<>("CUST-01", 320);

        System.out.println("Transaksi : " + transaksi.getData1() + " | Rp" + transaksi.getData2());
        System.out.println("Pelanggan : " + pelanggan.getData1() + " | Poin " + pelanggan.getData2());
    }
}