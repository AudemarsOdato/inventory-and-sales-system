package pages;

import database.Products;
import javax.swing.*;
import models.Item;
import models.Product;
import models.User;

public class RestockPage extends JFrame {
        
        public RestockPage(Inventory inventory, User user, String productName, int productId) {
                
                setTitle("Update Inventory - POS AND INVENTORY MANAGEMENT SYSTEM");
                setSize(500, 350);
                setLayout(null);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                Products products = new Products();
                Product product = products.getOne(productId);

                JLabel label = new JLabel("Product name:");
                label.setBounds(50, 40, 120, 30);
                add(label);

                JLabel label2 = new JLabel(product.getName());
                label2.setBounds(180, 40, 200, 30);
                add(label2);

                JLabel label3 = new JLabel("Last stock up:");
                label3.setBounds(50, 80, 120, 30);
                add(label3);

                JLabel label4 = new JLabel(String.valueOf(product.getLastStockup()));
                label4.setBounds(180, 80, 200, 30);
                add(label4);

                JLabel label5 = new JLabel("Current quantity:");
                label5.setBounds(50, 120, 120, 30);
                add(label5);

                JLabel label6 = new JLabel(String.valueOf(product.getQuantity()));
                label6.setBounds(180, 120, 200, 30);
                add(label6);

                JLabel label7 = new JLabel("Stock up quantity:");
                label7.setBounds(50, 160, 120, 30);
                add(label7);

                JTextField textField = new JTextField();
                textField.setBounds(180, 160, 200, 30);
                add(textField);

                JButton button = new JButton("SAVE");
                button.setBounds(180, 210, 100, 35);
                add(button);

                button.addActionListener(e -> {
                        int newQuantity = products.updateRestocks(new Item(productId, Integer.parseInt(textField.getText())));
                        System.out.println("new: " + newQuantity);

                        JOptionPane.showMessageDialog(null, "Updated inventory stock: " + productName);
                        inventory.closeCurrent();
                        new Inventory(user);
                        dispose();

                });

                setVisible(true);
        }
}