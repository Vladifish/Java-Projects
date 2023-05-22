// Vladimir Gray P. Velazco 1-CSC
// The awt library has a complicated way to make the border so I decided to not do it
package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class GUIMeal {
    public static void main(String[] args) {
        JFrame menu = new MenuFrame();
        menu.setVisible(true);
    }
}

class MenuFrame extends JFrame {
    private Panel header, body, orders, payment; // Main Panels
    private Panel lastItemAdded, orderHeader, lastAddedOrder; // Utility Panels
    private Button addButton, undoButton;
    private Label netCostLabel, grossCostLabel,
            orderType, orderSize, orderAmount, orderCost;

    TextField amountField;
    JComboBox<String> mealOptions, sizeOptions;
    Stack<String[]> order_stack = new Stack<>();
    HashMap<String, String[]> MENU_MAP = new HashMap<>();

    private double grossCost = 0, netCost = 0;
    private final double VAT = 0.08, DISCOUNT = 0.12;

    final static String[] MEAL_OPTIONS = {
            "Meal A", "Meal B", "Meal C",
            "Extra Rice", "Salad", "Soup", "Dessert"
    };

    final static String[] MENU = {
            "Meal A", "78.75", "102.50",
            "Meal B", "68.75", "85.70",
            "Meal C", "70.25", "95.60",
            "-----", "-----", "-----",
            "Sides", "(No sizes)", "",
            "Extra Rice", "20.00", "",
            "Salad", "60.00", "",
            "Soup", "55.00", "",
            "Dessert", "85.00", ""
    };

    final static String[] SIZES = {
            "R", "U"
    };

    private ActionListener addToOrder = (e) -> {
        int amt = getAmount();
        String size = getMealSize();
        String type = getMealType();

        if (!type.contains("Meal"))
            size = "-";
        if (amt > 0 && !size.equals("!") && !type.equals("!")) {
            // those are mostly dead values
            String outputStrings[] = new String[3];
            outputStrings[0] = type;
            outputStrings[1] = size;
            outputStrings[2] = amt + "";

            order_stack.push(outputStrings);
            displayLastAdded();

            grossCost += Double.parseDouble(orderCost.getText());
            grossCostLabel.setText(String.format("%.2f", grossCost));

            netCost = (grossCost > 2000) ? grossCost * (1 - DISCOUNT) * (1 + VAT) : grossCost * (1 + VAT);
            netCostLabel.setText(String.format("%.2f", netCost));
        }
    };

    private ActionListener undoOrder = (e) -> {
        if (order_stack.isEmpty())
            return;
        String[] removedOrder = order_stack.pop();

        grossCost -= Double.parseDouble(orderCost.getText());
        grossCostLabel.setText(String.format("%.2f", grossCost));

        netCost = (grossCost > 2000) ? grossCost * (1 - DISCOUNT) * (1 + VAT) : grossCost * (1 + VAT);
        netCostLabel.setText(String.format("%.2f", netCost));

        displayLastAdded();

    };

    MenuFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Ordertaker");
        setLayout(new GridLayout(4, 1));
        setSize(650, 650);
        setLocationRelativeTo(null);

        for (int i = 0; i < MENU.length / 3; i++) {
            if (i == 3 && i == 4)
                continue;

            String[] sizedPrices = new String[2];
            sizedPrices[0] = MENU[i * 3 + 1]; // either the regular price or the default
            if (i > 4) {
                sizedPrices[1] = MENU[i * 3 + 1];
            } else {
                sizedPrices[1] = MENU[i * 3 + 2];
            }
            MENU_MAP.put(MENU[i * 3], sizedPrices);
        }

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
        orderHeader = createRow("Meal", "Size", "Amount", "Cost");
        lastAddedOrder = new Panel(new GridLayout(1, 4));

        // These Labels would be filled up later on
        orderType = new Label("---", Label.CENTER);
        orderSize = new Label("---", Label.CENTER);
        orderAmount = new Label("---", Label.CENTER);
        orderCost = new Label("---", Label.CENTER);

        lastAddedOrder.add(orderType);
        lastAddedOrder.add(orderSize);
        lastAddedOrder.add(orderAmount);
        lastAddedOrder.add(orderCost);

