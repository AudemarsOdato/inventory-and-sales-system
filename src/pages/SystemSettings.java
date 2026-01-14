package pages;

import context.StoreContext;
import database.Config;
import database.Users;
import java.awt.*;
import javax.swing.*;
import models.User;
import utilities.JCrypt;

public class SystemSettings extends JFrame {

        private JTextField txtStoreName;
        private JPasswordField txtCurrentPassword;
        private JPasswordField txtNewPassword;
        private JPasswordField txtConfirmPassword;
        private JButton btnSave;

        public SystemSettings(User user) {
                setTitle("System's settings page");
                setSize(650, 400);
                
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 10, 8, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                
                JLabel lblHeader = new JLabel("System Settings");
                lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
                
                JLabel lblStoreName = new JLabel("Store name:");

                JLabel lblPasswordChange = new JLabel("Password change");
                lblPasswordChange.setForeground(Color.RED);

                JLabel lblCurrentPassword = new JLabel("Current password:");
                JLabel lblNewPassword = new JLabel("New password:");
                JLabel lblConfirmPassword = new JLabel("Confirm new password:");

                txtStoreName = new JTextField(20);
                txtCurrentPassword = new JPasswordField(20);
                txtNewPassword = new JPasswordField(20);
                txtConfirmPassword = new JPasswordField(20);
                
                btnSave = new JButton("SAVE CHANGES");
                
                gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
                panel.add(lblHeader, gbc);
                
                gbc.gridwidth = 1;
                gbc.gridx = 0; gbc.gridy = 1;
                panel.add(lblStoreName, gbc);
                
                gbc.gridx = 1;
                panel.add(txtStoreName, gbc);

                gbc.gridx = 0; gbc.gridy = 3;
                panel.add(lblPasswordChange, gbc);
                
                gbc.gridx = 0; gbc.gridy = 4;
                panel.add(lblCurrentPassword, gbc);
                
                gbc.gridx = 1;
                panel.add(txtCurrentPassword, gbc);
                
                gbc.gridx = 0; gbc.gridy = 5;
                panel.add(lblNewPassword, gbc);
                
                gbc.gridx = 1;
                panel.add(txtNewPassword, gbc);

                gbc.gridx = 0; gbc.gridy = 6;
                panel.add(lblConfirmPassword, gbc);
                
                gbc.gridx = 1;
                panel.add(txtConfirmPassword, gbc);
                
                gbc.gridx = 1; gbc.gridy = 7;
                panel.add(btnSave, gbc);

                Config config = new Config();
                txtStoreName.setText(config.getStoreTitle());
                btnSave.addActionListener(e -> {
                        if (txtStoreName.getText().equals("")) {
                                dispose();
                                return;
                        }
                        config.updateStoreTitle(txtStoreName.getText());
                        new StoreContext(config.getStoreTitle());
                        Users users = new Users();
                        JCrypt jCrypt = new JCrypt();

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
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setVisible(true);
        }
}