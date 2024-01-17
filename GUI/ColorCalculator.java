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
        computeButton.addActionListener(
                e -> {
                    int red, green, blue, alpha;

                    red = Integer.parseInt(redTF.getText());
                    green = Integer.parseInt(greenTF.getText());
                    blue = Integer.parseInt(blueTF.getText());
                    alpha = Integer.parseInt(alphaTF.getText());

                    outputArea.setBackground(new Color(red, green, blue, alpha));
                });

        clearButton.addActionListener(
                e -> {
                    redTF.setText("0");
                    greenTF.setText("0");
                    blueTF.setText("0");
                    alphaTF.setText("0");
                });
    }

    // private class computeEvent implements ActionListener {
    // public void ActionPerformed(ActionEvent e) {

    // }
    // }

    // exits the page on clicking the exit button
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    public static void main(String[] args) {
        ColorCalculator mainFrame = new ColorCalculator();
        mainFrame.startApp();
    }
}
