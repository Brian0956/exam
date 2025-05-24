package com.mycompany.final_exam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AddPilotFrame extends JFrame {
    private JTextField txtPilotID, txtPilotName, txtExperience;
    private JTable table;
    private DefaultTableModel model;

    public AddPilotFrame() {
        setTitle("Add Pilot");
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel lblTitle = new JLabel("Add New Pilot");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(200, 20, 300, 30);
        lblTitle.setForeground(new Color(25, 25, 112));
        add(lblTitle);

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 14);
        Color btnColor = new Color(100, 149, 237);
        Color btnText = Color.WHITE;

        JLabel lblPilotID = new JLabel("Pilot ID:");
        lblPilotID.setFont(labelFont);
        lblPilotID.setBounds(30, 70, 100, 25);
        add(lblPilotID);

        txtPilotID = new JTextField();
        txtPilotID.setBounds(130, 70, 150, 25);
        add(txtPilotID);

        JLabel lblPilotName = new JLabel("Pilot Name:");
        lblPilotName.setFont(labelFont);
        lblPilotName.setBounds(30, 110, 100, 25);
        add(lblPilotName);

        txtPilotName = new JTextField();
        txtPilotName.setBounds(130, 110, 150, 25);
        add(txtPilotName);

        JLabel lblExperience = new JLabel("Experience (Years):");
        lblExperience.setFont(labelFont);
        lblExperience.setBounds(30, 150, 150, 25);
        add(lblExperience);

        txtExperience = new JTextField();
        txtExperience.setBounds(180, 150, 100, 25);
        add(txtExperience);

        JButton btnSave = createButton("Save", 320, 70, btnColor, btnText);
        JButton btnEdit = createButton("Edit", 320, 110, btnColor, btnText);
        JButton btnDelete = createButton("Delete", 320, 150, btnColor, btnText);
        JButton btnClear = createButton("Clear", 320, 190, Color.GRAY, btnText);

        add(btnSave);
        add(btnEdit);
        add(btnDelete);
        add(btnClear);

        model = new DefaultTableModel(new String[]{"Pilot ID", "Name", "Experience"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 240, 520, 140);
        add(scrollPane);

        // Action Listeners
        btnSave.addActionListener(e -> {
            String id = txtPilotID.getText();
            String name = txtPilotName.getText();
            String exp = txtExperience.getText();
            if (!id.isEmpty() && !name.isEmpty() && !exp.isEmpty()) {
                model.addRow(new String[]{id, name, exp});
                JOptionPane.showMessageDialog(this, "Pilot saved successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        btnEdit.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.setValueAt(txtPilotID.getText(), selected, 0);
                model.setValueAt(txtPilotName.getText(), selected, 1);
                model.setValueAt(txtExperience.getText(), selected, 2);
                JOptionPane.showMessageDialog(this, "Pilot updated.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.removeRow(selected);
                JOptionPane.showMessageDialog(this, "Pilot deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });

        btnClear.addActionListener(e -> {
            txtPilotID.setText("");
            txtPilotName.setText("");
            txtExperience.setText("");
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                txtPilotID.setText(model.getValueAt(selected, 0).toString());
                txtPilotName.setText(model.getValueAt(selected, 1).toString());
                txtExperience.setText(model.getValueAt(selected, 2).toString());
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