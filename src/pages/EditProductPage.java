package pages;

import database.Products;
import java.awt.*;
import javax.swing.*;
import models.User;

public class EditProductPage extends JFrame {

    private JTextField txtProductName;
    private JTextField txtPricing;

    private JButton btnDelete;
    private JButton btnSave;

    public EditProductPage(Inventory inventory, User user, String productName, int productId, double price) {
        setTitle("Edit product details - POS AND INVENTORY MANAGEMENT SYSTEM");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblProductName = new JLabel("Product name:");
        JLabel lblPricing = new JLabel("Pricing:");

        txtProductName = new JTextField(20);
        txtPricing = new JTextField(20);

        btnDelete = new JButton("DELETE PRODUCT");
        btnSave = new JButton("SAVE CHANGES");

        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblProductName, gbc);

        gbc.gridx = 1;
        panel.add(txtProductName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPricing, gbc);

        gbc.gridx = 1;
        panel.add(txtPricing, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(btnDelete, gbc);

        gbc.gridx = 1;
        panel.add(btnSave, gbc);

        txtProductName.setText(productName);
        txtPricing.setText(String.valueOf(price));

        Products products = new Products();
        btnDelete.addActionListener(e -> {
                products.deleteOne(productId);
                JOptionPane.showMessageDialog(null, "Deleted product: " + productName);
                inventory.closeCurrent();
                new Inventory(user);
                dispose();
        });
        
        btnSave.addActionListener(e -> {
                products.updateOne(productId, txtProductName.getText(), "n/a", Double.parseDouble(txtPricing.getText()));
                JOptionPane.showMessageDialog(null, "Updated product: " + productName);
                inventory.dispose();
                new Inventory(user);
                dispose();
        });

        add(panel);

        setVisible(true);
    }
}