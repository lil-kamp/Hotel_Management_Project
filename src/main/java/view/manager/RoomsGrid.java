package view.manager;

import model.Room;
import repo.Repository;
import service.RoomService;
import view.Grid;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class RoomsGrid extends Grid {

    public RoomsGrid(){
        super("rooms");
        this.setName("RoomsGrid");
        initTable();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });
    }

    private void initTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"bedCount", "price", "allowed", " "};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        List<Room> list = RoomService.getInstance().findAll();
        for (Room r : list) {
            String bedCount = (r.getBedCount() != null) ? r.getBedCount().toString() : "";
            String price = (r.getPrice() != null) ? r.getPrice().toString() : "";
            String allowed = (r.getAllowed() != null) ? r.getAllowed().toString() : "";
            long id = (r.getId() != null) ? r.getId() : 0L;
            dtm.addRow(new Object[] { bedCount, price, allowed, id});
        }

        table.getColumnModel().getColumn(3).setMinWidth(0);
        table.getColumnModel().getColumn(3).setMaxWidth(0);
    }

    private void edit(){
        Frame current = Repository.getFrames().get("RoomsDialog");
        if (!Objects.isNull(current))
            current.dispose();
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 3);
        new RoomsDialog(value , true);
    }

    private void add(){
        new RoomsDialog(0, false);
    }
}
