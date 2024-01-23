package view.menu;

import view.Base;

import javax.swing.*;

public class CustomerMenu extends Base {

    public CustomerMenu(){
        super("Customer Menu");
//        JLabel label = new JLabel(String.join(" ", Repository.currentPerson.getFirstName(), "!!"));
//        label.setBounds(190, 20, 120, 30);
//        panel.add(label);
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
