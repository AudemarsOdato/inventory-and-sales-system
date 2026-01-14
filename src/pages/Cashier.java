package pages;

import components.Button;
import components.Container;
import components.Page;
import components.Text;
import context.UserContext;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import models.User;

public class Cashier extends Page{
        public Cashier(User user) {
                setTitle("Cashier Dashboard - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new GridBagLayout());

                String loggedInCashier = user.getName();

                Container mainContainer = new Container();
                mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                mainContainer.setPreferredSize(new Dimension(700, 500));
                
                Container headerContainer = new Container(true);
                headerContainer.setLayout(new BorderLayout(10, 10));
                headerContainer.setPreferredSize(new Dimension(593, 78));
                headerContainer.add(new Text("Cashier: " + loggedInCashier, Text.TEXT_SIZE, true), BorderLayout.WEST);
                headerContainer.add(new Text(LocalDate.now().toString(), Text.TEXT_SIZE, true), BorderLayout.EAST);
                
                Container buttonsContainer = new Container();
                buttonsContainer.setLayout(new GridLayout(1, 3, 20, 20)); // 1 row, 3 columns
                buttonsContainer.setPreferredSize(new Dimension(593, 150));

                Button posButton = new Button("POS");
                Button cashierSettingsButton = new Button("CASHIER SETTINGS");
                Button logoutButton = new Button("LOG OUT");

                posButton.addActionListener(e -> { 
                        new POS(user);
                        dispose();
                });

                cashierSettingsButton.addActionListener(e -> { 
                        new CashiersSettings(user, this);
                });

                logoutButton.addActionListener(e -> { 
                        new UserContext().removeUser();
                        new Login();
                        dispose();
                });

                Button[] buttons = {
                        posButton,
                        cashierSettingsButton,
                        logoutButton
                };
                for (Button btn : buttons) {
                        buttonsContainer.add(btn);
                }

                mainContainer.add(headerContainer);
                mainContainer.add(buttonsContainer);
                add(mainContainer);

                setLocationRelativeTo(null);
                setVisible(true);
        }

        protected void closeCurrent() {
                this.dispose();
        }
}