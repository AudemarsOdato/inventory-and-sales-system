package pages;

import components.Button;
import components.Container;
import components.Input;
import components.Page;
import components.Text;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class POS extends Page {
        public POS() {
                setTitle("Point-of-Sale - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new BorderLayout());

                // PAYMENT PANEL (LEFT)
                Container paymentPanel = new Container(true);
                paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

                paymentPanel.add(new Text("Payment Information", Text.TEXT_SIZE));

                paymentPanel.add(new Text("Total", Text.TEXT_SMALL));
                Input totalDisplay = new Input(false);
                paymentPanel.add(totalDisplay);

                paymentPanel.add(new Text("Cash Received", Text.TEXT_SMALL));
                Input cashReceivedInput = new Input();
                paymentPanel.add(cashReceivedInput);

                paymentPanel.add(new Text("Change", Text.TEXT_SMALL));
                Input changeDisplay = new Input(false);
                paymentPanel.add(changeDisplay);

                Button checkoutButton = new Button("CHECKOUT");
                paymentPanel.add(checkoutButton);

                // ITEMS PANEL (CENTER)
                Container itemsPanel = new Container(true);
                itemsPanel.setLayout(new BorderLayout());

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
                model.addRow(new Object[] {
                        "productId",
                        "itemName",
                        "quantity",
                        "price",
                        2*2
                });
                itemsPanel.add(scrollPane, BorderLayout.CENTER);

                // ITEM INFO PANEL (RIGHT)
                Container itemInfoPanel = new Container(true);
                itemInfoPanel.setLayout(new BoxLayout(itemInfoPanel, BoxLayout.Y_AXIS));

                itemInfoPanel.add(new Text("Item Information", Text.TEXT_SIZE));

                itemInfoPanel.add(new Text("Product ID", Text.TEXT_SMALL));
                Input productIdInput = new Input(); // editable
                itemInfoPanel.add(productIdInput);

                itemInfoPanel.add(new Text("Quantity", Text.TEXT_SMALL));
                Input quantityInput = new Input();
                itemInfoPanel.add(quantityInput);

                itemInfoPanel.add(new Text("Price", Text.TEXT_SMALL));
                Input priceInput = new Input(false);
                itemInfoPanel.add(priceInput);

                Button addButton = new Button("+ ADD");
                itemInfoPanel.add(addButton);

                fixAllInputSizes(
                        totalDisplay,
                        cashReceivedInput,
                        changeDisplay,
                        productIdInput,
                        quantityInput,
                        priceInput
                );

                // ADD PANELS TO PAGE
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