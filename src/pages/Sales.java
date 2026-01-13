package pages;

import components.Button;
import components.Container;
import components.Text;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import models.User;

public class Sales extends JFrame {

    public Sales(User user) {
        setTitle("Sales Dashboard - POS AND INVENTORY MANAGEMENT SYSTEM");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Container headerContainer = new Container();
        headerContainer.setLayout(new BorderLayout());
        headerContainer.add(new Text("Sales record", Text.TEXT_SIZE), BorderLayout.CENTER);
        Button backButton = new Button("<--");
        backButton.addActionListener(e -> {
                new Owner(user);
                dispose();
        });
        headerContainer.add(backButton, BorderLayout.WEST);
        panel.add(headerContainer, BorderLayout.NORTH);

        String[] columns = {
                "id", "time", "date", "totalAmount", "action"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
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

        model.addRow(new Object[] {
                "productId",
                "itemName",
                "quantity",
                "price",
                2*2
        });

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