        lastItemAdded.add(orderHeader);
        lastItemAdded.add(lastAddedOrder);
        displayLastAdded(); // the last item added, will be completely empty at first run
        payment.add(lastItemAdded);
        payment.add(new Panel()); // Filler

        // The display for the net and gross cost
        Panel costs = new Panel(new GridLayout(2, 2));
        costs.add(new Label("Gross Cost:", Label.CENTER));
        grossCostLabel = new Label("0.00");
        costs.add(grossCostLabel);
        costs.add(new Label("Net Cost:", Label.CENTER));
        netCostLabel = new Label("0.00");

        costs.add(netCostLabel);
        payment.add(costs);

        // Confirms orders
        Panel finalChanges = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        addButton = new Button("ADD");
        undoButton = new Button("UNDO");

        addButton.addActionListener(addToOrder);
        undoButton.addActionListener(undoOrder);

        finalChanges.add(addButton);
        finalChanges.add(undoButton);
        payment.add(finalChanges);
    }

    private void createOrderFields() {
        orders = new Panel(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        // Dropdown List for Meal Type
        orders.add(new Label("Meal Type:"));

        mealOptions = new JComboBox<>(MEAL_OPTIONS);
        mealOptions.setBorder(new EmptyBorder(0, 0, 0, /* right: */10));
        orders.add(mealOptions);

        // Field for Number of Meals
        orders.add(new Label("Amount:"));
        amountField = new TextField(2);
        orders.add(amountField);
        Panel fillerSpace = new Panel();
        fillerSpace.setSize(5, 0);
        orders.add(fillerSpace);

        // Fields for Meal Size, size shouldn't matter for sides
        orders.add(new Label("Size (Unnaplicable for Sides):"));
        sizeOptions = new JComboBox<>(SIZES);
        orders.add(sizeOptions);
    }

    private void createBody() {
        body = new Panel(new GridLayout((int) (MENU.length / 3), 1));
        Panel[] rows = new Panel[(int) (MENU.length / 3)];

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

    private Panel createRow(String c1, String c2, String c3, String c4) {
        Panel row = new Panel(new GridLayout(1, 4));
        row.add(new Label(c1, Label.CENTER));
        row.add(new Label(c2, Label.CENTER));
        row.add(new Label(c3, Label.CENTER));
        row.add(new Label(c4, Label.CENTER));
        return row;
    }

    /**
     * Reads the order stack and adds it to the display
     * If the stack is empty, then the output should also be empty
     */
    private void displayLastAdded() {
        if (!order_stack.isEmpty()) {
            String[] lastOrder = order_stack.peek();
            orderType.setText(lastOrder[0]);
            orderSize.setText(lastOrder[1]);
            orderAmount.setText(lastOrder[2]);
            orderCost.setText(computeCost(lastOrder[0], lastOrder[1], lastOrder[2]) + "");
        } else {
            orderType.setText("---");
            orderSize.setText("---");
            orderAmount.setText("---");
            orderCost.setText("---");
            // Completely Empty
        }
    }

    private double computeCost(String type, String size, String amount) {
        double computedCost = 0;
        String[] sizedPrices = MENU_MAP.get(type);

        int i = 0;
        if (size.equals("L")) {
            i = 1;
        }

        computedCost = Integer.parseInt(amount) * Double.parseDouble(sizedPrices[i]);
        return computedCost;
    }

    public int getAmount() {
        int amount;
        try {
            amount = Integer.parseInt(amountField.getText());
            if (amount < 0) {
                JOptionPane.showMessageDialog(null, "Amount must be a positive integer", "ERROR",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (NullPointerException e) {
            amount = -1;
            JOptionPane.showMessageDialog(null, "Amount Field is Blank", "ERROR", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            amount = -1;
            JOptionPane.showMessageDialog(null, "Amount must be an integer", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return amount;
    }

    public String getMealType() {
        String mealType;
        try {
            mealType = (String) mealOptions.getSelectedItem();
        } catch (NullPointerException e) {
            mealType = "!";
        }
        return mealType;
    }

    public String getMealSize() {
        String mealSize;
        try {
            mealSize = (String) sizeOptions.getSelectedItem();
        } catch (NullPointerException e) {
            mealSize = "!";
        }
        return mealSize;
    }

}
