
import view.Login;

public class Mainn {
    public static void main(String[] args) {
        // Memanggil form login menggunakan SwingUtilities agar thread GUI aman
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login lg = new Login();
                lg.setVisible(true); // Memastikan frame benar-benar tampil
            }
        });
    }
}