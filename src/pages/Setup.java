package pages;

import components.Page;
import javax.swing.JButton;

public class Setup extends Page {
        // create window
        public Setup() {
                setTitle("Setting up the store");
                add(new JButton("CREATE"));
        }
}