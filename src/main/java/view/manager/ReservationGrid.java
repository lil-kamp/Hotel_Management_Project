package view.manager;

import model.Reservation;
import service.ReservationService;
import view.Grid;
import view.menu.ManagerMenu;

import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ReservationGrid extends Grid {

    public ReservationGrid(){
        super("reservations");
        this.setName("ReservationGrid");
        this.addButton.setVisible(false);
        this.editButton.setVisible(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ManagerMenu();
            }
        });
        initTable();
    }

    private void initTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"date", "customer", "price", "worker", "days", "room", "status", " "};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        List<Reservation> list = ReservationService.getInstance().findAll();
        for (Reservation r : list) {
            String date = (r.getDate() != null) ? r.getDate().toString() : "";
            String customer = (r.getCustomer() != null) ? r.getCustomer().getNationalId().toString() : "";
            String price = (r.getPrice() != null) ? r.getPrice().toString() : "";
            String worker = (r.getWorker() != null) ? r.getWorker().getPersonCode() : "";
            String days = (r.getDays() != null) ? r.getDays().toString() : "";
            String room = (r.getRoom() != null) ? r.getRoom().getId().toString() : "";
            String status = (r.getStatus() != null) ? r.getStatus().toString() : "";
            long id = (r.getId() != null) ? r.getId() : 0L;
            dtm.addRow(new Object[] { date, customer, price, worker, days, room, status, id});
        }

        table.getColumnModel().getColumn(7).setMinWidth(0);
        table.getColumnModel().getColumn(7).setMaxWidth(0);
    }
}
