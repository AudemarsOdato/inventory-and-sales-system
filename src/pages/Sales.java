package pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Sales extends JFrame {

    public Sales() {
        setTitle("Sales Dashboard - POS AND INVENTORY MANAGEMENT SYSTEM");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Sales page");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panel.add(title, BorderLayout.NORTH);

        String[] columns = {
                "id", "time", "date", "totalAmount", "action"
        };

        Object[][] data = {
                {"18275", "19:32:58", "12-11-2025", "182.00", "View"},
                {"18275", "19:32:58", "12-11-2025", "182.00", "View"},
                {"18275", "19:32:58", "12-11-2025", "182.00", "View"},
                {"18275", "19:32:58", "12-11-2025", "182.00", "View"},
                {"18275", "19:32:58", "12-11-2025", "182.00", "View"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only action column is editable (button)
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Add button renderer and editor
        table.getColumn("action").setCellRenderer(new ButtonRenderer());
        table.getColumn("action").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    // BUTTON RENDERER
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("View");
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // BUTTON EDITOR
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;

            button = new JButton("View");
            button.addActionListener(e -> {
                int row = table.getSelectedRow();
                String saleId = table.getValueAt(row, 0).toString();

                JOptionPane.showMessageDialog(
                        null,
                        "Viewing details for Sale ID: " + saleId,
                        "Sale Details",
                        JOptionPane.INFORMATION_MESSAGE
                );
            });
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected,
                int row, int column) {
            return button;
        }
    }
}