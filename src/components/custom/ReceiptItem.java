package components.custom;

import components.Container;
import components.Text;
import java.awt.BorderLayout;

public class ReceiptItem extends Container {

        public ReceiptItem(String name, int quantity, double unitPrice) {
                setLayout(new BorderLayout());

                double total = quantity * unitPrice;

                Text left = new Text(
                        name + " " + quantity + " x " + unitPrice,
                        Text.TEXT_SIZE
                );

                Text right = new Text(
                        String.valueOf(total),
                        Text.TEXT_SIZE
                );

                add(left, BorderLayout.WEST);
                add(right, BorderLayout.EAST);
        }

        public ReceiptItem(String key, double value) {
                setLayout(new BorderLayout());

                Text left = new Text(
                        key,
                        Text.TEXT_SIZE
                );

                Text right = new Text(
                        String.valueOf(value),
                        Text.TEXT_SIZE
                );

                add(left, BorderLayout.WEST);
                add(right, BorderLayout.EAST);
        }
}