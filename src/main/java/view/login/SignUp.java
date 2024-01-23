package view.login;

import model.users.Customer;
import model.users.Manager;
import model.users.Worker;
import repo.Repository;
import service.CustomerService;
import service.ManagerService;
import service.WorkerService;
import view.Base;
import view.manager.WorkersGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class SignUp extends Base {

    private JTextField firstNameTxt;
    private JTextField lastNameTxt;
    private JTextField emailTxt;
    private JTextField passwordTxt;
    private JComboBox<String> type;
    private JButton saveButton;
    private JLabel messageLabel;

    public SignUp(){
        super("signUp");
        this.setName("SignUp");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Login();
            }
        });
        this.setSize(500, 300);
        this.panel.setSize(500,300);

        firstNameTxt = new JTextField();
        JLabel firstNameLabel = new JLabel("firstName:");
        lastNameTxt = new JTextField();
        JLabel lastNameLabel = new JLabel("lastName:");
        emailTxt = new JTextField();
        JLabel emailLabel = new JLabel("email:");
        passwordTxt = new JTextField();
        JLabel passwordLabel = new JLabel("pass:");
        type = new JComboBox<>();
        type.addItem("Worker");
        type.addItem("Customer");
        type.addItem("Manager");
        JLabel typeLabel = new JLabel("type:");
        messageLabel = new JLabel("email should be valid!!");
        saveButton = new JButton("save");
        saveButton.addActionListener(e -> save());

        panel.add(firstNameLabel);
        panel.add(lastNameLabel);
        panel.add(emailLabel);
        panel.add(passwordLabel);
        panel.add(typeLabel);
        panel.add(messageLabel);
        panel.add(firstNameTxt);
        panel.add(lastNameTxt);
        panel.add(emailTxt);
        panel.add(passwordTxt);
        panel.add(type);
        panel.add(saveButton);

        firstNameLabel.setBounds(20, 20, 70, 30);
        firstNameTxt.setBounds(90, 20, 100, 30);
        lastNameLabel.setBounds(270, 20, 80, 30);
        lastNameTxt.setBounds(350, 20, 100, 30);
        emailLabel.setBounds(20, 70, 70, 30);
        emailTxt.setBounds(90, 70, 100, 30);
        passwordLabel.setBounds(300, 70, 50, 30);
        passwordTxt.setBounds(350, 70, 100, 30);
        messageLabel.setBounds(20, 120, 300, 30);
        typeLabel.setBounds(300, 120, 50, 30);
        type.setBounds(350, 120, 100, 30);
        saveButton.setBounds(200, 210, 100, 30);

        panel.revalidate();
        panel.repaint();
    }

    private void save(){
        if (emailTxt.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            if (type.getSelectedItem().equals("Manager")) {
                Manager manager = new Manager();
                manager.setId(0L);
                manager.setFirstName(firstNameTxt.getText());
                manager.setLastName(lastNameTxt.getText());
                manager.setEmail(emailTxt.getText());
                manager.setPassword(passwordTxt.getText());
                ManagerService.getInstance().save(manager);
            } else if (type.getSelectedItem().equals("Worker")) {
                Worker worker = new Worker();
                worker.setId(0L);
                worker.setFirstName(firstNameTxt.getText());
                worker.setLastName(lastNameTxt.getText());
                worker.setEmail(emailTxt.getText());
                worker.setPassword(passwordTxt.getText());
                WorkerService.getInstance().save(worker);
            } else {
                Customer customer = new Customer();
                customer.setId(0L);
                customer.setFirstName(firstNameTxt.getText());
                customer.setLastName(lastNameTxt.getText());
                customer.setEmail(emailTxt.getText());
                customer.setPassword(passwordTxt.getText());
                CustomerService.getInstance().save(customer);
            }

            this.dispose();
            Frame current = Repository.getFrames().get("Login");
            if (!Objects.isNull(current))
                current.dispose();
            new Login();
        }else {
            messageLabel.setText("not a valid email");
        }
    }
}
