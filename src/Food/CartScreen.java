package Food;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CartScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<explor.FoodItem> cartItems;
    private explor parentFrame;
    private static final int MAX_QUANTITY_PER_ITEM = 10; // Maximum allowed quantity per item

    public CartScreen(List<explor.FoodItem> cartItems, explor parentFrame) {
        this.cartItems = cartItems;
        this.parentFrame = parentFrame;
        setTitle("Shopping Cart");
        setSize(719, 606);
        setLocationRelativeTo(parentFrame);

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        updateCartDisplay(cartPanel); // Initialize the cart display

        JScrollPane scrollPane = new JScrollPane(cartPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Checkout Button
        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.setFont(new Font("Arial", Font.BOLD, 14));
        btnCheckout.setBackground(new Color(50, 205, 50));
        btnCheckout.setForeground(Color.BLACK);
        btnCheckout.addActionListener(e -> {
            double total = calculateTotal();
            CheckoutScreen checkoutScreen = new CheckoutScreen(cartItems, total);
            checkoutScreen.setVisible(true);
            dispose(); // Close the current window
        });
        btnCheckout.setPreferredSize(new Dimension(200, 40));

        // Menu Button
        JButton btnMenu = new JButton("Menu");
        btnMenu.setForeground(Color.BLACK);
        btnMenu.setBackground(new Color(255, 128, 128));
        btnMenu.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose(); // Close the current window
        });

        // Home Button
        JButton btnHome = new JButton("Home");
        btnHome.setBackground(new Color(255, 128, 128));
        btnHome.addActionListener(e -> {
            restT2 frame = new restT2();
            frame.setVisible(true);
            dispose(); // Close the current window
        });

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(btnMenu);
        buttonPanel.add(btnHome);
        buttonPanel.add(btnCheckout);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    // Update the cart display
    private void updateCartDisplay(JPanel cartPanel) {
        cartPanel.removeAll(); // Clear the panel
        double total = 0.0;

        for (explor.FoodItem item : cartItems) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            itemPanel.setBackground(Color.WHITE);

            // Item Name and Description
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);

            JLabel lblItemName = new JLabel(item.getName());
            lblItemName.setFont(new Font("Arial", Font.BOLD, 14));
            infoPanel.add(lblItemName);

            JLabel lblItemDescription = new JLabel(item.getDescription());
            lblItemDescription.setFont(new Font("Arial", Font.PLAIN, 12));
            lblItemDescription.setForeground(new Color(100, 100, 100));
            infoPanel.add(lblItemDescription);

            itemPanel.add(infoPanel, BorderLayout.CENTER);

            // Quantity Controls
            JPanel quantityPanel = new JPanel();
            quantityPanel.setBackground(Color.WHITE);

            JButton btnDecrease = new JButton("-");
            btnDecrease.setFont(new Font("Arial", Font.BOLD, 14));
            btnDecrease.setBackground(new Color(250, 0, 0));
            btnDecrease.setForeground(Color.WHITE);
            btnDecrease.addActionListener(e -> {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    item.setStock(item.getStock() + 1);
                } else {
                    cartItems.remove(item); // Remove item if quantity is 0
                    item.setStock(item.getStock() + 1); // Restore stock
                }
                updateCartDisplay(cartPanel);
            });
            quantityPanel.add(btnDecrease);

            JLabel lblQuantity = new JLabel(String.valueOf(item.getQuantity()));
            lblQuantity.setFont(new Font("Arial", Font.BOLD, 14));
            quantityPanel.add(lblQuantity);

            JButton btnIncrease = new JButton("+");
            btnIncrease.setFont(new Font("Arial", Font.BOLD, 14));
            btnIncrease.setBackground(new Color(250,0, 0));
            btnIncrease.setForeground(Color.WHITE);
            btnIncrease.addActionListener(e -> {
                if (item.getQuantity() >= MAX_QUANTITY_PER_ITEM) {
                    JOptionPane.showMessageDialog(this,
                            "Maximum quantity reached for " + item.getName() + " (Max: " + MAX_QUANTITY_PER_ITEM + ")",
                            "Quantity Limit",
                            JOptionPane.WARNING_MESSAGE);
                } else if (item.getStock() > 0) {
                    item.setQuantity(item.getQuantity() + 1);
                    item.setStock(item.getStock() - 1);
                    updateCartDisplay(cartPanel);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Out of stock: " + item.getName(),
                            "Stock Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            quantityPanel.add(btnIncrease);

            itemPanel.add(quantityPanel, BorderLayout.EAST);

            // Subtotal
            JLabel lblSubtotal = new JLabel("Subtotal: £" + String.format("%.2f", item.getTotalPrice()));
            lblSubtotal.setFont(new Font("Arial", Font.BOLD, 12));
            lblSubtotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            itemPanel.add(lblSubtotal, BorderLayout.SOUTH);

            cartPanel.add(itemPanel);
            cartPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between items
            total += item.getTotalPrice();
        }

        // Total Price
        JPanel totalPanel = new JPanel();
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        totalPanel.setBackground(Color.WHITE);

        JLabel lblTotal = new JLabel("Total: £" + String.format("%.2f", total));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(lblTotal);

        cartPanel.add(totalPanel);

        // Refresh the panel
        cartPanel.revalidate();
        cartPanel.repaint();

        // Update the cart count on the parent frame
        parentFrame.updateCartCount();
    }

    // Calculate the total price of the cart
    private double calculateTotal() {
        double total = 0.0;
        for (explor.FoodItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}