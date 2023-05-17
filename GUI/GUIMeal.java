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
}
