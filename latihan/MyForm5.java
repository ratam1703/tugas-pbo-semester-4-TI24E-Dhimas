import java.awt.*;
import javax.swing.*;

public class MyForm5 extends JFrame {
    BoxLayout bl = new BoxLayout(getContentPane(), BoxLayout.X_AXIS);

    MyForm5() {
        super("Belajar GUI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setLayout(bl);
        add(new Button("Tombol 1"));
        add(new Button("Tombol 2"));
        add(new Button("Tombol 3"));
        add(new Button("Tombol 4"));
        add(new Button("Tombol 5"));
        add(new Button("Tombol 6"));
        add(new Button("Tombol 7"));
        add(new Button("Tombol 8"));
        setVisible(true);
    }

    public static void main(String[] args) {
        MyForm5 form = new MyForm5();
    }
}