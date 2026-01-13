package pages;

import components.Button;
import components.Container;
import components.Page;
import components.Text;
import components.custom.ProductTableRow;
import database.Products;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import models.Product;
import models.User;

public class Inventory extends Page{
        public Inventory(User user) {
                setTitle("Inventory Dashboard - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new GridBagLayout());

                Container mainContainer = new Container();
                mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                mainContainer.setPreferredSize(new Dimension(900, 600));
                
                Container headerContainer = new Container();
                headerContainer.setLayout(new BorderLayout(10, 10));
                headerContainer.setPreferredSize(new Dimension(798, 46));
                Button backButton = new Button("<--");
                backButton.addActionListener(e -> {
                        new Owner(user);
                        dispose();
                });
                headerContainer.add(backButton, BorderLayout.WEST);
                headerContainer.add(new Text("INVENTORY AND PRODUCTS", Text.TEXT_SIZE), BorderLayout.CENTER);
                Button addNewProductButton = new Button("+ ADD NEW PRODUCT");
                addNewProductButton.addActionListener(e -> {
                        new NewProductPage(user);
                        dispose();
                });
                headerContainer.add(addNewProductButton, BorderLayout.EAST);
                
                Container table = new Container();
                table.setLayout(new BorderLayout());
                Container tableHeader = new Container();
                tableHeader.setLayout(new GridLayout(1, 7));
                tableHeader.setPreferredSize(new Dimension(790, 40));
                tableHeader.add(new Text("id", Text.TEXT_SMALL));
                tableHeader.add(new Text("name", Text.TEXT_SMALL));
                tableHeader.add(new Text("in-stock", Text.TEXT_SMALL));
                tableHeader.add(new Text("pricing", Text.TEXT_SMALL));
                tableHeader.add(new Text("last restock", Text.TEXT_SMALL));
                tableHeader.add(new Text("", Text.TEXT_SMALL));
                tableHeader.add(new Text("", Text.TEXT_SMALL));
                Container tableBody = new Container();
                tableBody.setLayout(new BoxLayout(tableBody, BoxLayout.Y_AXIS));
                JScrollPane scrollPane = new JScrollPane(tableBody);
                scrollPane.setPreferredSize(new Dimension(790, 400));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                Products products = new Products();
                for (Product product : products.getAll()) {
                        tableBody.add(new ProductTableRow(product.getName(), product.getId(), product.getQuantity(), product.getLastStockup(), product.getPricing()));
                }
                
                table.add(tableHeader, BorderLayout.NORTH);
                // table.add(tableBody, BorderLayout.CENTER);
                table.add(scrollPane, BorderLayout.CENTER);

                // add header to main container
                mainContainer.add(headerContainer);
                mainContainer.add(table);
                add(mainContainer);

                pack();
                setLocationRelativeTo(null);
                setVisible(true);
        }
}