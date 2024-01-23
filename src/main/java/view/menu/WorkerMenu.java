package view.menu;

import model.enums.ReservationStatus;
import repo.Repository;
import view.Base;
import view.worker.CheckListGrid;
import view.worker.WorkerReservationGrid;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WorkerMenu extends Base {

    public WorkerMenu(){
        super("Worker Menu");
        JLabel label = new JLabel(String.join(" ", Repository.currentPerson.getFirstName(), "!!"));
        label.setBounds(190, 20, 120, 30);
        panel.add(label);
        JButton acceptedButton = new JButton();
        acceptedButton.setText("Accepted res");
        acceptedButton.setBounds(30, 60, 120, 30);
        acceptedButton.addActionListener(e -> accepted());
        this.panel.add(acceptedButton);
        JButton rejectedButton = new JButton();
        rejectedButton.setText("Rejected res");
        rejectedButton.setBounds(180, 60, 120, 30);
        rejectedButton.addActionListener(e -> rejected());
        this.panel.add(rejectedButton);
        JButton checkListButton = new JButton();
        checkListButton.setText("checklist");
        checkListButton.setBounds(330, 60, 120, 30);
        checkListButton.addActionListener(e -> checklist());
        this.panel.add(checkListButton);
        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBounds(180, 100, 120, 30);
        logoutButton.addActionListener(e -> logout());
        this.panel.add(logoutButton);
    }

    private void accepted(){
        this.dispose();
        Frame current = Repository.getFrames().get("WorkerReservationGrid");
        if (!Objects.isNull(current))
            current.dispose();
        new WorkerReservationGrid(ReservationStatus.ACCEPTED_ENDED);
    }

    private void rejected(){
        this.dispose();
        Frame current = Repository.getFrames().get("WorkerReservationGrid");
        if (!Objects.isNull(current))
            current.dispose();
        new WorkerReservationGrid(ReservationStatus.REJECTED);
    }

    private void checklist(){
        this.dispose();
        Frame current = Repository.getFrames().get("CheckListGrid");
        if (!Objects.isNull(current))
            current.dispose();
        new CheckListGrid();
    }

    private void logout(){
        this.dispose();
        Repository.logout();
    }
}
