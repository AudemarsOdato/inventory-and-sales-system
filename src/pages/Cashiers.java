package pages;

import components.Button;
import components.Container;
import components.Input;
import components.Page;
import components.Text;
import components.custom.UserTableRow;
import database.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import models.User;
import utilities.InputHelper;
import utilities.JCrypt;

public class Cashiers extends Page {
        Users users = new Users();

        public Cashiers(User currentUser) {
                setTitle("Store Cashiers - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new BorderLayout());
                
                Container mainPanel = new Container(true);
                mainPanel.setLayout(new BorderLayout());
                Container rightPanel = new Container(true);
                
                Container headerContainer = new Container();
                headerContainer.setLayout(new BorderLayout(10, 10));
                headerContainer.setPreferredSize(new Dimension(798, 46));
                
                Button backButton = new Button("<--");
                backButton.addActionListener(e -> {
                        new Owner(currentUser);
                        dispose();
                });
                
                headerContainer.add(backButton, BorderLayout.WEST);
                headerContainer.add(new Text("CASHIERS", Text.TEXT_SIZE), BorderLayout.CENTER);
                
                Container table = new Container();
                table.setLayout(new BorderLayout());
                table.add(new UserTableRow(), BorderLayout.NORTH);
                Container tableBody = new Container();
                tableBody.setLayout(new BoxLayout(tableBody, BoxLayout.Y_AXIS));
                tableBody.setAlignmentX(Container.LEFT_ALIGNMENT);
                JScrollPane scrollPane = new JScrollPane(tableBody);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(800, 400));
                scrollPane.setMinimumSize(new Dimension(800, 200));

                for (User user : users.getAll()) {
                        tableBody.add(new UserTableRow(currentUser, user, this));
                }
                table.add(scrollPane, BorderLayout.CENTER);

                mainPanel.add(headerContainer, BorderLayout.NORTH);
                mainPanel.add(table, BorderLayout.CENTER);

                rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
                rightPanel.setPreferredSize(new Dimension(200, 0));

                rightPanel.add(new Text("            ", Text.TEXT_SIZE));
                rightPanel.add(new Text("New cashier", Text.TEXT_SIZE));
                rightPanel.add(new Text("            ", Text.TEXT_SIZE));
                
                rightPanel.add(new Text("Name", Text.TEXT_SMALL));
                Input nameInput = new Input();
                rightPanel.add(nameInput);
                
                rightPanel.add(new Text("Password", Text.TEXT_SMALL));
                Input passwordInput = new Input();
                rightPanel.add(passwordInput);
                
                rightPanel.add(new Text("Confirm password", Text.TEXT_SMALL));
                Input confirmPasswordInput = new Input();
                rightPanel.add(confirmPasswordInput);
                
                rightPanel.add(new Text("            ", Text.TEXT_SIZE));
                Button addUserButton = new Button("+ ADD");
                rightPanel.add(addUserButton);
                addUserButton.addActionListener(e -> {
                        String name = nameInput.getText();
                        String password = passwordInput.getText();
                        String confirmPassword = confirmPasswordInput.getText();
                        if (new InputHelper().hasEmpty(nameInput, passwordInput, confirmPasswordInput)) {
                                JOptionPane.showMessageDialog(null, "Fill all inputs.");
                                return;
                        }
                        if (!password.equals(confirmPassword)) {
                                JOptionPane.showMessageDialog(null, "Password does not match.");
                                return;
                        }
                        users.insert("cashier", name, new JCrypt().hash(password));
                        new Cashiers(currentUser);
                        dispose();
                });

                fixAllInputSizes(
                nameInput,
                passwordInput,
                confirmPasswordInput
                );
                
                add(mainPanel, BorderLayout.CENTER);
                add(rightPanel, BorderLayout.EAST);

                pack();
                setLocationRelativeTo(null);
                setVisible(true);
        }

        private void fixAllInputSizes(Input... inputs) {
                for (Input input : inputs) {
                        input.setMaximumSize(new Dimension(400, 30));
                }
        }
}