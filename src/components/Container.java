package components;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Container extends JPanel {
        public Container() {

        }
        
        public Container(boolean isBordered) {
                // set border
                setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.GRAY),
                                BorderFactory.createEmptyBorder(10, 10, 10, 10)
                        )
                );
        }

        public Container(boolean isBordered, int paddingWidth, int paddingHeight) {
                // set border
                setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.GRAY),
                                BorderFactory.createEmptyBorder(paddingWidth, paddingHeight, paddingWidth, paddingHeight)
                        )
                );
        }

        public Container(int paddingWidth, int paddingHeight) {
                // set border
                setBorder(
                        BorderFactory.createEmptyBorder(paddingWidth, paddingHeight, paddingWidth, paddingHeight)
                );
        }

        public void addBorder() {
                setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.GRAY),
                                BorderFactory.createEmptyBorder(10, 10, 10, 10)
                        )
                );
        }
}