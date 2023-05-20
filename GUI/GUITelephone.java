// Vladimir Gray P. Velazco 1CSC
package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

// TODO: Functionality and Submit Button
public class GUITelephone {

    final static int CALL_RATE = 4;
    final static double NIGHT_DISCOUNT = 0.5;
    final static double EXTEND_DISCOUNT = 0.15;
    final static double EVAT = 0.12;

    public static void main(String[] args) {
        TelephoneUI mainProgram = new TelephoneUI();
        mainProgram.setVisible(true);

        mainProgram.submitButton.addActionListener(e -> {
            char timeOfDay = mainProgram.getTime();
            int totalHours = mainProgram.getHours();
            double cost = totalHours * CALL_RATE;
            mainProgram.setGrossCost(cost);
            if (timeOfDay == 'N')
                cost *= NIGHT_DISCOUNT;
            if (totalHours > 60)
                cost *= 1 - EXTEND_DISCOUNT;
            cost *= 1 + EVAT;
            mainProgram.setNetCost(cost);
        });
        mainProgram.cancelButton.addActionListener(e -> {
            mainProgram.revertHours();
        });
    }

}

class TelephoneUI extends JFrame {

    private ButtonGroup dayOrNight;
    private JRadioButton dayButton, nightButton;
    private JPanel CostOutputPanel, InputPanel, actionPanel;
    private JTextField callHoursInput;
    private JLabel grossCostLabel, netCostLabel;

    protected JButton submitButton, cancelButton;

    TelephoneUI() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 1, 15, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        setContentPane(contentPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GUI Calculator");

        setupOutputPanel();
        contentPanel.add(CostOutputPanel);
        setupInputPanel();
        contentPanel.add(InputPanel);
        setupActionPanel();
        contentPanel.add(actionPanel);
    }

    private void setupActionPanel() {

        actionPanel = new JPanel(new GridLayout(2, 1));
        actionPanel.add(new Panel()); // Filler for the left
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        rightPanel.add(submitButton);
        rightPanel.add(cancelButton);
        actionPanel.add(rightPanel);
    }

    private void setupOutputPanel() {
        CostOutputPanel = new JPanel();
        CostOutputPanel.setLayout(new GridLayout(2, 2));

        CostOutputPanel.add(new JLabel("Gross Cost:"));
        grossCostLabel = new JLabel("P0.00");
        CostOutputPanel.add(grossCostLabel);

        CostOutputPanel.add(new JLabel("Net Cost"));
        netCostLabel = new JLabel("P0.00");
        CostOutputPanel.add(netCostLabel);
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

    public void revertHours() {
        callHoursInput.setText("0");
    }

    public void setGrossCost(double num) {
        grossCostLabel.setText(String.format("P%.2f", num));
    }

    public void setNetCost(double num) {
        netCostLabel.setText(String.format("P%.2f", num));
    }
}
