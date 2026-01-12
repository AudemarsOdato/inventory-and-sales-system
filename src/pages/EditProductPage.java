package pages;

import java.awt.*;
import javax.swing.*;

public class EditProductPage extends JFrame {

    private JTextField txtProductName;
    private JTextField txtImagePath;
    private JTextField txtPricing;

    private JButton btnDelete;
    private JButton btnSave;

    public EditProductPage() {
        setTitle("Edit product details - POS AND INVENTORY MANAGEMENT SYSTEM");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblProductName = new JLabel("Product name:");
        JLabel lblImagePath = new JLabel("Image path:");
        JLabel lblPricing = new JLabel("Pricing:");

        txtProductName = new JTextField(20);
        txtImagePath = new JTextField(20);
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

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblImagePath, gbc);

        gbc.gridx = 1;
        panel.add(txtImagePath, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPricing, gbc);

        gbc.gridx = 1;
        panel.add(txtPricing, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(btnDelete, gbc);

        gbc.gridx = 1;
        panel.add(btnSave, gbc);

        add(panel);

        setVisible(true);
    }
}