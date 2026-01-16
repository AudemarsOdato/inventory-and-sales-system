package pages;

import components.Button;
import components.Container;
import components.Input;
import components.Page;
import components.Text;
import database.Products;
import database.SalesHistory;
import database.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.Product;
import models.Sale;
import models.User;

public class POS extends Page {
        double grandTotal = 0;
        public POS(User user) {
                setTitle("Point-of-Sale - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new BorderLayout());

                Container headerContainer = new Container();
                headerContainer.setLayout(new BorderLayout(10, 10));
                headerContainer.setPreferredSize(new Dimension(798, 46));
                Button backButton = new Button("<--");
                backButton.addActionListener(e -> {
                        if (user.getRole().equals("owner")) {
                                new Owner(user);
                        }

                        if (user.getRole().equals("cashier")) {
                                new Cashier(user);
                        }
                        dispose();
                });
                headerContainer.add(backButton, BorderLayout.WEST);
                headerContainer.add(new Text("Point-of-Sale", Text.TEXT_SIZE), BorderLayout.CENTER);

                Container paymentPanel = new Container(true);
                paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

                paymentPanel.add(new Text("Payment Information", Text.TEXT_SIZE));

                paymentPanel.add(new Text("Total", Text.TEXT_SMALL));
                Input totalDisplay = new Input(false);
                paymentPanel.add(totalDisplay);

                
                paymentPanel.add(new Text("Cash Received", Text.TEXT_SMALL));
                Input cashReceivedInput = new Input();
                paymentPanel.add(cashReceivedInput);
                
                Button checkoutButton = new Button("CHECKOUT");
                paymentPanel.add(checkoutButton);

                Container itemsPanel = new Container(true);
                itemsPanel.setLayout(new BorderLayout());
                
                // be able to remove items from the items table
                String[] columns = { "ID", "Item", "Quantity", "Price", "Total" };
                DefaultTableModel model = new DefaultTableModel(columns, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false; // all cells uneditable
                        }
                };

                JTable itemsTable = new JTable(model);
                itemsTable.setRowHeight(24);
                itemsTable.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(itemsTable);
                itemsPanel.add(scrollPane, BorderLayout.CENTER);
                
                Container itemInfoPanel = new Container(true);
                itemInfoPanel.setLayout(new BoxLayout(itemInfoPanel, BoxLayout.Y_AXIS));
                
                itemInfoPanel.add(new Text("Item Information", Text.TEXT_SIZE));

                itemInfoPanel.add(new Text("Product ID", Text.TEXT_SMALL));
                Input productIdInput = new Input(); // editable
                itemInfoPanel.add(productIdInput);
                
                itemInfoPanel.add(new Text("Quantity", Text.TEXT_SMALL));
                Input quantityInput = new Input();
                itemInfoPanel.add(quantityInput);
                
                Button addButton = new Button("+ ADD");
                itemInfoPanel.add(addButton);

                Products products = new Products();
                SalesHistory sales = new SalesHistory();
                ArrayList<Item> items = new ArrayList<>();
                
                addButton.addActionListener(e -> {
                        Product product = products.getOne(Integer.parseInt(productIdInput.getText()));
                        if (product.getQuantity() < Integer.parseInt(quantityInput.getText())) {
                                JOptionPane.showMessageDialog(null, "Remaining stock: " + product.getQuantity());  
                                return;                           
                        }
                        if (!(product != null)) {
                                JOptionPane.showMessageDialog(null, "Invalid Id!");
                        }                     
                        Item item = new Item(product.getId(), Integer.parseInt(quantityInput.getText()), product.getPricing());
                        double total = item.getQuantity() * item.getUnitPrice();
                        grandTotal += total;
                        model.addRow(new Object[] {
                                item.getProductId(),
                                product.getName(),
                                item.getQuantity(),
                                item.getUnitPrice(),
                                total
                        });
                        items.add(item);
                        totalDisplay.setText(String.valueOf(grandTotal));
                        productIdInput.setText("");
                        quantityInput.setText("");
                });

                checkoutButton.addActionListener(e -> {
                        // if (Integer.parseInt(cashReceivedInput.getText()) < grandTotal) {
                        //         JOptionPane.showMessageDialog(null, "Bawal utang dito pre, namo ah");
                        //         return;
                        // }
                        // if (Integer.parseInt(cashReceivedInput.getText()) > grandTotal) {
                        //         JOptionPane.showMessageDialog(null, "Walang panukli yah");
                        //         return;
                        // }
                        int saleId = sales.insert(new Sale(user.getId(), items, grandTotal, Integer.parseInt(cashReceivedInput.getText())));
                        new Receipt(user, sales.getOne(saleId));
                        new POS(user);
                        new Users().incrementTotalSale(user.getId());
                        grandTotal = 0;
                        dispose();
                });

                fixAllInputSizes(
                        totalDisplay,
                        cashReceivedInput,
                        productIdInput,
                        quantityInput
                );

                add(headerContainer, BorderLayout.NORTH);
                add(paymentPanel, BorderLayout.WEST);
                add(itemsPanel, BorderLayout.CENTER);
                add(itemInfoPanel, BorderLayout.EAST);

                setLocationRelativeTo(null);
                setVisible(true); // must always be at the end
        }

        private void fixAllInputSizes(Input... inputs) {
                for (Input input : inputs) {
                        input.setMaximumSize(new Dimension(300, 30));
                }
        }
}