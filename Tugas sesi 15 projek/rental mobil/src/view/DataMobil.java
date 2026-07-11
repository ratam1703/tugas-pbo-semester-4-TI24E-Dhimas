package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import koneksi.Koneksi;

public class DataMobil extends JFrame {
    private JTextField txtPlat, txtMerk, txtTipe, txtTahun, txtWarna, txtHarga, txtStatus;
    private JTable tabelMobil;
    private DefaultTableModel modelTabel;
    private JButton btnSimpan, btnUbah, btnHapus, btnKembali;

    public DataMobil() {
        setTitle("Pengelolaan Data Mobil");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Input Form Komponen
        JLabel lblPlat = new JLabel("Plat Nomor"); lblPlat.setBounds(20, 20, 100, 25); add(lblPlat);
        txtPlat = new JTextField(); txtPlat.setBounds(130, 20, 150, 25); add(txtPlat);

        JLabel lblMerk = new JLabel("Merk"); lblMerk.setBounds(20, 55, 100, 25); add(lblMerk);
        txtMerk = new JTextField(); txtMerk.setBounds(130, 55, 150, 25); add(txtMerk);

        JLabel lblTipe = new JLabel("Tipe"); lblTipe.setBounds(20, 90, 100, 25); add(lblTipe);
        txtTipe = new JTextField(); txtTipe.setBounds(130, 90, 150, 25); add(txtTipe);

        JLabel lblTahun = new JLabel("Tahun"); lblTahun.setBounds(20, 125, 100, 25); add(lblTahun);
        txtTahun = new JTextField(); txtTahun.setBounds(130, 125, 150, 25); add(txtTahun);

        JLabel lblWarna = new JLabel("Warna"); lblWarna.setBounds(20, 160, 100, 25); add(lblWarna);
        txtWarna = new JTextField(); txtWarna.setBounds(130, 160, 150, 25); add(txtWarna);

        JLabel lblHarga = new JLabel("Harga / Hari"); lblHarga.setBounds(20, 195, 100, 25); add(lblHarga);
        txtHarga = new JTextField(); txtHarga.setBounds(130, 195, 150, 25); add(txtHarga);

        JLabel lblStatus = new JLabel("Status"); lblStatus.setBounds(20, 230, 100, 25); add(lblStatus);
        txtStatus = new JTextField(); txtStatus.setBounds(130, 230, 150, 25); add(txtStatus);

        // Tombol Aksi
        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(20, 280, 85, 30); add(btnSimpan);
        btnUbah = new JButton("Ubah"); btnUbah.setBounds(115, 280, 85, 30); add(btnUbah);
        btnHapus = new JButton("Hapus"); btnHapus.setBounds(210, 280, 85, 30); add(btnHapus);
        btnKembali = new JButton("Kembali"); btnKembali.setBounds(20, 320, 275, 30); add(btnKembali);

        // Komponen Tabel Data
        modelTabel = new DefaultTableModel(new String[]{"ID", "Plat Nomor", "Merk", "Tipe", "Tahun", "Warna", "Harga", "Status"}, 0);
        tabelMobil = new JTable(modelTabel);
        JScrollPane sp = new JScrollPane(tabelMobil);
        sp.setBounds(310, 20, 400, 400);
        add(sp);

        // Event Listeners
        loadDataTabel();

        btnSimpan.addActionListener(e -> simpanData());
        btnKembali.addActionListener(e -> { new Dashboard(); dispose(); });
        
        tabelMobil.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = tabelMobil.getSelectedRow();
                txtPlat.setText(modelTabel.getValueAt(baris, 1).toString());
                txtMerk.setText(modelTabel.getValueAt(baris, 2).toString());
                txtTipe.setText(modelTabel.getValueAt(baris, 3).toString());
                txtTahun.setText(modelTabel.getValueAt(baris, 4).toString());
                txtWarna.setText(modelTabel.getValueAt(baris, 5).toString());
                txtHarga.setText(modelTabel.getValueAt(baris, 6).toString());
                txtStatus.setText(modelTabel.getValueAt(baris, 7).toString());
            }
        });

        setVisible(true);
    }

    private void loadDataTabel() {
        modelTabel.setRowCount(0);
        try {
            Connection conn = Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mobil");
            while(rs.next()) {
                modelTabel.addRow(new Object[]{
                    rs.getInt("id_mobil"), rs.getString("plat_nomor"), rs.getString("merk"),
                    rs.getString("tipe"), rs.getInt("tahun"), rs.getString("warna"),
                    rs.getInt("harga_per_hari"), rs.getString("status")
                });
            }
        } catch(Exception e) {
            System.out.println("Gagal memuat tabel: " + e.getMessage());
        }
    }

    private void simpanData() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO mobil (plat_nomor, merk, tipe, tahun, warna, harga_per_hari, status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtPlat.getText());
            ps.setString(2, txtMerk.getText());
            ps.setString(3, txtTipe.getText());
            ps.setInt(4, Integer.parseInt(txtTahun.getText()));
            ps.setString(5, txtWarna.getText());
            ps.setInt(6, Integer.parseInt(txtHarga.getText()));
            ps.setString(7, txtStatus.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
            loadDataTabel();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + e.getMessage());
        }
    }
}