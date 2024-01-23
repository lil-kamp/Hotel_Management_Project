package view.manager;

import model.Hotel;
import model.users.Worker;
import repo.Repository;
import service.HotelService;
import service.WorkerService;
import view.Grid;
import view.menu.ManagerMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Objects;

public class WorkersGrid extends Grid {

    public WorkersGrid(){
        super("workers");
        this.setName("WorkersGrid");
        initTable();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ManagerMenu();
            }
        });
        addButton.addActionListener(e -> add());
        editButton.addActionListener(e -> edit());

        JButton fireButton = new JButton();
        fireButton.setText("fire");
        fireButton.setBounds(360, 230, 100, 30);
        this.panel.add(fireButton);
        JButton payButton = new JButton();
        payButton.setText("pay");
        payButton.setBounds(470, 230, 100, 30);
        this.panel.add(payButton);

        fireButton.addActionListener(e -> fire());
        payButton.addActionListener(e -> pay());

        panel.revalidate();
        panel.repaint();
    }

    private void initTable(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String[] header = new String[] {"firstName", "lastName", "email", "password", "personCode", "salary", "balance", " "};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        List<Worker> list = WorkerService.getInstance().findAll();
        for (Worker w : list) {
            String firstName = w.getFirstName();
            String lastName = w.getLastName();
            String email = w.getEmail();
            String password = w.getPassword();
            String personCode = w.getPersonCode();
            String salary = (w.getSalary() != null) ? w.getSalary().toString() : "";
            String balance = (w.getBalance() != null) ? w.getBalance().toString() : "";
            long id = (w.getId() != null) ? w.getId() : 0L;
            dtm.addRow(new Object[] { firstName, lastName, email, password, personCode, salary, balance, id});
        }

        table.getColumnModel().getColumn(7).setMinWidth(0);
        table.getColumnModel().getColumn(7).setMaxWidth(0);
    }

    private void edit(){
        Frame current = Repository.getFrames().get("WorkersDialog");
        if (!Objects.isNull(current))
            current.dispose();
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 7);
        new WorkersDialog(value , true);
    }

    private void add(){
        new WorkersDialog(0, false);
    }

    private void fire(){
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 7);
        WorkerService.getInstance().delete(WorkerService.getInstance().get(value));

        this.dispose();
        new WorkersGrid();
    }

    private void pay(){
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        Long value = (Long) table.getModel().getValueAt(row, 7);
        Worker worker = WorkerService.getInstance().get(value);
        Hotel hotel = HotelService.getInstance().find();
        hotel.setBalance(hotel.getBalance() - worker.getSalary());
        HotelService.getInstance().save(hotel);
        worker.setBalance(worker.getSalary() + worker.getBalance());
        WorkerService.getInstance().save(worker);

        this.dispose();
        new WorkersGrid();
    }
}
