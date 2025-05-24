package com.mycompany.final_exam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AssignCrewFrame extends JFrame {
    private JTextField txtAssignmentID, txtPilotID, txtPassengerID;
    private JTable table;
    private DefaultTableModel model;

    public AssignCrewFrame() {
        setTitle("✈️👤 Assign Crew");
        setSize(600, 470);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel lblTitle = new JLabel("✈️👤 Assign Pilot & Passenger");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(150, 20, 350, 30);
        lblTitle.setForeground(new Color(25, 25, 112));
        add(lblTitle);

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 14);
        Color btnColor = new Color(100, 149, 237);
        Color btnText = Color.WHITE;

        JLabel lblAssignID = new JLabel("Assignment ID:");
        lblAssignID.setFont(labelFont);
        lblAssignID.setBounds(30, 70, 120, 25);
        add(lblAssignID);

        txtAssignmentID = new JTextField();
        txtAssignmentID.setBounds(150, 70, 150, 25);
        add(txtAssignmentID);

        JLabel lblPilotID = new JLabel("Pilot ID:");
        lblPilotID.setFont(labelFont);
        lblPilotID.setBounds(30, 110, 120, 25);
        add(lblPilotID);

        txtPilotID = new JTextField();
        txtPilotID.setBounds(150, 110, 150, 25);
        add(txtPilotID);

        JLabel lblPassengerID = new JLabel("Passenger ID:");
        lblPassengerID.setFont(labelFont);
        lblPassengerID.setBounds(30, 150, 120, 25);
        add(lblPassengerID);

        txtPassengerID = new JTextField();
        txtPassengerID.setBounds(150, 150, 150, 25);
        add(txtPassengerID);

        JButton btnAssign = createButton("Assign", 340, 70, btnColor, btnText);
        JButton btnEdit = createButton("Edit", 340, 110, btnColor, btnText);
        JButton btnDelete = createButton("Delete", 340, 150, btnColor, btnText);
        JButton btnClear = createButton("Clear", 340, 190, Color.GRAY, btnText);

        add(btnAssign);
        add(btnEdit);
        add(btnDelete);
        add(btnClear);

        model = new DefaultTableModel(new String[]{"Assignment ID", "Pilot ID", "Passenger ID"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 240, 520, 160);
        add(scrollPane);

        // Action Listeners
        btnAssign.addActionListener(e -> {
            String assignId = txtAssignmentID.getText();
            String pilotId = txtPilotID.getText();
            String passId = txtPassengerID.getText();
            if (!assignId.isEmpty() && !pilotId.isEmpty() && !passId.isEmpty()) {
                model.addRow(new String[]{assignId, pilotId, passId});
                JOptionPane.showMessageDialog(this, "Crew assigned successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        btnEdit.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.setValueAt(txtAssignmentID.getText(), selected, 0);
                model.setValueAt(txtPilotID.getText(), selected, 1);
                model.setValueAt(txtPassengerID.getText(), selected, 2);
                JOptionPane.showMessageDialog(this, "Assignment updated.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.removeRow(selected);
                JOptionPane.showMessageDialog(this, "Assignment deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });

        btnClear.addActionListener(e -> {
            txtAssignmentID.setText("");
            txtPilotID.setText("");
            txtPassengerID.setText("");
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                txtAssignmentID.setText(model.getValueAt(selected, 0).toString());
                txtPilotID.setText(model.getValueAt(selected, 1).toString());
                txtPassengerID.setText(model.getValueAt(selected, 2).toString());
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