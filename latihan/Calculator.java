import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private JTextField display;
    private double firstNumber = 0;
    private String operation = "";
    private boolean operationPressed = false;

    public Calculator() {
        // Setup Frame
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Display (North)
        display = new JTextField();
        display.setFont(new Font("Verdana", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        mainPanel.add(display, BorderLayout.SOUTH);

        // Button Panel (Center)
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Button Labels
        String[] buttons = {
                "C", "DEL", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", "±"
        };

        // Create Buttons
        for (String text : buttons) {
            JButton button = createButton(text);
            buttonPanel.add(button);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // Color for operators
        if (text.matches("[+\\-*/%=]")) {
            button.setBackground(new Color(220, 240, 255));
        }

        // Color for special buttons
        if (text.equals("C") || text.equals("DEL")) {
            button.setBackground(new Color(255, 220, 220));
        }

        // Button Functionality
        button.addActionListener(e -> handleButtonClick(text));
        return button;
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                display.setText("");
                firstNumber = 0;
                operation = "";
                operationPressed = false;
                break;
            case "DEL":
                if (!display.getText().isEmpty()) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
                break;
            case "±":
                if (!display.getText().isEmpty()) {
                    double value = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(-value));
                }
                break;
            case "%":
                if (!display.getText().isEmpty()) {
                    double value = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(value / 100));
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!display.getText().isEmpty()) {
                    if (operationPressed) {
                        // Replace previous operation if another operation is pressed
                        String currentText = display.getText();
                        display.setText(currentText.substring(0, currentText.length() - 3));
                    }
                    firstNumber = Double.parseDouble(display.getText());
                    operation = text;
                    display.setText(display.getText() + " " + text + " ");
                    operationPressed = true;
                }
                break;
            case "=":
                if (!display.getText().isEmpty() && !operation.isEmpty()) {
                    String[] parts = display.getText().split(" ");
                    if (parts.length >= 3) {
                        double secondNumber = Double.parseDouble(parts[2]);
                        double result = calculate(firstNumber, secondNumber, operation);
                        display.setText(String.valueOf(result));
                        operation = "";
                        operationPressed = false;
                    }
                }
                break;
            default:
                if (operationPressed) {
                    // After operation is pressed, clear the display for new number
                    String currentText = display.getText();
                    display.setText(currentText + text);
                    operationPressed = false;
                } else {
                    display.setText(display.getText() + text);
                }
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}