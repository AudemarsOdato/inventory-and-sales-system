package pages;

import context.UserContext;
import database.Users;
import java.awt.*;
import javax.swing.*;
import models.User;
import utilities.JCrypt;

public class CashiersSettings extends JFrame {

        private JTextField txtCashierName;
        private JPasswordField txtCurrentPassword;
        private JPasswordField txtNewPassword;
        private JPasswordField txtConfirmPassword;
        private JButton btnSave;

        public CashiersSettings(User user, Cashier cashier) {
                setTitle("Cashier's settings page");
                setSize(650, 350);
                
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 10, 8, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                
                JLabel lblHeader = new JLabel("Your Settings");
                lblHeader.setFont(new Font("Arial", Font.BOLD, 16));

                JLabel lblCashierName = new JLabel("Cashier name:");
                
                JLabel lblPasswordChange = new JLabel("Password change");
                lblPasswordChange.setForeground(Color.RED);
                
                JLabel lblCurrentPassword = new JLabel("Current password:");
                JLabel lblNewPassword = new JLabel("New password:");
                JLabel lblConfirmPassword = new JLabel("Confirm new password:");
                
                txtCashierName = new JTextField(20);
                txtCurrentPassword = new JPasswordField(20);
                txtNewPassword = new JPasswordField(20);
                txtConfirmPassword = new JPasswordField(20);
                
                btnSave = new JButton("SAVE CHANGES");
                
                gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
                panel.add(lblHeader, gbc);
                
                gbc.gridwidth = 1;
                gbc.gridx = 0; gbc.gridy = 1;
                panel.add(lblCashierName, gbc);

                gbc.gridx = 1;
                panel.add(txtCashierName, gbc);

                gbc.gridx = 0; gbc.gridy = 2;
                panel.add(lblPasswordChange, gbc);

                gbc.gridx = 0; gbc.gridy = 3;
                panel.add(lblCurrentPassword, gbc);
                
                gbc.gridx = 1;
                panel.add(txtCurrentPassword, gbc);

                gbc.gridx = 0; gbc.gridy = 4;
                panel.add(lblNewPassword, gbc);

                gbc.gridx = 1;
                panel.add(txtNewPassword, gbc);
                
                gbc.gridx = 0; gbc.gridy = 5;
                panel.add(lblConfirmPassword, gbc);

                gbc.gridx = 1;
                panel.add(txtConfirmPassword, gbc);
                
                gbc.gridx = 1; gbc.gridy = 6;
                panel.add(btnSave, gbc);
                
                txtCashierName.setText(user.getName());
                btnSave.addActionListener(e -> {
                        Users users = new Users();
                        JCrypt jCrypt = new JCrypt();
                        if (txtCashierName.getText().equals("")) {
                                dispose();
                                return;
                        }
                        users.updateOne(user.getId(), user.getRole(), txtCashierName.getText(), user.getPassword(), user.getTotalSales());
                        User newUser = users.getOne(user.getId());
                        new UserContext(user.getId(), newUser.getName());
                        new Cashier(newUser);
                        cashier.closeCurrent();
                        
                        String currentPasword = txtCurrentPassword.getText();
                        String newPassword = txtNewPassword.getText();
                        String confirmPassword = txtConfirmPassword.getText();
                        if (currentPasword.equals("")) {
                                dispose();
                                return; // exits when user is not trying to change his/her own password
                        }
                        if (!(jCrypt.isMatch(currentPasword, user.getPassword()))) {
                                JOptionPane.showMessageDialog(null, "Incorrect password!");
                                return; // stops the flow from reaching dispose method
                        }
                        if (!newPassword.equals(confirmPassword)) {
                                JOptionPane.showMessageDialog(null, "Password does not match.");
                                return;
                        }
                        // update the password using jcrypt to hash new password
                        users.updateOne(user.getId(), user.getRole(), user.getName(), jCrypt.hash(newPassword), user.getTotalSales());
                        
                        dispose();
                });

                add(panel);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(true);

        }
}