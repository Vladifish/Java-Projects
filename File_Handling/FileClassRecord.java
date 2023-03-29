// Vladimir Gray P. Velazco 1CSC
package File_Handling; // maybe also some GUI manipulation

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FileClassRecord {
    public static void main(String[] args) {
        JFrame runningFrame = new JFrame("Class Record Creator/Viewer");
        runningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runningFrame.setLayout(null);
        runningFrame.setSize(500, 500);
        runningFrame.setVisible(true);
    }

    public static boolean run(JFrame frame) {
        JButton exitButton = new JButton("Exit");
        JButton continueButton = new JButton("Continue");
        return true; // fix later
    }
}
