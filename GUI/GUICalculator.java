package GUI;

import java.awt.*;
import javax.swing.JFrame;
// import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUICalculator {
    public static void main(String[] args) {
        new OutputFrame();
    }
}

class OutputFrame extends JFrame {
    private Label l1, l2, l3;
    private TextField tf1, tf2, tf3;
    private Panel p1, p2, p3;

    OutputFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        l1 = new Label("Label 1");
        tf1 = new TextField("Textfield 1");
        l2 = new Label("Label 2");
        tf2 = new TextField("Textfield 3");
        l3 = new Label("Label 3");
        tf3 = new TextField("Textfield 3");

        p1 = new Panel(new FlowLayout());
        p2 = new Panel(new FlowLayout());
        p3 = new Panel(new FlowLayout());

        p1.add(l1);
        p1.add(tf1);
        p2.add(l2);
        p2.add(tf2);
        p3.add(l3);
        p3.add(tf3);

        add(p1);
        add(p2);
        add(p3);

        setBounds(500, 500, 300, 300);
        pack();
        setVisible(true);
    }
}