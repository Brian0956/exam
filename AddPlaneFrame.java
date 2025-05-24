package com.mycompany.final_exam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
public class AddPlaneFrame extends JFrame {
    private JTextField txtPlaneID, txtPlaneName, txtCapacity;
    private JTable table;
    private DefaultTableModel model;

    public AddPlaneFrame() {
        setTitle("✈️ Add Plane");
        setSize(600, 470);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel lblTitle = new JLabel(" Add New Plane");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(220, 20, 250, 30);
        lblTitle.setForeground(new Color(25, 25, 112));
        add(lblTitle);

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 14);
        Color btnColor = new Color(100, 149, 237);
        Color btnText = Color.WHITE;

        JLabel lblPlaneID = new JLabel("Plane ID:");
        lblPlaneID.setFont(labelFont);
        lblPlaneID.setBounds(30, 70, 120, 25);
        add(lblPlaneID);

        txtPlaneID = new JTextField();
        txtPlaneID.setBounds(150, 70, 150, 25);
        add(txtPlaneID);

        JLabel lblPlaneName = new JLabel("Plane Name:");
        lblPlaneName.setFont(labelFont);
        lblPlaneName.setBounds(30, 110, 120, 25);
        add(lblPlaneName);

        txtPlaneName = new JTextField();
        txtPlaneName.setBounds(150, 110, 150, 25);
        add(txtPlaneName);

        JLabel lblCapacity = new JLabel("Capacity:");
        lblCapacity.setFont(labelFont);
        lblCapacity.setBounds(30, 150, 120, 25);
        add(lblCapacity);

        txtCapacity = new JTextField();
        txtCapacity.setBounds(150, 150, 150, 25);
        add(txtCapacity);

        JButton btnSave = createButton("Save", 340, 70, btnColor, btnText);
        JButton btnEdit = createButton("Edit", 340, 110, btnColor, btnText);
        JButton btnDelete = createButton("Delete", 340, 150, btnColor, btnText);
        JButton btnClear = createButton("Clear", 340, 190, Color.GRAY, btnText);

        add(btnSave);
        add(btnEdit);
        add(btnDelete);
        add(btnClear);

        model = new DefaultTableModel(new String[]{"Plane ID", "Plane Name", "Capacity"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 240, 520, 180);
        add(scrollPane);

        // Action Listeners
        btnSave.addActionListener(e -> {
            String id = txtPlaneID.getText().trim();
            String name = txtPlaneName.getText().trim();
            String cap = txtCapacity.getText().trim();
            if (!id.isEmpty() && !name.isEmpty() && !cap.isEmpty()) {
                try {
                    int capacity = Integer.parseInt(cap);
                    model.addRow(new String[]{id, name, String.valueOf(capacity)});
                    JOptionPane.showMessageDialog(this, "Plane saved successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Capacity must be a number.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        btnEdit.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                String id = txtPlaneID.getText().trim();
                String name = txtPlaneName.getText().trim();
                String cap = txtCapacity.getText().trim();
                if (!id.isEmpty() && !name.isEmpty() && !cap.isEmpty()) {
                    try {
                        int capacity = Integer.parseInt(cap);
                        model.setValueAt(id, selected, 0);
                        model.setValueAt(name, selected, 1);
                        model.setValueAt(String.valueOf(capacity), selected, 2);
                        JOptionPane.showMessageDialog(this, "Plane updated.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Capacity must be a number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.removeRow(selected);
                JOptionPane.showMessageDialog(this, "Plane deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });

        btnClear.addActionListener(e -> {
            txtPlaneID.setText("");
            txtPlaneName.setText("");
            txtCapacity.setText("");
            table.clearSelection();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                if (selected != -1) {
                    txtPlaneID.setText(model.getValueAt(selected, 0).toString());
                    txtPlaneName.setText(model.getValueAt(selected, 1).toString());
                    txtCapacity.setText(model.getValueAt(selected, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, Color bg, Color fg) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPlaneFrame());
    }
}
