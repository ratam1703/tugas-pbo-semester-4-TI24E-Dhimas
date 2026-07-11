package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import koneksi.Koneksi;

public class TransaksiRental extends JFrame {
    private JComboBox<String> cbPelanggan, cbMobil;
    private JTextField txtTglPinjam, txtTglKembali, txtLamaSewa, txtTotalHarga, txtStatus;
    private JTable tabelTransaksi;
    private DefaultTableModel modelTabel;
    private JButton btnSimpan, btnHapus, btnKembali;

    public TransaksiRental() {
        setTitle("Transaksi Rental Mobil");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Input Form Komponen
        JLabel lblPelanggan = new JLabel("Pilih Pelanggan"); lblPelanggan.setBounds(20, 20, 100, 25); add(lblPelanggan);
        cbPelanggan = new JComboBox<>(); cbPelanggan.setBounds(140, 20, 150, 25); add(cbPelanggan);

        JLabel lblMobil = new JLabel("Pilih Mobil"); lblMobil.setBounds(20, 55, 100, 25); add(lblMobil);
        cbMobil = new JComboBox<>(); cbMobil.setBounds(140, 55, 150, 25); add(cbMobil);

        JLabel lblTglPinjam = new JLabel("Tgl Pinjam (Y-M-D)"); lblTglPinjam.setBounds(20, 90, 110, 25); add(lblTglPinjam);
        txtTglPinjam = new JTextField("2026-07-11"); txtTglPinjam.setBounds(140, 90, 150, 25); add(txtTglPinjam);

        JLabel lblTglKembali = new JLabel("Tgl Kembali (Y-M-D)"); lblTglKembali.setBounds(20, 125, 110, 25); add(lblTglKembali);
        txtTglKembali = new JTextField("2026-07-12"); txtTglKembali.setBounds(140, 125, 150, 25); add(txtTglKembali);

        JLabel lblLama = new JLabel("Lama Sewa (Hari)"); lblLama.setBounds(20, 160, 110, 25); add(lblLama);
        txtLamaSewa = new JTextField("1"); txtLamaSewa.setBounds(140, 160, 150, 25); add(txtLamaSewa);

        JLabel lblTotal = new JLabel("Total Bayar"); lblTotal.setBounds(20, 195, 100, 25); add(lblTotal);
        txtTotalHarga = new JTextField(); txtTotalHarga.setBounds(140, 195, 150, 25); add(txtTotalHarga);

        JLabel lblStatus = new JLabel("Status"); lblStatus.setBounds(20, 230, 100, 25); add(lblStatus);
        txtStatus = new JTextField("Selesai"); txtStatus.setBounds(140, 230, 150, 25); add(txtStatus);

        // Tombol Aksi
        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(20, 280, 120, 30); add(btnSimpan);
        btnHapus = new JButton("Hapus"); btnHapus.setBounds(170, 280, 120, 30); add(btnHapus);
        btnKembali = new JButton("Kembali"); btnKembali.setBounds(20, 320, 270, 30); add(btnKembali);

        // Komponen Tabel Data (Menyesuaikan kolom tabel rental)
        modelTabel = new DefaultTableModel(new String[]{"ID Rental", "Pelanggan", "Mobil", "Tgl Pinjam", "Tgl Kembali", "Lama", "Total Bayar", "Status"}, 0);
        tabelTransaksi = new JTable(modelTabel);
        JScrollPane sp = new JScrollPane(tabelTransaksi);
        sp.setBounds(310, 20, 500, 400);
        add(sp);

        // Mengisi data drop-down dan tabel
        isiPilihanPelanggan();
        isiPilihanMobil();
        loadDataTabel();

        // Event Trigger Tombol
        btnSimpan.addActionListener(e -> simpanTransaksi());
        btnHapus.addActionListener(e -> hapusTransaksi());
        btnKembali.addActionListener(e -> { new Dashboard(); dispose(); });

        setVisible(true);
    }

    private void isiPilihanPelanggan() {
        try {
            Connection conn = Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_pelanggan, nama FROM pelanggan");
            while(rs.next()) {
                cbPelanggan.addItem(rs.getInt("id_pelanggan") + " - " + rs.getString("nama"));
            }
        } catch(Exception e) {
            System.out.println("Gagal memuat combobox pelanggan: " + e.getMessage());
        }
    }

    private void isiPilihanMobil() {
        try {
            Connection conn = Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_mobil, merk, plat_nomor FROM mobil");
            while(rs.next()) {
                cbMobil.addItem(rs.getInt("id_mobil") + " - " + rs.getString("merk"));
            }
        } catch(Exception e) {
            System.out.println("Gagal memuat combobox mobil: " + e.getMessage());
        }
    }

    private void loadDataTabel() {
        modelTabel.setRowCount(0);
        try {
            Connection conn = Koneksi.getConnection();
            // Query menggunakan nama tabel 'rental' dan kolom yang sesuai di phpMyAdmin
            String sql = "SELECT r.id_rental, p.nama, m.merk, r.tanggal_pinjam, r.tanggal_kembali, r.lama_sewa, r.total_bayar, r.status " +
                         "FROM rental r JOIN pelanggan p ON r.id_pelanggan = p.id_pelanggan " +
                         "JOIN mobil m ON r.id_mobil = m.id_mobil";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                modelTabel.addRow(new Object[]{
                    rs.getInt("id_rental"), 
                    rs.getString("nama"), 
                    rs.getString("merk"),
                    rs.getDate("tanggal_pinjam"), 
                    rs.getDate("tanggal_kembali"), 
                    rs.getInt("lama_sewa"),
                    rs.getInt("total_bayar"),
                    rs.getString("status")
                });
            }
        } catch(Exception e) {
            System.out.println("Gagal memuat tabel rental: " + e.getMessage());
        }
    }

    private void simpanTransaksi() {
        try {
            Connection conn = Koneksi.getConnection();
            // INSERT menargetkan ke tabel 'rental'
            String sql = "INSERT INTO rental (id_pelanggan, id_mobil, tanggal_pinjam, tanggal_kembali, lama_sewa, total_bayar, status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            String idPelanggan = cbPelanggan.getSelectedItem().toString().split(" - ")[0];
            String idMobil = cbMobil.getSelectedItem().toString().split(" - ")[0];
            
            ps.setInt(1, Integer.parseInt(idPelanggan));
            ps.setInt(2, Integer.parseInt(idMobil));
            ps.setString(3, txtTglPinjam.getText());
            ps.setString(4, txtTglKembali.getText());
            ps.setInt(5, Integer.parseInt(txtLamaSewa.getText()));
            ps.setInt(6, Integer.parseInt(txtTotalHarga.getText()));
            ps.setString(7, txtStatus.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan!");
            loadDataTabel();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan Transaksi: " + e.getMessage());
        }
    }

    private void hapusTransaksi() {
        int baris = tabelTransaksi.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris transaksi rental yang akan dihapus!");
            return;
        }
        String id = modelTabel.getValueAt(baris, 0).toString();
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "DELETE FROM rental WHERE id_rental=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Transaksi Rental Berhasil Dihapus!");
            loadDataTabel();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus Transaksi: " + e.getMessage());
        }
    }
}