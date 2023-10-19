package GUI;

import javax.swing.*;
import java.awt.*;

// Write a GUI program to compute the amount of a certificate of deposit on
// maturity. The sample data follows:
// Amount deposited: 80000.00
// Years: 15
// Interest rate: 7.75
// Hint: To solve this problem, compute 80000.00 (1 + 7.75 / 100)15.

public class GUIDeposit extends JFrame {

    double deposit = 0;
    double years = 0;
    double interest_rate = 0;

    public GUIDeposit() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));
    }

    public static void main(String[] args) {
        new GUIDeposit();
    }
}
