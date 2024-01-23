package view.customer;

import model.Reservation;
import model.Room;
import model.enums.ReservationStatus;
import model.users.Customer;
import repo.Repository;
import service.ReservationService;
import service.RoomService;
import view.Base;
import view.Grid;
import view.manager.RoomsGrid;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class CustomerRoomsDialog extends Base {

    private JTextField daysTxt;
    private JButton saveButton;
    private Room current;

    public CustomerRoomsDialog(long id){
        super("reserve");
        this.setName("CustomerRoomsDialog");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        daysTxt = new JTextField();
        JLabel daysLabel = new JLabel("days:");
        saveButton = new JButton("save");
        saveButton.addActionListener(e -> save());

        panel.add(daysLabel);
        panel.add(daysTxt);
        panel.add(saveButton);

        daysLabel.setBounds(20, 20, 70, 30);
        daysTxt.setBounds(90, 20, 100, 30);
        saveButton.setBounds(200, 110, 100, 30);

        panel.revalidate();
        panel.repaint();

        current = RoomService.getInstance().get(id);
    }

    private void save(){
        Reservation reservation = new Reservation(0L, new Date(), (Customer) Repository.currentPerson, current.getPrice(),
                Integer.valueOf(daysTxt.getText()), current, ReservationStatus.WAITING_BEFORE_RESERVE);
        current.setAllowed(false);
        RoomService.getInstance().save(current);
        ReservationService.getInstance().save(reservation);

        this.dispose();
        Frame current = Repository.getFrames().get("CustomerRoomsGrid");
        if (!Objects.isNull(current))
            current.dispose();
        new CustomerRoomsGrid();
    }
}
