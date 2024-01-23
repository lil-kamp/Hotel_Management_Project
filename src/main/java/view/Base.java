package view;

import javax.swing.*;

public class Base extends JFrame {

    protected JPanel panel;

    public Base(String title){
        super(title);
        JPanel panel = new JPanel();
        panel.setSize(500, 200);
        panel.setLayout(null);
        this.panel = panel;
        this.add(panel);
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
