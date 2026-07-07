import javax.swing.*;
import java.awt.*;

public class MyForm2 extends JFrame {

    JButton b1 = new JButton("Button 1");
    JButton b2 = new JButton("Button 2");
    JButton b3 = new JButton("Button 3");
    JButton b4 = new JButton("Button 4");
    JButton b5 = new JButton("Button 5");

    public MyForm2() {
        super("Belajar GUI dengan Java Swing");

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);

        FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);
        setLayout(f1);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyForm2();
    }
}