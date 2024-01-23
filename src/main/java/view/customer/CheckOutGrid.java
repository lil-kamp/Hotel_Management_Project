package view.customer;

import model.Reservation;
import model.enums.ReservationStatus;
import model.users.Customer;
import repo.Repository;
import service.ReservationService;
import view.Grid;
import view.menu.CustomerMenu;
import view.menu.WorkerMenu;
import view.worker.CheckListGrid;

import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.PriorityQueue;

public class CheckOutGrid extends Grid {

    public CheckOutGrid(){
        super("reservations");
        this.setName("CheckOutGrid");
        this.editButton.setVisible(false);
        this.addButton.setText("check out");
        this.addButton.addActionListener(e -> checkout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new CustomerMenu();
            }
        });
        initTable();
    }

    private void initTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"date", "customer", "price", "worker", "days", "room", "status", " "};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        PriorityQueue<Reservation> list = ReservationService.getInstance().findAllAcceptedByCustomer((Customer) Repository.currentPerson);
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

    private void checkout(){
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 7);
        Reservation reservation = ReservationService.getInstance().get(value);
        reservation.setStatus(ReservationStatus.WAITING_ENDED);
        ReservationService.getInstance().save(reservation);

        this.dispose();
        new CheckListGrid();
    }
}
