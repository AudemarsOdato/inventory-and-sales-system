package components.custom;

import components.Button;
import components.Container;
import components.Text;
import database.Users;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import models.User;
import pages.Cashiers;

public class UserTableRow extends Container {
        public UserTableRow(User currentUser, User user, Cashiers cashiers) {
                setLayout(new GridLayout(1, 4));
                setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                setAlignmentX(CENTER_ALIGNMENT);
                add(new Text(String.valueOf(user.getId()), Text.TEXT_SMALL));
                add(new Text(user.getName(), Text.TEXT_SMALL));
                add(new Text(String.valueOf(user.getTotalSales()), Text.TEXT_SMALL));

                Button deleteButton = new Button("DELETE");
                deleteButton.setForeground(Color.WHITE);
                deleteButton.setBackground(Color.RED);
                deleteButton.addActionListener(e -> {
                        new Users().deleteOne(user.getId());
                        cashiers.dispose();
                        new Cashiers(currentUser);
                });
                add(deleteButton);
        }
        
        public UserTableRow() {
                setLayout(new GridLayout(1, 4));
                addBorder();
                setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                setAlignmentX(CENTER_ALIGNMENT);
                add(new Text("id", Text.TEXT_SMALL));
                add(new Text("name", Text.TEXT_SMALL));
                add(new Text("sales_count", Text.TEXT_SMALL));
                add(new Text("", Text.TEXT_SMALL));
        }
}