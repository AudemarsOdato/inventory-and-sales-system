package utilities;

import java.util.Arrays;
import javax.swing.JTextField;

public class InputHelper {
        public InputHelper() {

        }

        public boolean hasEmpty(JTextField... inputs) {
                return Arrays.stream(inputs).anyMatch(f -> f.getText().trim().isEmpty());
        }
}