// Vladimir Gray P. Velazco 1CSC
package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

public class GUITelephone {

    final static int CALL_RATE = 4;
    final static double NIGHT_DISCOUNT = 0.5;
    final static double EXTEND_DISCOUNT = 0.15;
    final static double EVAT = 0.12;

    public static void main(String[] args) {
        TelephoneUI mainProgram = new TelephoneUI();
        mainProgram.setVisible(true);
    }

}

class TelephoneUI extends JFrame {

    private ButtonGroup dayOrNight;
    private JRadioButton dayButton, nightButton;
    private JPanel CostOutputPanel, InputPanel;
    private JTextField callHoursInput;

    TelephoneUI() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1, 15, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setContentPane(contentPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GUI Calculator");

        // setupOutputPanel();
        // add(CostOutputPanel);
        setupInputPanel();
        add(InputPanel);
    }

    private void setupOutputPanel() {

    }

    private void setupButtons() {
        dayOrNight = new ButtonGroup();
        dayButton = new JRadioButton("DAY", true);
        nightButton = new JRadioButton("NIGHT");
        dayOrNight.add(dayButton);
        dayOrNight.add(nightButton);
    }

    private void setupInputPanel() {

        InputPanel = new JPanel(new GridLayout(1, 2));
        setupButtons();

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(dayButton);
        buttons.add(nightButton);

        JPanel textInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textInput.add(new JLabel("Call Length (minutes): "));
        callHoursInput = new JTextField("0", 3);
        // callHoursInput.setSize(50, 20);
        textInput.add(callHoursInput);

        InputPanel.add(buttons);
        InputPanel.add(textInput);
    }

    public char getTime() {
        if (dayButton.isSelected()) {
            return 'D';
        } else {
            return 'N';
        }
    }

    public int getHours() {
        return Integer.parseInt(callHoursInput.getText());
    }
}
