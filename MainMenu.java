package com.mycompany.final_exam;
import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("✈️ Plane Reservation System");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel(" Plane Reservation System");
        title.setBounds(70, 30, 400, 40);
        title.setFont(new Font("Times New Roman", Font.BOLD, 22));
        title.setForeground(new Color(25, 25, 112));
        add(title);

        Font btnFont = new Font("Times New Roman", Font.PLAIN, 16);
        Color btnColor = new Color(100, 149, 237);
        Color btnText = Color.WHITE;

        JButton btnAddPlane = createButton("Add Plane", 100, 100, btnFont, btnColor, btnText);
        JButton btnAddPilot = createButton(" Add Pilot", 100, 150, btnFont, btnColor, btnText);
        JButton btnAddPassenger = createButton("Add Passenger", 100, 200, btnFont, btnColor, btnText);
        JButton btnReservePassenger = createButton(" Reserve Passenger", 100, 250, btnFont, btnColor, btnText);
        JButton btnAssignCrew = createButton("Assign Pilot & View Passengers", 100, 300, btnFont, btnColor, btnText);
        JButton btnExit = createButton("Exit", 100, 350, btnFont, Color.RED.darker(), btnText);

        
        add(btnAddPlane);
        add(btnAddPilot);
        add(btnAddPassenger);
        add(btnReservePassenger);
        add(btnAssignCrew);
        add(btnExit);

        
        btnAddPlane.addActionListener(e -> new AddPlaneFrame());
        btnAddPilot.addActionListener(e -> new AddPilotFrame());
        btnAddPassenger.addActionListener(e -> new AddPassengerFrame());
        btnReservePassenger.addActionListener(e -> new ReservePassengerFrame());
        btnAssignCrew.addActionListener(e -> new AssignCrewFrame());
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, Font font, Color bg, Color fg) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 35);
        button.setFont(font);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}