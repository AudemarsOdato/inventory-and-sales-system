package pages;

import components.Page;
import javax.swing.JButton;

public class Setup extends Page {
        // create window
        public Setup() {
                setTitle("Setting up the store - POS AND INVENTORY MANAGEMENT SYSTEM");
                add(new JButton("CREATE"));

                setVisible(true);
        }
}