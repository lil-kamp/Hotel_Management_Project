package view.manager;

import model.Room;
import model.users.Worker;
import repo.Repository;
import service.RoomService;
import service.WorkerService;
import view.Base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class WorkersDialog extends Base {

    private JTextField firstNameTxt;
    private JTextField lastNameTxt;
    private JTextField emailTxt;
    private JTextField passwordTxt;
    private JTextField personCodeTxt;
    private JTextField salaryTxt;
    private JTextField balanceTxt;
    private JButton saveButton;
    private Worker current;

    public WorkersDialog(long id, boolean edit){
        super("workers");
        this.setName("WorkersDialog");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        personCodeTxt = new JTextField();
        JLabel personCodeLabel = new JLabel("code:");
        salaryTxt = new JTextField();
        JLabel salaryLabel = new JLabel("salary:");
        balanceTxt = new JTextField();
        JLabel balanceLabel = new JLabel("balance:");
        saveButton = new JButton("save");
        saveButton.addActionListener(e -> save());

        panel.add(firstNameLabel);
        panel.add(lastNameLabel);
        panel.add(emailLabel);
        panel.add(passwordLabel);
        panel.add(personCodeLabel);
        panel.add(salaryLabel);
        panel.add(balanceLabel);
        panel.add(firstNameTxt);
        panel.add(lastNameTxt);
        panel.add(emailTxt);
        panel.add(passwordTxt);
        panel.add(personCodeTxt);
        panel.add(salaryTxt);
        panel.add(balanceTxt);
        panel.add(saveButton);

        firstNameLabel.setBounds(20, 20, 70, 30);
        firstNameTxt.setBounds(90, 20, 100, 30);
        lastNameLabel.setBounds(270, 20, 80, 30);
        lastNameTxt.setBounds(350, 20, 100, 30);
        emailLabel.setBounds(20, 70, 70, 30);
        emailTxt.setBounds(90, 70, 100, 30);
        passwordLabel.setBounds(300, 70, 50, 30);
        passwordTxt.setBounds(350, 70, 100, 30);
        personCodeLabel.setBounds(20, 120, 70, 30);
        personCodeTxt.setBounds(90, 120, 100, 30);
        salaryLabel.setBounds(300, 120, 50, 30);
        salaryTxt.setBounds(350, 120, 100, 30);
        balanceLabel.setBounds(20, 170, 70, 30);
        balanceTxt.setBounds(90, 170, 100, 30);
        saveButton.setBounds(200, 210, 100, 30);

        panel.revalidate();
        panel.repaint();

        if (edit){
            current = WorkerService.getInstance().get(id);
            firstNameTxt.setText(current.getFirstName());
            lastNameTxt.setText(current.getLastName());
            emailTxt.setText(current.getEmail());
            passwordTxt.setText(current.getPassword());
            personCodeTxt.setText(current.getPersonCode());
            salaryTxt.setText(current.getSalary().toString());
            balanceTxt.setText(current.getBalance().toString());
        }else {
            current = new Worker();
            current.setId(0L);
        }
    }

    private void save(){
        current.setFirstName(firstNameTxt.getText());
        current.setLastName(lastNameTxt.getText());
        current.setEmail(emailTxt.getText());
        current.setPassword(passwordTxt.getText());
        current.setPersonCode(personCodeTxt.getText());
        current.setSalary(Double.valueOf(salaryTxt.getText()));
        current.setBalance(Double.valueOf(balanceTxt.getText()));
        WorkerService.getInstance().save(current);

        this.dispose();
        Frame current = Repository.getFrames().get("WorkersGrid");
        if (!Objects.isNull(current))
            current.dispose();
        new WorkersGrid();
    }
}
