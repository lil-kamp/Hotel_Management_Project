package view.customer;

import model.Reservation;
import model.Room;
import repo.Repository;
import service.RoomService;
import view.Grid;
import view.menu.CustomerMenu;
import view.menu.ManagerMenu;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CustomerRoomsGrid extends Grid {

    public CustomerRoomsGrid(){
        super("rooms");
        this.setName("CustomerRoomsGrid");
        this.addButton.setText("reserve");
        this.editButton.setVisible(false);
        initTable();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new CustomerMenu();
            }
        });
        addButton.addActionListener(e -> reserve());
    }

    private void initTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"bedCount", "price", "allowed", "no"};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        List<Room> list = RoomService.getInstance().findAllAllowed();
        for (Room r : list) {
            String bedCount = (r.getBedCount() != null) ? r.getBedCount().toString() : "";
            String price = (r.getPrice() != null) ? r.getPrice().toString() : "";
            String allowed = (r.getAllowed() != null) ? r.getAllowed().toString() : "";
            long id = (r.getId() != null) ? r.getId() : 0L;
            dtm.addRow(new Object[] { bedCount, price, allowed, id});
        }
    }

    private void reserve(){
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 3);
        Frame current = Repository.getFrames().get("CustomerRoomsDialog");
        if (!Objects.isNull(current))
            current.dispose();
        new CustomerRoomsDialog(value);
    }
}
