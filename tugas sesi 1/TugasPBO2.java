import javax.swing.JOptionPane;

public class TugasPBO2 {
    public static void main(String[] args) {

        // Menampilkan input dialog
        String belajar = JOptionPane.showInputDialog(
                null,
                "Anda sedang belajar apa?",
                "Input",
                JOptionPane.QUESTION_MESSAGE
        );

        // Menampilkan message dialog
        JOptionPane.showMessageDialog(
                null,
                "Belajar " + belajar + " sangat mudah",
                "Message",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}