package view.worker;

import model.Reservation;
import model.enums.ReservationStatus;
import service.ReservationService;
import view.Grid;
import view.menu.WorkerMenu;

import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.PriorityQueue;

public class CheckListGrid extends Grid {

    public CheckListGrid(){
        super("reservations");
        this.setName("CheckListGrid");
        this.addButton.setText("accept");
        this.editButton.setText("reject");
        this.addButton.addActionListener(e -> accept());
        this.editButton.addActionListener(e -> reject());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new WorkerMenu();
            }
        });
        initTable();
    }

    private void initTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"date", "customer", "price", "worker", "days", "room", "status", " "};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        PriorityQueue<Reservation> list = ReservationService.getInstance().findAllWaiting();
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

    private void accept(){
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 7);
        Reservation reservation = ReservationService.getInstance().get(value);
        if (reservation.getStatus().equals(ReservationStatus.WAITING_BEFORE_RESERVE)){
            reservation.setStatus(ReservationStatus.ACCEPTED_RESERVE);
        }else {
            reservation.setStatus(ReservationStatus.ACCEPTED_ENDED);
        }
        ReservationService.getInstance().save(reservation);

        this.dispose();
        new CheckListGrid();
    }

    private void reject(){
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 7);
        Reservation reservation = ReservationService.getInstance().get(value);
        reservation.setStatus(ReservationStatus.REJECTED);
        ReservationService.getInstance().save(reservation);

        this.dispose();
        new CheckListGrid();
    }
}
