package pages;

import controllers.NewProductController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import models.Product;
import models.Response;
import models.User;

public class NewProductPage { 

            JFrame frame = new JFrame();
            JButton cancelButton = new JButton("CANCEL");
            JButton addProductButton = new JButton("+ ADD PRODUCT");
            JLabel label = new JLabel("Product Name:");
        //     JLabel label2 = new JLabel("Image Path:");
            JLabel label3 = new JLabel("Quantity:");
            JLabel label4 = new JLabel("Pricing:");
            JTextField textField = new JTextField();
        //     JTextField textField2 = new JTextField();
            JTextField textField3 = new JTextField();
            JTextField textField4 = new JTextField();

            public NewProductPage(User user) {
                cancelButton.setBounds(345, 310, 150, 40);
                cancelButton.setFocusable(false);
                addProductButton.setBounds(500, 310, 150, 40);
                addProductButton.setFocusable(false);
                frame.add(cancelButton);
                frame.add(addProductButton);
                frame.add(label);
                // frame.add(label2);
                frame.add(label3);
                frame.add(label4);
                frame.add(textField);
                // frame.add(textField2);
                frame.add(textField3);
                frame.add(textField4);
                label.setBounds(100, 100, 200, 40);
                // label2.setBounds(100, 150, 200, 40);
                label3.setBounds(100, 150, 200, 40);
                label4.setBounds(100,200, 200,40);
                // label4.setBounds(100, 250, 200, 40);
                textField.setBounds(450, 110, 200, 20);
                // textField2.setBounds(450, 160, 200, 20);
                textField3.setBounds(450, 160, 200, 20);
                // textField2.setEditable(false);
                textField4.setBounds(450, 210, 200, 20);
                // textField4.setBounds(450, 260, 200, 20);

                addProductButton.addActionListener(e -> {
                        Response response = new NewProductController().addProduct(new Product(textField.getText(),  Integer.parseInt(textField3.getText()), Double.parseDouble(textField4.getText())));
                        if (!response.ok()) {
                                JOptionPane.showMessageDialog(null, response.getMessage());
                                return;
                        }
                        new Inventory(user);
                        frame.dispose();
                });
                cancelButton.addActionListener(e -> {
                        new Inventory(user);
                        frame.dispose();
                });
                
                frame.setTitle("Create product - POS AND INVENTORY MANAGEMENT SYSTEM");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(820, 620);
                frame.setLayout(null);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
}