import java.awt.*;
import java.awt.event.*;

// just display not actually functional
// java.awt topic project
// TODO: Error Handling

public class ColorCalculator extends WindowAdapter {
    private Frame f;

    private Button computeButton, clearButton;

    private TextField redTF, blueTF, greenTF, alphaTF;

    private Label redLabel, blueLabel, greenLabel, alphaLabel;

    private Panel inputArea, outputArea;

    // all the current text-field values, used for cross-checking
    // also used for reverting text in a text-field for errors
    private String curr_red, curr_blue, curr_green, curr_alpha;

    // the app would be divided into two parts
    // top would be where the user would provide input
    // bottom is the color output
    public ColorCalculator() {
        f = new Frame("My Color Calculator");
        f.addWindowListener(this); // so that the GUI closes on press

        // all the labels
        redLabel = new Label("Red:");
        blueLabel = new Label("Blue:");
        greenLabel = new Label("Green:");
        alphaLabel = new Label("Alpha: ");

        redLabel.setSize(400, 12);

        // all the text-fields
        redTF = new TextField("0");
        blueTF = new TextField("0");
        greenTF = new TextField("0");
        alphaTF = new TextField("0");

        // buttons
        computeButton = new Button("Compute");
        clearButton = new Button("Clear");

        // panels
        inputArea = new Panel(new GridLayout(5, 2, 0, 5));
        outputArea = new Panel();

        clearTextFieldValues();
    }

    public void startApp() {
        f.setSize(300, 500);
        f.setLayout(new GridLayout(2, 1));

        inputArea.setSize(250, 200);
        outputArea.setSize(250, 300);

        setupInputArea();
        setupListeners();

        f.add(inputArea);
        f.add(outputArea);

        f.setVisible(true);

    }

    public void setupInputArea() {
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

    public void setupListeners() {

        // updates the color output
        computeButton.addActionListener(
                e -> {
                    int red, green, blue, alpha;

                    red = Integer.parseInt(redTF.getText());
                    green = Integer.parseInt(greenTF.getText());
                    blue = Integer.parseInt(blueTF.getText());
                    alpha = Integer.parseInt(alphaTF.getText());

                    outputArea.setBackground(new Color(red, green, blue, alpha));
                });

        // sets all the values in the TF to '0'
        clearButton.addActionListener(
                e -> {
                    clearTextFieldValues();
                    updateTextFields();
                });

        // checks if there's a change in a text field
        // then checks for errors, if no change then go as usual
        TextListener textFieldCheck = (e) -> {
            TextField tf = (TextField) e.getSource();
            String new_val = tf.getText();

            // input clean-up
            // text field must pass through a series of checks,

            if (!checkIfNumber(new_val)) {
                System.out.println("Not a number!");
                updateTextFields();
                updateStoredValues();
                return;
            }

            if (!checkIfInRange(new_val)) {
                System.out.println("Number must be inside the range (0-255)");
                updateTextFields();
                updateStoredValues();
                return;
            }

            updateStoredValues();
            System.out.println(new_val);
        };

        redTF.addTextListener(textFieldCheck);
        blueTF.addTextListener(textFieldCheck);
        greenTF.addTextListener(textFieldCheck);
        alphaTF.addTextListener(textFieldCheck);
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

    private void updateStoredValues() {
        // I could make this an array, if we need to scale the application
        curr_red = redTF.getText();
        curr_blue = blueTF.getText();
        curr_green = greenTF.getText();
        curr_alpha = alphaTF.getText();
    }

    public static void main(String[] args) {
        ColorCalculator mainFrame = new ColorCalculator();
        mainFrame.startApp();
    }
}
