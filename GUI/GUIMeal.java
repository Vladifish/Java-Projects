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

        header = new Panel(new GridLayout(2, 1));

    }

    void addComponentsToPanel(Panel panel, Component[] components) {
        for (Component c : components)
            panel.add(c);
    }
}
