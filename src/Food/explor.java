package Food;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Food.explor.FoodItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class explor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Inner class to represent a food item
    public static class FoodItem {
        private String name;
        private double price;
        private int quantity; // Quantity in cart
        private int stock;    // Available stock
        private LocalDate expiryDate; // Expiry date of the item
        private Timer restockTimer; // Timer for restocking
        private JLabel lblRestockTime; // Label to show restock time
        private String description; // Description of the food item

        public FoodItem(String name, double price, int stock, JLabel lblRestockTime, String description) {
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.quantity = 0; // Initially, no items are in the cart
            this.lblRestockTime = lblRestockTime;
            this.expiryDate = LocalDate.now().plus(3, ChronoUnit.DAYS); // Set expiry to 3 days from today
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public LocalDate getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getDescription() {
            return description;
        }

        public double getTotalPrice() {
            return price * quantity;
        }

        // Check if the item has expired
        public boolean isExpired() {
            return LocalDate.now().isAfter(expiryDate);
        }

        // Start the restock timer
        public void startRestockTimer(JButton btnAddToCart) {
            lblRestockTime.setText("Restocking in: 00:03:00");
            restockTimer = new Timer(1000, e -> {
                String timeText = lblRestockTime.getText();
                if (timeText.equals("Restocking in: 0:00:00")) {
                    // Restock the item
                    stock = 10; // Replenish stock to 10 (or any desired value)
                    expiryDate = LocalDate.now().plus(3, ChronoUnit.DAYS); // Reset expiry date
                    lblRestockTime.setText("In Stock");
                    btnAddToCart.setEnabled(true); // Re-enable the button
                    ((Timer) e.getSource()).stop(); // Stop the timer
                } else {
                    // Update the timer
                    String[] parts = timeText.split(":");
                    int hours = Integer.parseInt(parts[1].trim());
                    int minutes = Integer.parseInt(parts[2].trim());
                    int seconds = Integer.parseInt(parts[3].trim());

                    if (seconds > 0) {
                        seconds--;
                    } else {
                        if (minutes > 0) {
                            minutes--;
                            seconds = 59;
                        } else {
                            if (hours > 0) {
                                hours--;
                                minutes = 59;
                                seconds = 59;
                            }
                        }
                    }
                    lblRestockTime.setText(String.format("Restocking in: %d:%02d:%02d", hours, minutes, seconds));
                }
            });
            restockTimer.start();
        }
    }

    // List to store cart items
    private List<FoodItem> cartItems = new ArrayList<>();
    private JButton cart;

    public explor() {
        setTitle("Explore Restaurants");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1341, 649);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title Label
        JLabel lblTitle = new JLabel(" ", JLabel.CENTER);
        lblTitle.setBounds(-60, 0, 1469, 88);
        lblTitle.setIcon(new ImageIcon("src\\Food\\head.png"));
        lblTitle.setFont(new Font("Serif", Font.BOLD, 26));
        contentPane.add(lblTitle);

        // Cart Button
        cart = new JButton("View Cart (0)");
        cart.setBounds(1111, 587, 168, 25);
        cart.addActionListener(e -> showCartScreen());
        contentPane.add(cart);

        // Define fixed positions for 12 items
        int[][] positions = {
            {10, 98}, {237, 98}, {467, 98}, {703, 98}, {932, 98}, {1160, 98},
            {10, 340}, {237, 340}, {467, 340}, {703, 340}, {932, 340}, {1160, 340}
        };

        // Read food items from CSV
        List<FoodItemData> foodItems = readFoodItemsFromCSV("food_items.csv");

        // Shuffle the list
        Collections.shuffle(foodItems);

        // Add food item panels
        for (int i = 0; i < positions.length; i++) {
            FoodItemData item = foodItems.get(i);
            int[] pos = positions[i];
            addFoodItemPanel(item.name, item.price, item.stock, item.imagePath, item.description, pos[0], pos[1]);
        }

        JButton back_button = new JButton("Back");
        back_button.addActionListener(e -> {
            restT2 frame = new restT2();
            frame.setVisible(true);
            dispose(); // Close the current window
        });
        back_button.setBounds(0, 33, 75, 21);
        contentPane.add(back_button);

        // Timer to check for expired items
        Timer expiryTimer = new Timer(60000, e -> checkForExpiredItems()); // Check every minute
        expiryTimer.start();
    }

    // Helper method to check for expired items
    private void checkForExpiredItems() {
        for (Component comp : contentPane.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component innerComp : panel.getComponents()) {
                    if (innerComp instanceof JLabel && ((JLabel) innerComp).getText().startsWith("Expiry:")) {
                        JLabel lblExpiry = (JLabel) innerComp;
                        String expiryText = lblExpiry.getText();
                        LocalDate expiryDate = LocalDate.parse(expiryText.split(": ")[1]);
                        if (LocalDate.now().isAfter(expiryDate)) {
                            // Item has expired, restock it
                            lblExpiry.setText("Expiry: " + LocalDate.now().plus(3, ChronoUnit.DAYS)); // Reset expiry date
                            for (FoodItem item : cartItems) {
                                if (item.getExpiryDate().equals(expiryDate)) {
                                    item.setStock(100); // Restock to 100
                                    item.setExpiryDate(LocalDate.now().plus(3, ChronoUnit.DAYS)); // Reset expiry date
                                    break;
                                }
                            }
                        }
                    }
                }
                       }
                                  }
                                     }

    // Helper method to read food items from CSV
    private List<FoodItemData> readFoodItemsFromCSV(String filePath) {
        List<FoodItemData> foodItems = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the header line
                }
                String[] values = line.split(",");
                String name = values[0];
                double price = Double.parseDouble(values[1]);
                int stock = Integer.parseInt(values[2]);
                String imagePath = values[3];
                String description = values[4]; // Read description
                foodItems.add(new FoodItemData(name, price, stock, imagePath, description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    // Helper method to create food item panels
    private void addFoodItemPanel(String name, double price, int stock, String imagePath, String description, int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, 161, 227);
        contentPane.add(panel);

        // Food Image
        JLabel lblImage = new JLabel("New label");
        lblImage.setIcon(new ImageIcon(imagePath));
        lblImage.setBounds(10, 0, 141, 148);
        panel.add(lblImage);

        // Food Name
        JLabel lblName = new JLabel(name);
        lblName.setBounds(10, 158, 141, 13);
        panel.add(lblName);

        // Stock Label
        JLabel lblStock = new JLabel("Stock: " + stock);
        lblStock.setBounds(10, 175, 141, 13);
        panel.add(lblStock);

        // Expiry Date Label
        JLabel lblExpiry = new JLabel("Expiry: " + LocalDate.now().plus(3, ChronoUnit.DAYS));
        lblExpiry.setBounds(10, 190, 141, 13);
        panel.add(lblExpiry);

        // Add to Cart Button
        JButton btnAddToCart = new JButton(String.format("£%.2f   Add to cart", price));
        btnAddToCart.setBounds(10, 205, 141, 21);
        btnAddToCart.addActionListener(e -> {
            FoodItem item = new FoodItem(name, price, stock, lblExpiry, description);
            addToCart(item, lblStock, btnAddToCart);
        });
        panel.add(btnAddToCart);
        }

    // Add item to cart
    private void addToCart(FoodItem newItem, JLabel lblStock, JButton btnAddToCart) {
        // Check if the item already exists in the cart
        for (FoodItem item : cartItems) {
            if (item.getName().equals(newItem.getName())) {
                // Check if the order limit (10) is reached
                if (item.getQuantity() >= 10) {
                    JOptionPane.showMessageDialog(this,
                            "Order limit reached for " + item.getName() + " (max 10 per order)",
                            "Order Limit",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Check if there is enough stock
                if (item.getStock() > 0) {
                    item.setQuantity(item.getQuantity() + 1); // Increase quantity
                    item.setStock(item.getStock() - 1);       // Decrease stock
                    lblStock.setText("Stock: " + item.getStock()); // Update stock label
                    updateCartCount();
                    JOptionPane.showMessageDialog(this,
                            "Added " + newItem.getName() + " to cart",
                            "Item Added",
                            JOptionPane.INFORMATION_MESSAGE);
                    if (item.getStock() == 0) {
                        btnAddToCart.setEnabled(false); // Disable button if stock is 0
                        item.startRestockTimer(btnAddToCart); // Start restock timer
                    }
                    return;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Out of stock: " + item.getName(),
                            "Stock Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        // If the item is not in the cart, add it
        if (newItem.getStock() > 0) {
            newItem.setQuantity(1); // Set initial quantity
            newItem.setStock(newItem.getStock() - 1); // Decrease stock
            cartItems.add(newItem);
            lblStock.setText("Stock: " + newItem.getStock()); // Update stock label
            updateCartCount();
            JOptionPane.showMessageDialog(this,
                    "Added " + newItem.getName() + " to cart",
                    "Item Added",
                    JOptionPane.INFORMATION_MESSAGE);
            if (newItem.getStock() == 0) {
                btnAddToCart.setEnabled(false); // Disable button if stock is 0
                newItem.startRestockTimer(btnAddToCart); // Start restock timer
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Out of stock: " + newItem.getName(),
                    "Stock Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update cart button text
    void updateCartCount() {
        int totalItems = 0;
        for (FoodItem item : cartItems) {
            totalItems += item.getQuantity();
        }
        cart.setText("View Cart (" + totalItems + ")");
    }

    // Show cart on the second screen
    private void showCartScreen() {
        CartScreen cartScreen = new CartScreen(cartItems, this);
        cartScreen.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                explor frame = new explor();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

// Helper class to hold food item data
class FoodItemData {
    String name;
    double price;
    int stock;
    String imagePath;
    String description;

    public FoodItemData(String name, double price, int stock, String imagePath, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        this.description = description;
    }
}

// Cart Screen to display items in the cart
class CartScreen extends JFrame {
    private JPanel contentPane;

    public CartScreen(List<explor.FoodItem> cartItems, explor parentFrame) {
        setTitle("Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Create a panel to display cart items
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (explor.FoodItem item : cartItems) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            // Display item details
            JLabel lblItem = new JLabel("<html><b>" + item.getName() + "</b><br>" +
                    "Price: £" + String.format("%.2f", item.getPrice()) + "<br>" +
                    "Quantity: " + item.getQuantity() + "<br>" 
                   // "Description: " + item.getDescription() + "</html>"
                    );
            itemPanel.add(lblItem);

            cartPanel.add(itemPanel);
        }

        // Add a scroll pane in case there are many items
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Add a close button
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        contentPane.add(btnClose, BorderLayout.SOUTH);
    }
}