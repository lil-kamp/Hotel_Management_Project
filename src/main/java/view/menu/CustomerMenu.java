package view.menu;

import javax.swing.*;

public class CustomerMenu extends Menu{

    public CustomerMenu(){
        super("Customer Menu");
        JButton roomsButton = new JButton();
        roomsButton.setText("Reserve a room");
        roomsButton.setBounds(30, 60, 150, 30);
        this.panel.add(roomsButton);
        JButton reservationsButton = new JButton();
        reservationsButton.setText("Check out");
        reservationsButton.setBounds(300, 60, 150, 30);
        this.panel.add(reservationsButton);
        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(180, 100, 120, 30);
        this.panel.add(logoutButton);
    }
}
