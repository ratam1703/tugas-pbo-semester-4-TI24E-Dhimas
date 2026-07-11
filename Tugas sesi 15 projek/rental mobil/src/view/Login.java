package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import koneksi.Koneksi;

public class Login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnReset;

    public Login() {
        setTitle("Login Rental Mobil");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("SISTEM RENTAL MOBIL", SwingConstants.CENTER);
        lblTitle.setBounds(10, 20, 360, 25);
        add(lblTitle);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(40, 70, 80, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(140, 70, 200, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(40, 110, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 110, 200, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(80, 160, 100, 30);
        add(btnLogin);

        btnReset = new JButton("Reset");
        btnReset.setBounds(200, 160, 100, 30);
        add(btnReset);

        // Aksi Tombol Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesLogin();
            }
        });

        // Aksi Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });

        setVisible(true);
    }

    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Berhasil!");
                new Dashboard(); // Buka Dashboard
                this.dispose();  // Tutup form login
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password Salah!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}