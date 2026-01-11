package components;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Container extends JPanel {
        public Container() {

        }
        
        public Container(boolean isBordered) {
                // set border
                setBorder(new LineBorder(Color.DARK_GRAY, 1));
        }
}