import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelloGUI implements ActionListener {
    JButton b1,b2;
    JTextField tf1,tf2;
     
    public HelloGUI(){
        JFrame frame = new JFrame("Belajar Form");
        frame.setSize(400,100);
        frame.setLocationRelativeTo(null);//Tampil di tengah
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        b1 = new JButton("Submit");
        b1.addActionListener(this);

        b2 = new JButton("Clear");
        b2.addActionListener(this);

        tf1 = new JTextField(15);
        tf2 = new JTextField(15);

        JLabel l1 = new JLabel("Nama");
        JLabel l2 = new JLabel("Alamat");

        JPanel panel = new JPanel();
        //panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(new GridLayout(3, 2));
        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel.add(b1);
        panel.add(b2);
        panel.setBackground(new Color(200,100,50));
        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        HelloGUI hello = new HelloGUI();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String teks = tf1.getText();
            String alamat = tf2.getText();
            String result = "Hello " + teks + "\n";
            result += "Alamat :" + alamat;

            JOptionPane.showMessageDialog(null, result);
        }
        else if(e.getSource()==b2){
            tf1.setText("");
            tf2.setText("");
        }
    }
}
