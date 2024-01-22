package view.menu;

import javax.swing.*;

public class ManagerMenu extends Menu{

    public ManagerMenu(){
        super("Manager Menu");
        JButton roomsButton = new JButton();
        roomsButton.setText("Rooms");
        roomsButton.setBounds(30, 60, 120, 30);
        this.panel.add(roomsButton);
        JButton workersButton = new JButton();
        workersButton.setText("Workers");
        workersButton.setBounds(180, 60, 120, 30);
        this.panel.add(workersButton);
        JButton reservationsButton = new JButton();
        reservationsButton.setText("Reservations");
        reservationsButton.setBounds(330, 60, 120, 30);
        this.panel.add(reservationsButton);
        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(180, 100, 120, 30);
        this.panel.add(logoutButton);
    }
}
