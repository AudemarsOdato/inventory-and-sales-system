package components;

import java.awt.Font;
import javax.swing.JLabel;

public class Text extends JLabel {
        public Text(String text, int size) {
                setText(text);
                setFont(new Font("Arial", Font.PLAIN, size));
        }
}
