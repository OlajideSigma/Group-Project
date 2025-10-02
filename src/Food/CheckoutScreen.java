package Food;

import java.awt.*;
import javax.swing.*;
import java.util.List;

public class CheckoutScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<explor.FoodItem> cartItems;
    private double total;

    public CheckoutScreen(List<explor.FoodItem> cartItems, double total) {
        this.cartItems = cartItems;
        this.total = total;
        setTitle("Checkout");
        setSize(719, 606);
        setLocationRelativeTo(null);

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new BoxLayout(checkoutPanel, BoxLayout.Y_AXIS));
        checkoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

            // Quantity
            JLabel lblQuantity = new JLabel("Quantity: " + item.getQuantity());
            lblQuantity.setFont(new Font("Arial", Font.BOLD, 12));
            itemPanel.add(lblQuantity, BorderLayout.EAST);

            // Subtotal
            JLabel lblSubtotal = new JLabel("Subtotal: £" + String.format("%.2f", item.getTotalPrice()));
            lblSubtotal.setFont(new Font("Arial", Font.BOLD, 12));
            lblSubtotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            itemPanel.add(lblSubtotal, BorderLayout.SOUTH);

            checkoutPanel.add(itemPanel);
            checkoutPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between items
        }

        // Total Price
        JPanel totalPanel = new JPanel();
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        totalPanel.setBackground(Color.WHITE);

        JLabel lblTotal = new JLabel("Total: £" + String.format("%.2f", total));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(lblTotal);

        checkoutPanel.add(totalPanel);

        JScrollPane scrollPane = new JScrollPane(checkoutPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Confirm Purchase Button
        JButton btnConfirmPurchase = new JButton("Confirm Purchase");
        btnConfirmPurchase.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmPurchase.setBackground(new Color(50, 205, 50));
        btnConfirmPurchase.setForeground(Color.BLACK);
        btnConfirmPurchase.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Purchase Confirmed! Thank you for your order.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        btnConfirmPurchase.setPreferredSize(new Dimension(200, 40));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(btnConfirmPurchase);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }
}