import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

public class Kalkulator extends JFrame {

    private JTextField display;
    private JButton btnTambah, btnKurang, btnKali, btnBagi, btnHitung;

    public Kalkulator() {
        super("Kalkulator Sederhana");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5)); // BorderLayout dengan jarak 5px

        // 1. Komponen Input (Utara)
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        add(display, BorderLayout.NORTH);

        // 2. Panel Tombol Operasi (Tengah)
        JPanel panelOperasi = new JPanel(new GridLayout(1, 4, 5, 5));
        btnTambah = new JButton("+");
        btnKurang = new JButton("-");
        btnKali = new JButton("*");
        btnBagi = new JButton("/");
        
        // Tambahkan tombol ke panel
        panelOperasi.add(btnTambah);
        panelOperasi.add(btnKurang);
        panelOperasi.add(btnKali);
        panelOperasi.add(btnBagi);
        
        add(panelOperasi, BorderLayout.CENTER);

        // 3. Tombol Hitung (Selatan)
        btnHitung = new JButton("HITUNG");
        btnHitung.setBackground(Color.GREEN);
        add(btnHitung, BorderLayout.SOUTH);

        // Event Handling (Contoh sederhana)
        btnHitung.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hasil akan muncul di sini!");
        });

        setVisible(true);
        setLocationRelativeTo(null); // Tengah layar
    }

    public static void main(String[] args) {
        new Kalkulator();
    }
}