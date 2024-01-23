package view.menu;

import repo.Repository;
import view.Base;
import view.manager.ReservationGrid;
import view.manager.RoomsGrid;
import view.manager.WorkersGrid;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ManagerMenu extends Base {

    public ManagerMenu(){
        super("Manager Menu");
        JLabel label = new JLabel(String.join(" ", Repository.currentPerson.getFirstName(), "!!"));
        label.setBounds(190, 20, 120, 30);
        panel.add(label);
        JButton roomsButton = new JButton();
        roomsButton.setText("Rooms");
        roomsButton.setBounds(30, 60, 120, 30);
        roomsButton.addActionListener(e -> rooms());
        this.panel.add(roomsButton);
        JButton workersButton = new JButton();
        workersButton.setText("Workers");
        workersButton.setBounds(180, 60, 120, 30);
        workersButton.addActionListener(e -> workers());
        this.panel.add(workersButton);
        JButton reservationsButton = new JButton();
        reservationsButton.setText("Reservations");
        reservationsButton.setBounds(330, 60, 120, 30);
        reservationsButton.addActionListener(e -> reservations());
        this.panel.add(reservationsButton);
        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(180, 100, 120, 30);
        logoutButton.addActionListener(e -> logout());
        this.panel.add(logoutButton);
    }

    private void rooms(){
        Frame current = Repository.getFrames().get("RoomsGrid");
        if (!Objects.isNull(current))
            current.dispose();
        this.dispose();
        new RoomsGrid();
    }

    private void reservations(){
        Frame current = Repository.getFrames().get("ReservationGrid");
        if (!Objects.isNull(current))
            current.dispose();
        this.dispose();
        new ReservationGrid();
    }

    private void workers(){
        Frame current = Repository.getFrames().get("WorkersGrid");
        if (!Objects.isNull(current))
            current.dispose();
        this.dispose();
        new WorkersGrid();
    }

    private void logout(){
        this.dispose();
        Repository.logout();
    }
}
