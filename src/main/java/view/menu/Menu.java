package view.menu;

import repo.Repository;

import javax.swing.*;

public class Menu extends JFrame {

    protected JPanel panel;

    public Menu(String title){
        super(title);
        JPanel panel = new JPanel();
        panel.setSize(600, 200);
        panel.setLayout(null);
//        JLabel label = new JLabel(String.join(" ", "welcome", Repository.currentPerson.getFirstName(), "!!"));
//        label.setBounds(190, 20, 120, 30);
//        panel.add(label);
        this.panel = panel;
        this.add(panel);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
