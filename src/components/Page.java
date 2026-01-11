package components;

import javax.swing.JFrame;

public class Page extends JFrame {
        // page format settings options
        final protected static int HEADER_SIZE = 32;
        final protected static int TEXT_SIZE = 20;

        public Page() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1000, 700);
        }
}