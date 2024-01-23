package view.worker;

import model.Reservation;
import model.enums.ReservationStatus;
import service.ReservationService;
import view.Grid;
import view.menu.ManagerMenu;
import view.menu.WorkerMenu;

import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.PriorityQueue;

public class WorkerReservationGrid extends Grid {

    public WorkerReservationGrid(ReservationStatus status){
        super("reservations");
        this.setName("WorkerReservationGrid");
        this.addButton.setVisible(false);
        this.editButton.setVisible(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new WorkerMenu();
            }
        });
        initTable(status);
    }

    private void initTable(ReservationStatus reservationStatus){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"date", "customer", "price", "worker", "days", "room", "status", " "};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        PriorityQueue<Reservation> list = ReservationService.getInstance().findAllByStatus(reservationStatus);
        for (Reservation r : list) {
            String date = (r.getDate() != null) ? r.getDate().toString() : "";
            String customer = (r.getCustomer() != null) ? r.getCustomer().getNationalId() : "";
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
