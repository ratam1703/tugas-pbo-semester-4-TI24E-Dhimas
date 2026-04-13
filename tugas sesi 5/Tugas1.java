import javax.swing.JOptionPane;

public class Tugas1 {
    public static void main(String[] args) {
        String belajar = JOptionPane.showInputDialog("Anda sedang belajar apa?");
        JOptionPane.showMessageDialog(null,
                "Belajar " + belajar + " sangat mudah");
    }
}