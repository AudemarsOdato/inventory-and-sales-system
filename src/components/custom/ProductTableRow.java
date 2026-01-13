package components.custom;

import components.Button;
import components.Container;
import components.Text;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import models.User;
import pages.EditProductPage; // refactor
import pages.Inventory;
import pages.RestockPage;

public class ProductTableRow extends Container {
        public ProductTableRow(Inventory inventory, User user, String productName, int productId, int inStock, LocalDate lastRestock, double price) {
                setLayout(new GridLayout(1, 7));
                addBorder();
                setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                setAlignmentX(CENTER_ALIGNMENT);
                add(new Text(String.valueOf(productId), Text.TEXT_SMALL));
                add(new Text(productName, Text.TEXT_SMALL));
                add(new Text(String.valueOf(inStock), Text.TEXT_SMALL));
                add(new Text(String.valueOf(price), Text.TEXT_SMALL));
                add(new Text(lastRestock.toString(), Text.TEXT_SMALL));
                
                Button editProductButton = new Button("EDIT");
                editProductButton.addActionListener(e -> {
                        new EditProductPage(inventory, user, productName, productId, price);
                });
                Button restockButton = new Button("RE-STOCK");
                restockButton.addActionListener(e -> {
                        new RestockPage(inventory, user, productName, productId);
                });
                add(editProductButton);
                add(restockButton);
        }
        
        public ProductTableRow() {
                setLayout(new GridLayout(1, 7));
                addBorder();
                add(new Text("id", Text.TEXT_SMALL));
                add(new Text("name", Text.TEXT_SMALL));
                add(new Text("in-stock", Text.TEXT_SMALL));
                add(new Text("pricing", Text.TEXT_SMALL));
                add(new Text("lastRestock", Text.TEXT_SMALL));
                add(new Text("", Text.TEXT_SMALL));
                add(new Text("", Text.TEXT_SMALL));
        }
}