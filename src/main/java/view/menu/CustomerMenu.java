package view.menu;

import repo.Repository;
import view.Base;
import view.customer.CheckOutGrid;
import view.customer.CustomerRoomsGrid;
import view.manager.ReservationGrid;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CustomerMenu extends Base {

    public CustomerMenu(){
        super("Customer Menu");
        JLabel label = new JLabel(String.join(" ", Repository.currentPerson.getFirstName(), "!!"));
        label.setBounds(190, 20, 120, 30);
        panel.add(label);
        JButton reserveButton = new JButton();
        reserveButton.setText("Reserve a room");
        reserveButton.setBounds(30, 60, 150, 30);
        reserveButton.addActionListener(e -> reserve());
        this.panel.add(reserveButton);
        JButton checkOutButton = new JButton();
        checkOutButton.setText("Check out");
        checkOutButton.setBounds(300, 60, 150, 30);
        checkOutButton.addActionListener(e -> checkout());
        this.panel.add(checkOutButton);
        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(180, 100, 120, 30);
        logoutButton.addActionListener(e -> logout());
        this.panel.add(logoutButton);
    }

    private void reserve(){
        Frame current = Repository.getFrames().get("CustomerRoomsGrid");
        if (!Objects.isNull(current))
            current.dispose();
        this.dispose();
        new CustomerRoomsGrid();
    }

    private void checkout(){
        Frame current = Repository.getFrames().get("CheckOutGrid");
        if (!Objects.isNull(current))
            current.dispose();
        this.dispose();
        new CheckOutGrid();
    }

    private void logout(){
        this.dispose();
        Repository.logout();
    }
}
