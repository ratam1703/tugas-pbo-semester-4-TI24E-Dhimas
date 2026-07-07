import java.sql.*;
import java.util.Scanner;

public class Main17 {
    // Tetap menggunakan database retail sesuai tugas Anda
    private static final String URL_DB = "jdbc:mysql://localhost:3306/toko_retail";
    private static final String USER_DB = "root";
    private static final String PASS_DB = "";
    
    private static Connection koneksi;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiDatabase();
        
        while (true) {
            tampilkanMenuUtama();
            System.out.print("Pilih Menu: ");
            String pilihanMenu = input.nextLine();
            System.out.println(); 

            switch (pilihanMenu) {
                case "1":
                    tampilkanKatalogBarang();
                    break;
                case "2":
                    sisipkanBarangBaru();
                    break;
                case "3":
                    pencarianSpesifikBarang();
                    break;
                case "4":
                    perbaruiStokAtauHarga();
                    break;
                case "5":
                    pangkasDataBarang();
                    break;
                case "0":
                    System.out.println("Sistem dimatikan. Terima kasih.");
                    putuskanDatabase();
                    System.exit(0);
                default:
                    System.out.println("Pilihan menu tidak valid!");
            }
            System.out.println();
        }
    }

    private static void inisialisasiDatabase() {
        try {
            koneksi = DriverManager.getConnection(URL_DB, USER_DB, PASS_DB);
        } catch (SQLException e) {
            System.out.println("Gagal menghubungkan ke MySQL: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void putuskanDatabase() {
        try {
            if (koneksi != null) koneksi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("+---------------------------------------+");
        System.out.println("|       SISTEM RETAIL TOKO UTAMA        |");
        System.out.println("+---------------------------------------+");
        System.out.println("|  1. Lihat Seluruh Stok                |");
        System.out.println("|  2. Registrasi Barang Baru            |");
        System.out.println("|  3. Cari Informasi Barang             |");
        System.out.println("|  4. Update Data Barang                |");
        System.out.println("|  5. Eliminasi Barang Lama             |");
        System.out.println("|  0. Keluar Keluar                     |");
        System.out.println("+---------------------------------------+");
    }

    // MENU 1: Tampil Semua Data (Sesuai format tabel rapi, isi barang berbeda)
    private static void tampilkanKatalogBarang() {
        String query = "SELECT * FROM barang";
        try (Statement stmt = koneksi.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                     INVENTORY DATA TOKO RETAIL                    |");
            System.out.println("+----+--------+-----------------------------------+-------+---------+");
            System.out.println("| #  | Kode   | Nama Barang                       | Harga | Stok    |");
            System.out.println("+----+--------+-----------------------------------+-------+---------+");

            int indeks = 1;
            while (rs.next()) {
                String kobe = rs.getString("kode");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");

                System.out.printf("| %-2d | %-6s | %-33s | %-5d | %-7d |\n", indeks++, kobe, nama, harga, stok);
            }
            
            System.out.println("+----+--------+-----------------------------------+-------+---------+");
            System.out.println("Total Ringkasan: " + (indeks - 1) + " produk ditemukan.");

        } catch (SQLException e) {
            System.out.println("Gagal membaca database: " + e.getMessage());
        }
    }

    // MENU 2: Tambah Data
    private static void sisipkanBarangBaru() {
        System.out.println("=== FORM REGISTRASI BARANG ===");
        System.out.print("Masukkan Kode Unik : "); String kd = input.nextLine();
        System.out.print("Masukkan Nama Item : "); String nm = input.nextLine();
        System.out.print("Masukkan Harga Jual: "); int hg = Integer.parseInt(input.nextLine());
        System.out.print("Masukkan Jumlah Stok: "); int sk = Integer.parseInt(input.nextLine());

        String query = "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = koneksi.prepareStatement(query)) {
            pstmt.setString(1, kd);
            pstmt.setString(2, nm);
            pstmt.setInt(3, hg);
            pstmt.setInt(4, sk);
            pstmt.executeUpdate();
            System.out.println("Sukses: Data item baru berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan item: " + e.getMessage());
        }
    }

    // MENU 3: Cari Data
    private static void pencarianSpesifikBarang() {
        System.out.println("=== HUB PENCARIAN DATA ===");
        System.out.print("Ketik Kode/Nama yang ingin dicari: ");
        String kataKunci = input.nextLine();

        String query = "SELECT * FROM barang WHERE kode = ? OR nama_barang LIKE ?";
        try (PreparedStatement pstmt = koneksi.prepareStatement(query)) {
            pstmt.setString(1, kataKunci);
            pstmt.setString(2, "%" + kataKunci + "%");
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nLog Pencarian:");
            while (rs.next()) {
                System.out.println("-> [" + rs.getString("kode") + "] " + rs.getString("nama_barang") + " | Rp." + rs.getInt("harga") + " | Stok: " + rs.getInt("stok"));
            }
        } catch (SQLException e) {
            System.out.println("Error saat mencari: " + e.getMessage());
        }
    }

    // MENU 4: Ubah Data
    private static void perbaruiStokAtauHarga() {
        System.out.println("=== MODIFIKASI DATA BARANG ===");
        System.out.print("Ketik Kode Barang yang ingin dirubah: ");
        String kdTarget = input.nextLine();

        System.out.print("Perubahan Nama  : "); String nmBaru = input.nextLine();
        System.out.print("Perubahan Harga : "); int hgBaru = Integer.parseInt(input.nextLine());
        System.out.print("Perubahan Stok  : "); int skBaru = Integer.parseInt(input.nextLine());

        String query = "UPDATE barang SET nama_barang = ?, harga = ?, stok = ? WHERE kode = ?";
        try (PreparedStatement pstmt = koneksi.prepareStatement(query)) {
            pstmt.setString(1, nmBaru);
            pstmt.setInt(2, hgBaru);
            pstmt.setInt(3, skBaru);
            pstmt.setString(4, kdTarget);
            
            int barisBerubah = pstmt.executeUpdate();
            if (barisBerubah > 0) {
                System.out.println("Sukses: Informasi barang diperbarui!");
            } else {
                System.out.println("Gagal: Kode barang tidak valid/ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Proses modifikasi gagal: " + e.getMessage());
        }
    }

    // MENU 5: Hapus Data
    private static void pangkasDataBarang() {
        System.out.println("=== PENGHAPUSAN ITEM ===");
        System.out.print("Ketik Kode Barang yang akan dihapus: ");
        String kdHapus = input.nextLine();

        String query = "DELETE FROM barang WHERE kode = ?";
        try (PreparedStatement pstmt = koneksi.prepareStatement(query)) {
            pstmt.setString(1, kdHapus);
            int barisTerhapus = pstmt.executeUpdate();
            if (barisTerhapus > 0) {
                System.out.println("Sukses: Item berhasil dieliminasi dari sistem toko.");
            } else {
                System.out.println("Gagal: Kode barang tidak terdaftar.");
            }
        } catch (SQLException e) {
            System.out.println("Proses eliminasi gagal: " + e.getMessage());
        }
    }
}