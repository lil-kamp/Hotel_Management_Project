package view.menu;

import javax.swing.*;

public class WorkerMenu extends Menu{

    public WorkerMenu(){
        super("Worker Menu");
        JButton roomsButton = new JButton();
        roomsButton.setText("Accepted res");
        roomsButton.setBounds(30, 60, 120, 30);
        this.panel.add(roomsButton);
        JButton workersButton = new JButton();
        workersButton.setText("Rejected res");
        workersButton.setBounds(180, 60, 120, 30);
        this.panel.add(workersButton);
        JButton reservationsButton = new JButton();
        reservationsButton.setText("checklist");
        reservationsButton.setBounds(330, 60, 120, 30);
        this.panel.add(reservationsButton);
        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(180, 100, 120, 30);
        this.panel.add(logoutButton);
    }
}
