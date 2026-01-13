package pages;

import components.Container;
import components.Page;
import components.Text;
import components.custom.ReceiptItem;
import database.Config;
import database.Products;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import models.Item;
import models.Sale;
import models.User;
import utilities.FormattedDateTime;

public class Receipt extends Page{
        public Receipt(User user, Sale sale) {
                setTitle("Receipt");
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setLayout(new BorderLayout());


                Container headerContainer = new Container(20, 20);
                headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.Y_AXIS));

                headerContainer.add(new Text(new Config().getStoreTitle(), Text.TEXT_SIZE, "center"));
                headerContainer.add(new Text("Cashier: " + user.getName(), Text.TEXT_SIZE, "center"));
                headerContainer.add(new Text(new FormattedDateTime().getDateTime(), Text.TEXT_SIZE, "center"));
                headerContainer.add(new Text("", Text.TEXT_SIZE, "center"));

                Container itemsContainer = new Container(20, 20);
                itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));

                Products products = new Products();
                for (Item item : sale.getItems()) {
                        itemsContainer.add(
                                new ReceiptItem(
                                        products.getOne(item.getProductId()).getName(),
                                        item.getQuantity(),
                                        item.getUnitPrice()
                                )
                        );
                }

                Container paymentContainer = new Container(20, 20);
                paymentContainer.setLayout(new BoxLayout(paymentContainer, BoxLayout.Y_AXIS));

                paymentContainer.add(new ReceiptItem("Total: ", (int) sale.getTotalAmount()));
                paymentContainer.add(new ReceiptItem("Received: ", (int) sale.getCashReceived()));
                paymentContainer.add(new ReceiptItem("Change: ", (int) sale.getChangeAmount()));

                add(headerContainer, BorderLayout.NORTH);
                add(itemsContainer, BorderLayout.CENTER);
                add(paymentContainer, BorderLayout.SOUTH);

                pack();
                setVisible(true); // must always be at the end
        }
}