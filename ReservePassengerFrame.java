package com.mycompany.final_exam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ReservePassengerFrame extends JFrame {
    private JTextField txtReservationID, txtPassengerID, txtPlaneID;
    private JTable table;
    private DefaultTableModel model;

    public ReservePassengerFrame() {
        setTitle("🎟 Reserve Passenger");
        setSize(600, 460);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel lblTitle = new JLabel("Reserve a Passenger");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(180, 20, 300, 30);
        lblTitle.setForeground(new Color(25, 25, 112));
        add(lblTitle);

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 14);
        Color btnColor = new Color(70, 130, 180);
        Color btnText = Color.WHITE;

        JLabel lblReservationID = new JLabel("Reservation ID:");
        lblReservationID.setFont(labelFont);
        lblReservationID.setBounds(30, 70, 120, 25);
        add(lblReservationID);

        txtReservationID = new JTextField();
        txtReservationID.setBounds(150, 70, 150, 25);
        add(txtReservationID);

        JLabel lblPassengerID = new JLabel("Passenger ID:");
        lblPassengerID.setFont(labelFont);
        lblPassengerID.setBounds(30, 110, 120, 25);
        add(lblPassengerID);

        txtPassengerID = new JTextField();
        txtPassengerID.setBounds(150, 110, 150, 25);
        add(txtPassengerID);

        JLabel lblPlaneID = new JLabel("Plane ID:");
        lblPlaneID.setFont(labelFont);
        lblPlaneID.setBounds(30, 150, 120, 25);
        add(lblPlaneID);

        txtPlaneID = new JTextField();
        txtPlaneID.setBounds(150, 150, 150, 25);
        add(txtPlaneID);

        JButton btnReserve = createButton("Reserve", 340, 70, btnColor, btnText);
        JButton btnEdit = createButton("Edit", 340, 110, btnColor, btnText);
        JButton btnDelete = createButton("elete", 340, 150, btnColor, btnText);
        JButton btnClear = createButton("Clear", 340, 190, Color.GRAY, btnText);

        add(btnReserve);
        add(btnEdit);
        add(btnDelete);
        add(btnClear);

        model = new DefaultTableModel(new String[]{"Reservation ID", "Passenger ID", "Plane ID"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 240, 520, 150);
        add(scrollPane);

        // Actions
        btnReserve.addActionListener(e -> {
            String resId = txtReservationID.getText();
            String passId = txtPassengerID.getText();
            String planeId = txtPlaneID.getText();
            if (!resId.isEmpty() && !passId.isEmpty() && !planeId.isEmpty()) {
                model.addRow(new String[]{resId, passId, planeId});
                JOptionPane.showMessageDialog(this, "Reservation added.");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        btnEdit.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.setValueAt(txtReservationID.getText(), selected, 0);
                model.setValueAt(txtPassengerID.getText(), selected, 1);
                model.setValueAt(txtPlaneID.getText(), selected, 2);
                JOptionPane.showMessageDialog(this, "Reservation updated.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to edit.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.removeRow(selected);
                JOptionPane.showMessageDialog(this, "Reservation deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });

        btnClear.addActionListener(e -> {
            txtReservationID.setText("");
            txtPassengerID.setText("");
            txtPlaneID.setText("");
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                txtReservationID.setText(model.getValueAt(selected, 0).toString());
                txtPassengerID.setText(model.getValueAt(selected, 1).toString());
                txtPlaneID.setText(model.getValueAt(selected, 2).toString());
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