package view.menu;

import view.Base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenu extends Base {

    public ManagerMenu(){
        super("Manager Menu");
//        JLabel label = new JLabel(String.join(" ", Repository.currentPerson.getFirstName(), "!!"));
//        label.setBounds(190, 20, 120, 30);
//        panel.add(label);
        JButton roomsButton = new JButton();
        roomsButton.setText("Rooms");
        roomsButton.setBounds(30, 60, 120, 30);
        roomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
