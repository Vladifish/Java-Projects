// Vladimir Gray P. Velazco 1-CSC
// The awt library has a complicated way to make the border so I decided to not do it
package GUI;

import java.awt.*;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class GUIMeal {
    public static void main(String[] args) {
        JFrame menu = new MenuFrame();
        menu.setVisible(true);
    }
}

class MenuFrame extends JFrame {
    private Panel header, body, orders, payment; // Main Panels
    private Panel lastItemAdded; // Utility Panels
    private Button submitButton, undoButton;
    private Label netCost, grossCost;

    TextField amountField;
    JComboBox<String> mealOptions;
    Stack<String[]> order_stack = new Stack<>();

    final static String[] MEAL_OPTIONS = {
            "Meal A", "Meal B", "MEAL C",
            "Extra Rice", "Salad", "Soup", "Dessert"
    };

    final static String[] MENU = {
            "A", "78.75", "102.50",
            "B", "68.75", "85.70",
            "C", "70.25", "95.60",
            "Sides", "", "",
            "Extra Rice", "20.00", "",
            "Salad", "60.00", "",
            "Soup", "55.00", "",
            "Dessert", "85.00", ""
    };

    MenuFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Ordertaker");
        setLayout(new GridLayout(4, 1));
        setSize(600, 600);
        setLocationRelativeTo(null);

        createHeader();
        add(header);
        createBody();
        add(body);
        createOrderFields();
        add(orders);
        createPaymentPanel();
        add(payment);
    }

    private void createPaymentPanel() {
        payment = new Panel(new GridLayout(2, 2));
        lastItemAdded = new Panel(new GridLayout(2, 1));
        displayLastAdded(); // the last item added, will be completely empty at first run
        payment.add(lastItemAdded);
        payment.add(new Panel()); // Filler

        // Lower Half of Payment Panel

        // The display for the net and gross cost
        Panel costs = new Panel(new GridLayout(2, 2));
        costs.add(new Label("Gross Cost:"));
        grossCost = new Label("0.00");
        costs.add(grossCost);
        costs.add(new Label("Net Cost:"));
        netCost = new Label("0.00");

        costs.add(netCost);
        payment.add(costs);

        // Confirms orders
        Panel finalChanges = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        submitButton = new Button("SUBMIT");
        undoButton = new Button("UNDO");

        finalChanges.add(submitButton);
        finalChanges.add(undoButton);
        payment.add(finalChanges);
    }

    private void createOrderFields() {
        orders = new Panel(new FlowLayout(FlowLayout.RIGHT, 0, 2));

        // Dropdown List for Meal Type
        orders.add(new Label("Meal Type:"));
        mealOptions = new JComboBox<>(MEAL_OPTIONS);
        mealOptions.setBorder(new EmptyBorder(0, 0, 0, /* right: */10));
        orders.add(mealOptions);

        // Field for Number of Meals
        orders.add(new Label("Amount:"));
        amountField = new TextField(2);
        orders.add(amountField);
    }

    private void createBody() {
        body = new Panel(new GridLayout(8, 1));
        Panel[] rows = new Panel[8];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = createRow(MENU[i * 3], MENU[i * 3 + 1], MENU[i * 3 + 2]);
        }

        addComponentsToPanel(body, rows);
    }

    private void createHeader() {
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

    private void addComponentsToPanel(Panel panel, Component[] components) {
        for (Component c : components)
            panel.add(c);
    }

    private Panel createRow(String c1, String c2, String c3) {
        Panel row = new Panel(new GridLayout(1, 3));
        row.add(new Label(c1, Label.CENTER));
        row.add(new Label(c2, Label.CENTER));
        row.add(new Label(c3, Label.CENTER));
        return row;
    }

    /**
     * Reads the order stack and adds it to the display
     * If the stack is empty, then the output should also be empty
     */
    private void displayLastAdded() {
        Panel orderHeader = createRow("Meal", "Size", "Amount");
        Panel lastAddedOrder;
        if (!order_stack.isEmpty()) {
            String[] lastOrder = order_stack.peek();
            lastAddedOrder = createRow(lastOrder[0], lastOrder[1], lastOrder[2]);
        } else {
            lastAddedOrder = createRow("-", "-", "-"); // Completely Empty
        }
        lastItemAdded.add(orderHeader);
        lastItemAdded.add(lastAddedOrder);
    }
}
