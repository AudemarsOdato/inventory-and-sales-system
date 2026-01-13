package components.custom;

import components.Button;
import components.Container;
import components.Text;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate; // refactor

public class ProductTableRow extends Container {
        public ProductTableRow(String productName, int productId, int inStock, LocalDate lastRestock, double price) {
                setLayout(new GridLayout(1, 7));
                addBorder();
                setMaximumSize(new Dimension(798, 50));
                add(new Text(String.valueOf(productId), Text.TEXT_SMALL));
                add(new Text(productName, Text.TEXT_SMALL));
                add(new Text(String.valueOf(inStock), Text.TEXT_SMALL));
                add(new Text(String.valueOf(price), Text.TEXT_SMALL));
                add(new Text(lastRestock.toString(), Text.TEXT_SMALL));

                Button editProductButton = new Button("EDIT");
                Button restockButton = new Button("RE-STOCK");
                add(editProductButton);
                add(restockButton);
        }
}