import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BiodataMahasiswa extends JFrame implements ActionListener {

    JLabel lblNim, lblNama, lblProdi;
    JTextField txtNim, txtNama, txtProdi;
    JButton btnTampil, btnReset;
    JTextArea txtOutput;

    public BiodataMahasiswa() {

        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Input
        lblNim = new JLabel("NIM");
        lblNim.setBounds(30, 20, 100, 25);
        add(lblNim);

        txtNim = new JTextField();
        txtNim.setBounds(150, 20, 300, 25);
        add(txtNim);

        lblNama = new JLabel("Nama");
        lblNama.setBounds(30, 60, 100, 25);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150, 60, 300, 25);
        add(txtNama);

        lblProdi = new JLabel("Program Studi");
        lblProdi.setBounds(30, 100, 100, 25);
        add(lblProdi);

        txtProdi = new JTextField();
        txtProdi.setBounds(150, 100, 300, 25);
        add(txtProdi);

        // Tombol
        btnTampil = new JButton("Tampilkan");
        btnTampil.setBounds(150, 150, 120, 35);
        btnTampil.addActionListener(this);
        add(btnTampil);

        btnReset = new JButton("Reset");
        btnReset.setBounds(290, 150, 100, 35);
        btnReset.addActionListener(this);
        add(btnReset);

        // Output
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 15));

        JScrollPane scroll = new JScrollPane(txtOutput);
        scroll.setBounds(30, 210, 470, 150);
        add(scroll);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnTampil) {

            String nim = txtNim.getText();
            String nama = txtNama.getText();
            String prodi = txtProdi.getText();

            txtOutput.setText(
                    "=========== BIODATA MAHASISWA ===========\n\n" +
                    "NIM            : " + nim + "\n" +
                    "Nama           : " + nama + "\n" +
                    "Program Studi  : " + prodi
            );

        } else if (e.getSource() == btnReset) {

            txtNim.setText("");
            txtNama.setText("");
            txtProdi.setText("");
            txtOutput.setText("");

        }
    }

    public static void main(String[] args) {
        new BiodataMahasiswa();
    }
}