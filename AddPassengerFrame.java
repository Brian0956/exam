package com.mycompany.final_exam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AddPassengerFrame extends JFrame {
    private JTextField txtPassengerID, txtPassengerName, txtAge;
    private JTable table;
    private DefaultTableModel model;

    public AddPassengerFrame() {
        setTitle("🧍 Add Passenger");
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel lblTitle = new JLabel("Add New Passenger");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(180, 20, 300, 30);
        lblTitle.setForeground(new Color(25, 25, 112));
        add(lblTitle);

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 14);
        Color btnColor = new Color(70, 130, 180);
        Color btnText = Color.WHITE;

        JLabel lblID = new JLabel("Passenger ID:");
        lblID.setFont(labelFont);
        lblID.setBounds(30, 70, 120, 25);
        add(lblID);

        txtPassengerID = new JTextField();
        txtPassengerID.setBounds(150, 70, 150, 25);
        add(txtPassengerID);

        JLabel lblName = new JLabel("Passenger Name:");
        lblName.setFont(labelFont);
        lblName.setBounds(30, 110, 120, 25);
        add(lblName);

        txtPassengerName = new JTextField();
        txtPassengerName.setBounds(150, 110, 150, 25);
        add(txtPassengerName);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(labelFont);
        lblAge.setBounds(30, 150, 120, 25);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(150, 150, 150, 25);
        add(txtAge);

        JButton btnSave = createButton("Save", 340, 70, btnColor, btnText);
        JButton btnEdit = createButton("Edit", 340, 110, btnColor, btnText);
        JButton btnDelete = createButton("Delete", 340, 150, btnColor, btnText);
        JButton btnClear = createButton("Clear", 340, 190, Color.GRAY, btnText);

        add(btnSave);
        add(btnEdit);
        add(btnDelete);
        add(btnClear);

        model = new DefaultTableModel(new String[]{"Passenger ID", "Name", "Age"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 240, 520, 140);
        add(scrollPane);

        // Button functionality
        btnSave.addActionListener(e -> {
            String id = txtPassengerID.getText();
            String name = txtPassengerName.getText();
            String age = txtAge.getText();
            if (!id.isEmpty() && !name.isEmpty() && !age.isEmpty()) {
                model.addRow(new String[]{id, name, age});
                JOptionPane.showMessageDialog(this, "Passenger saved successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        btnEdit.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.setValueAt(txtPassengerID.getText(), selected, 0);
                model.setValueAt(txtPassengerName.getText(), selected, 1);
                model.setValueAt(txtAge.getText(), selected, 2);
                JOptionPane.showMessageDialog(this, "Passenger updated.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.removeRow(selected);
                JOptionPane.showMessageDialog(this, "Passenger deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });

        btnClear.addActionListener(e -> {
            txtPassengerID.setText("");
            txtPassengerName.setText("");
            txtAge.setText("");
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                txtPassengerID.setText(model.getValueAt(selected, 0).toString());
                txtPassengerName.setText(model.getValueAt(selected, 1).toString());
                txtAge.setText(model.getValueAt(selected, 2).toString());
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
}