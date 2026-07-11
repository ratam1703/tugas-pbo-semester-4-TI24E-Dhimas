package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    public Dashboard() {
        // Konfigurasi Dasar Window Dashboard
        setTitle("Dashboard - Sistem Rental Mobil");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Label Judul Utama Aplikasi
        JLabel lblTitle = new JLabel("SISTEM INFORMASI RENTAL MOBIL", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(10, 30, 460, 30);
        add(lblTitle);

        // Label Sub-Judul Informasi Akun
        JLabel lblSub = new JLabel("Selamat Datang di Dashboard Admin", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSub.setBounds(10, 65, 460, 20);
        add(lblSub);

        // Inisialisasi Tombol Menu Data Mobil
        JButton btnMobil = new JButton("DATA MOBIL");
        btnMobil.setFont(new Font("Arial", Font.BOLD, 12));
        btnMobil.setBounds(100, 110, 280, 40);
        add(btnMobil);

        // Inisialisasi Tombol Menu Data Pelanggan
        JButton btnPelanggan = new JButton("DATA PELANGGAN");
        btnPelanggan.setFont(new Font("Arial", Font.BOLD, 12));
        btnPelanggan.setBounds(100, 170, 280, 40);
        add(btnPelanggan);

        // Inisialisasi Tombol Menu Transaksi Rental
        JButton btnTransaksi = new JButton("TRANSAKSI RENTAL");
        btnTransaksi.setFont(new Font("Arial", Font.BOLD, 12));
        btnTransaksi.setBounds(100, 230, 280, 40);
        add(btnTransaksi);

        // Inisialisasi Tombol Logout Sistem
        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogout.setBounds(100, 290, 280, 40);
        btnLogout.setBackground(Color.RED);
        btnLogout.setForeground(Color.WHITE);
        add(btnLogout);

        // ==========================================
        // LOGIKA AKSI TOMBOL (EVENT LISTENERS)
        // ==========================================

        // Aksi Tombol Data Mobil
        btnMobil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataMobil(); // Membuka Frame Data Mobil
                dispose();       // Menutup Window Dashboard aktif
            }
        });

        // Aksi Tombol Data Pelanggan
        btnPelanggan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataPelanggan(); // Membuka Frame Data Pelanggan
                dispose();           // Menutup Window Dashboard aktif
            }
        });

        // Aksi Tombol Transaksi Rental
        btnTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransaksiRental(); // Membuka Frame Transaksi Rental
                dispose();             // Menutup Window Dashboard aktif
            }
        });

        // Aksi Tombol Logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menampilkan Dialog Konfirmasi sebelum Logout
                int pilihan = JOptionPane.showConfirmDialog(
                    Dashboard.this, 
                    "Apakah Anda yakin ingin keluar dari sistem?", 
                    "Konfirmasi Keluar", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (pilihan == JOptionPane.YES_OPTION) {
                    new Login(); // Kembali menampilkan Window Login awal
                    dispose();   // Mengakhiri session Dashboard
                }
            }
        });

        // Mengaktifkan visibilitas Window ke Layar
        setVisible(true);
    }
}