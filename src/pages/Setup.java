package pages;

import components.Button;
import components.Container;
import components.Input;
import components.InputPassword;
import components.Page;
import components.Text;
import controllers.SetupController;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import models.Response;
import models.User;
import utilities.InputHelper;

public class Setup extends Page {
        // create window
        public Setup() {
                setTitle("Setting up the store - POS AND INVENTORY MANAGEMENT SYSTEM");
                setLayout(new GridBagLayout());

                Container container = new Container();
                container.setLayout(new GridLayout(11, 1));
                container.setPreferredSize(new Dimension(300, 400));
                container.add(new Text("Setting up the store", Text.HEADER_SIZE));
                container.add(new Text("store name", Text.TEXT_SIZE));
                Input storeNameInput = new Input();
                container.add(storeNameInput);
                container.add(new Text("owner username", Text.TEXT_SIZE));
                Input storeOwnerInput = new Input();
                container.add(storeOwnerInput);
                container.add(new Text("password", Text.TEXT_SIZE));
                InputPassword passInput = new InputPassword();
                container.add(passInput);
                container.add(new Text("confirm password", Text.TEXT_SIZE));
                InputPassword passConfirmInput = new InputPassword();
                container.add(passConfirmInput);
                container.add(new Container());
                Button setupButton = new Button("CREATE");
                container.add(setupButton);
                add(container);

                // refactor: use Response model object for the controller
                setupButton.addActionListener(e -> { 
                        boolean hasEmptyInputs = new InputHelper().hasEmpty(storeNameInput, storeOwnerInput, passInput, passConfirmInput);
                        if (hasEmptyInputs) {
                                JOptionPane.showMessageDialog(null, "Fill all inputs");
                                return;
                        }
                        Response response = new SetupController().setup(storeNameInput.getText(), storeOwnerInput.getText(), passInput.getText(), passConfirmInput.getText());
                        if (!response.ok()) {
                                System.out.println("is response ok: " +response.ok());
                                JOptionPane.showMessageDialog(null, response.getMessage());
                                return;
                        }

                        User user = (User)response.getObject();
                        
                        new Owner(user);                        
                        dispose();
                });

                // pack(); // set size to fit content
                setLocationRelativeTo(null);
                setVisible(true); // must always be at the end
        }
}