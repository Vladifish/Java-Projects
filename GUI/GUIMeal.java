// Vladimir Gray P. Velazco 1-CSC
package GUI;

import java.awt.*;
import javax.swing.JFrame;

public class GUIMeal {
    public static void main(String[] args) {
        JFrame menu = new MenuFrame();
        menu.setVisible(true);
    }
}

class MenuFrame extends JFrame {
    Panel header, body, orders;

    MenuFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Ordertaker");
        setLayout(new GridLayout(3, 1));
        setSize(600, 600);
        setLocationRelativeTo(null);

        createHeader();
        add(header);
        createBody();
        add(body);
    }

    void createHeader() {
        header = new Panel(new GridLayout(1, 2));

        // Creating the header
        Component[] headerObjects = new Component[2];

        Label mealCode = new Label("Meal Code");
        mealCode.setAlignment(Label.CENTER);
        headerObjects[0] = mealCode;

        Panel rightPanel = new Panel(new GridLayout(2, 1));
        Label size = new Label("Size");
        size.setAlignment(Label.CENTER);
        rightPanel.add(size);

        Panel sizes = new Panel(new GridLayout(1, 2));
        sizes.add(new Label("Regular - R"));
        sizes.add(new Label("Upsize - U"));
        rightPanel.add(sizes);

        headerObjects[1] = rightPanel;

        addComponentsToPanel(header, headerObjects);
    }

    void addComponentsToPanel(Panel panel, Component[] components) {
        for (Component c : components)
            panel.add(c);
    }

    void createBody() {
        body = new Panel(new GridLayout(3, 1));
        Panel[] rows = new Panel[3];
        rows[0] = createRow("A", "78.75", "102.50");
        rows[1] = createRow("B", "68.75", "85.70");
        rows[2] = createRow("C", "70.25", "95.60");

        addComponentsToPanel(body, rows);
    }

    private Panel createRow(String c1, String c2, String c3) {
        Panel row = new Panel(new GridLayout(1, 3));
        row.add(new Label(c1, Label.CENTER));
        row.add(new Label(c2, Label.CENTER));
        row.add(new Label(c3, Label.CENTER));
        return row;
    }
}
