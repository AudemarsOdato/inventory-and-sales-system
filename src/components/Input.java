package components;

import java.awt.Font;
import javax.swing.JTextField;

public class Input extends JTextField {
        public Input() {
                setFont(new Font("Arial", Font.PLAIN, 20));
        }

        public Input(boolean isEditable) {
                setFont(new Font("Arial", Font.PLAIN, 20));
                setEditable(isEditable);
        }
}