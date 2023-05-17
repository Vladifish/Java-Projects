package GUI;

import java.awt.*;

import javax.swing.JFrame;

public class GUICalculator {
    public static void main(String[] args) {
        new OutputFrame();
    }
}

class OutputFrame extends JFrame {
    private Label l1, l2, l3;
    private TextField tf1, tf2, tf3;
    private Panel p1, p2, p3, p4;
    private Button add, subt, mult, divide, cancel;

    OutputFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GUI Calculator");
        setLayout(new GridLayout(4, 1, 15, 0));

        p1 = new Panel(new FlowLayout());
        p2 = new Panel(new FlowLayout());
        p3 = new Panel(new FlowLayout());
        p4 = new Panel(new FlowLayout());

        l1 = new Label("First Number:");
        tf1 = new TextField("0.0");
        l2 = new Label("Second Number:");
        tf2 = new TextField("0.0");
        l3 = new Label("Answer:");
        tf3 = new TextField("0.0");
        tf3.setEditable(false);

        p1.add(l1);
        p1.add(tf1);
        p2.add(l2);
        p2.add(tf2);
        p3.add(l3);
        p3.add(tf3);

        add = new Button("+");
        subt = new Button("-");
        mult = new Button("*");
        divide = new Button("/");
        cancel = new Button("C");

        setButtons();

        p4.add(add);
        p4.add(subt);
        p4.add(mult);
        p4.add(divide);
        p4.add(cancel);

        add(p1);
        add(p2);
        add(p3);
        add(p4);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setButtons() {
        add.addActionListener(e -> {
            tf3.setText("" +
                    (Double.parseDouble(tf1.getText()) +
                            Double.parseDouble(tf2.getText())));
        });

        subt.addActionListener(e -> {
            tf3.setText("" +
                    (Double.parseDouble(tf1.getText()) -
                            Double.parseDouble(tf2.getText())));
        });

        mult.addActionListener(e -> {
            tf3.setText("" +
                    (Double.parseDouble(tf1.getText()) *
                            Double.parseDouble(tf2.getText())));
        });

        divide.addActionListener(e -> {
            tf3.setText("" +
                    (Double.parseDouble(tf1.getText()) /
                            Double.parseDouble(tf2.getText())));
        });

        cancel.addActionListener(e -> {
            tf3.setText("0.00");
            tf1.setText("0.00");
            tf2.setText("0.00");
        });
    }
}