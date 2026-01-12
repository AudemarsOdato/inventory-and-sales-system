package components;

import java.awt.Font;
import javax.swing.JLabel;

public class Text extends JLabel {
        final public static int HEADER_SIZE = 32;
        final public static int TEXT_SIZE = 20;
        final public static int TEXT_SMALL = 16;
        public Text(String text, int size) {
                setText(text);
                setFont(new Font("Arial", Font.PLAIN, size));
        }

        public Text(String text, int size, boolean  isBold) {
                setText(text);
                setFont(new Font("Arial", Font.BOLD, size));
        }
}
