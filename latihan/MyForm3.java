import java.awt.*;
import javax.swing.*;

public class MyForm3 extends JFrame {

    JButton b1 = new JButton("Tombol 1");
    JButton b2 = new JButton("Tombol 2");
    JButton b3 = new JButton("Tombol 3");
    JButton b4 = new JButton("Tombol 4");
    JButton b5 = new JButton("Tombol 5");

    MyForm3() {
        super("Belajar GUI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400,400));
        getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        BorderLayout BL = new BorderLayout(5,5);
        setLayout(BL);
        add("North",b1);
        add("South",b2);
        add("West",b3);
        add("East",b4);
        add("Center",b5);   
        setVisible(true);
    }
    public static void main(String[] args) {
        MyForm3 form = new MyForm3();
    }
}