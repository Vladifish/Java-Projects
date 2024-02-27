import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// just display not actually functional
// java.awt topic project
// TODO: Error Handling

public class ColorCalculator extends WindowAdapter {
    private JFrame f;

    private JButton computeButton, clearButton;

    private JTextField redTF, blueTF, greenTF, alphaTF;

    private JLabel redLabel, blueLabel, greenLabel, alphaLabel;

    private JPanel inputArea, outputArea;

    // all the current text-field values, used for cross-checking
    // also used for reverting text in a text-field for errors
    private String curr_red, curr_blue, curr_green, curr_alpha;

    // the app would be divided into two parts
    // top would be where the user would provide input
    // bottom is the color output
    public ColorCalculator() {
        f = new JFrame("My Color Calculator");
        f.addWindowListener(this); // so that the GUI closes on press

        // all the labels
        redLabel = new JLabel("Red:");
        blueLabel = new JLabel("Blue:");
        greenLabel = new JLabel("Green:");
        alphaLabel = new JLabel("Alpha: ");

        redLabel.setSize(400, 12);

        // all the text-fields
        redTF = new JTextField("0");
        blueTF = new JTextField("0");
        greenTF = new JTextField("0");
        alphaTF = new JTextField("0");

        // buttons
        computeButton = new JButton("Compute");
        clearButton = new JButton("Clear");

        // panels
        inputArea = new JPanel(new GridLayout(5, 2, 0, 5));
        outputArea = new JPanel();

        clearTextFieldValues();
    }

    public void startApp() {
        f.setSize(300, 500);
        f.setLayout(new GridLayout(2, 1));

        inputArea.setPreferredSize(new Dimension(250, 200));
        outputArea.setPreferredSize(new Dimension(250, 300));

        setupInputArea();
        setupListeners();

        f.add(inputArea);
        f.add(outputArea);

        f.setVisible(true);

    }

    private void setupInputArea() {
        inputArea.add(redLabel);
        inputArea.add(redTF);

        inputArea.add(blueLabel);
        inputArea.add(blueTF);

        inputArea.add(greenLabel);
        inputArea.add(greenTF);

        inputArea.add(alphaLabel);
        inputArea.add(alphaTF);

        inputArea.add(computeButton);
        inputArea.add(clearButton);
    }

    private void setupListeners() {

        // updates the color output
        computeButton.addActionListener(
                e -> {
                    int red, green, blue, alpha;

                    red = Integer.parseInt(redTF.getText());
                    green = Integer.parseInt(greenTF.getText());
                    blue = Integer.parseInt(blueTF.getText());
                    alpha = Integer.parseInt(alphaTF.getText());

                    System.out.printf("r = %d, g = %d, b = %d, a = %d \n", red, green, blue, alpha);
                    outputArea.setBackground(new Color(red, green, blue, alpha));
                });

        // sets all the values in the TF to '0'
        clearButton.addActionListener(
                e -> {
                    clearTextFieldValues();
                    updateTextFields();
                });

    }

    public boolean checkIfNumber(String val) {
        return val.matches("^[0-9].*");
    }

    public boolean checkIfInRange(String val) throws NumberFormatException {
        int num = Integer.parseInt(val);
        return num <= 255 && num >= 0;
    }

    // exits the page on clicking the exit button
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    private void clearTextFieldValues() {
        curr_red = "0";
        curr_blue = "0";
        curr_green = "0";
        curr_alpha = "0";
    }

    private void updateTextFields() {
        redTF.setText(curr_red);
        greenTF.setText(curr_blue);
        blueTF.setText(curr_green);
        alphaTF.setText(curr_alpha);
    }

    public static void main(String[] args) {
        ColorCalculator mainFrame = new ColorCalculator();
        mainFrame.startApp();
    }
}
