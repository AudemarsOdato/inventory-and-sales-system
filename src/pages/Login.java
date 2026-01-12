package pages;

import components.Button;
import components.Container;
import components.Input;
import components.InputPassword;
import components.Page;
import components.Text;
import controllers.LoginController;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import models.Response;
import models.User;
import utilities.InputHelper;

public class Login extends Page{
        public Login() {
                setTitle("Login - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new GridBagLayout());

                Container container = new Container();
                container.setLayout(new GridLayout(11, 1));
                container.setPreferredSize(new Dimension(300, 400));
                container.add(new Text("Welcome Back!", Text.HEADER_SIZE));
                container.add(new Text("username", Text.TEXT_SIZE));
                Input usernameInput = new Input();
                container.add(usernameInput);
                container.add(new Text("password", Text.TEXT_SIZE));
                InputPassword passInput = new InputPassword();
                container.add(passInput);
                container.add(new Container());
                Button loginButton = new Button("LOGIN");
                container.add(loginButton);
                add(container);

                loginButton.addActionListener(e -> { 
                        boolean hasEmptyInputs = new InputHelper().hasEmpty(usernameInput, passInput);
                        if (hasEmptyInputs) {
                                JOptionPane.showMessageDialog(null, "Fill all inputs");
                                return;
                        }

                        Response response = new LoginController().login(usernameInput.getText(), passInput.getPassword().toString());
                        if (!response.ok()) {
                                JOptionPane.showMessageDialog(null, response.getMessage());
                                return;
                        }

                        // add to context and redirect to dashboard
                        User user = (User) response.getObject();
                        System.out.println("logged in: " + user.getName());
                        System.out.println("role: " + user.getRole());

                        dispose();
                });

                setLocationRelativeTo(null);
                setVisible(true); // must always be at the end
        }
}