package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import koneksi.Koneksi;

public class DataPelanggan extends JFrame {
    private JTextField txtNik, txtNama, txtTelepon, txtAlamat;
    private JTable tabelPelanggan;
    private DefaultTableModel modelTabel;
    private JButton btnSimpan, btnUbah, btnHapus, btnKembali;

    public DataPelanggan() {
        setTitle("Pengelolaan Data Pelanggan");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Input Form Komponen
        JLabel lblNik = new JLabel("NIK / No KTP"); lblNik.setBounds(20, 20, 100, 25); add(lblNik);
        txtNik = new JTextField(); txtNik.setBounds(130, 20, 150, 25); add(txtNik);

        JLabel lblNama = new JLabel("Nama Lengkap"); lblNama.setBounds(20, 55, 100, 25); add(lblNama);
        txtNama = new JTextField(); txtNama.setBounds(130, 55, 150, 25); add(txtNama);

        JLabel lblTelepon = new JLabel("No. Telepon"); lblTelepon.setBounds(20, 90, 100, 25); add(lblTelepon);
        txtTelepon = new JTextField(); txtTelepon.setBounds(130, 90, 150, 25); add(txtTelepon);

        JLabel lblAlamat = new JLabel("Alamat"); lblAlamat.setBounds(20, 125, 100, 25); add(lblAlamat);
        txtAlamat = new JTextField(); txtAlamat.setBounds(130, 125, 150, 25); add(txtAlamat);

        // Tombol Aksi
        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(20, 180, 85, 30); add(btnSimpan);
        btnUbah = new JButton("Ubah"); btnUbah.setBounds(115, 180, 85, 30); add(btnUbah);
        btnHapus = new JButton("Hapus"); btnHapus.setBounds(210, 180, 85, 30); add(btnHapus);
        btnKembali = new JButton("Kembali"); btnKembali.setBounds(20, 220, 275, 30); add(btnKembali);

        // Komponen Tabel Data
        modelTabel = new DefaultTableModel(new String[]{"ID", "NIK", "Nama", "Alamat", "No. HP"}, 0);
        tabelPelanggan = new JTable(modelTabel);
        JScrollPane sp = new JScrollPane(tabelPelanggan);
        sp.setBounds(310, 20, 400, 350);
        add(sp);

        // Memuat Data Pertama Kali
        loadDataTabel();

        // Event Trigger
        btnSimpan.addActionListener(e -> simpanData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnKembali.addActionListener(e -> { new Dashboard(); dispose(); });
        
        tabelPelanggan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = tabelPelanggan.getSelectedRow();
                txtNik.setText(modelTabel.getValueAt(baris, 1).toString());
                txtNama.setText(modelTabel.getValueAt(baris, 2).toString());
                txtAlamat.setText(modelTabel.getValueAt(baris, 3).toString());
                txtTelepon.setText(modelTabel.getValueAt(baris, 4).toString());
            }
        });

        setVisible(true);
    }

    private void loadDataTabel() {
        modelTabel.setRowCount(0);
        try {
            Connection conn = Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pelanggan");
            while(rs.next()) {
                modelTabel.addRow(new Object[]{
                    rs.getInt("id_pelanggan"), 
                    rs.getString("nik"), 
                    rs.getString("nama"), 
                    rs.getString("alamat"),
                    rs.getString("no_hp") // Disesuaikan dengan phpMyAdmin kamu
                });
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void simpanData() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO pelanggan (nik, nama, alamat, no_hp) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNik.getText());
            ps.setString(2, txtNama.getText());
            ps.setString(3, txtAlamat.getText());
            ps.setString(4, txtTelepon.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pelanggan Berhasil Ditambahkan!");
            loadDataTabel();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void ubahData() {
        int baris = tabelPelanggan.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pelanggan yang akan diubah!");
            return;
        }
        String id = modelTabel.getValueAt(baris, 0).toString();
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE pelanggan SET nik=?, nama=?, alamat=?, no_hp=? WHERE id_pelanggan=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNik.getText());
            ps.setString(2, txtNama.getText());
            ps.setString(3, txtAlamat.getText());
            ps.setString(4, txtTelepon.getText());
            ps.setString(5, id);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Pelanggan Berhasil Diperbarui!");
            loadDataTabel();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Mengubah: " + e.getMessage());
        }
    }

    private void hapusData() {
        int baris = tabelPelanggan.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pelanggan yang akan dihapus!");
            return;
        }
        String id = modelTabel.getValueAt(baris, 0).toString();
        int opsi = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus pelanggan ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if(opsi == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Koneksi.getConnection();
                String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Pelanggan Berhasil Dihapus!");
                loadDataTabel();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Menghapus: " + e.getMessage());
            }
        }
    }
}