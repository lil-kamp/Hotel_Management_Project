package view;

import javax.swing.*;

public class Grid extends Base{

    protected JScrollPane scrollPane;
    protected JTable table;
    protected JButton addButton;
    protected JButton editButton;

    public Grid(String title){
        super(title);
        this.setSize(650, 350);
        panel.setSize(650, 350);

        table = new JTable();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        panel.add(scrollPane);
        scrollPane.setBounds(17, 10, 600, 215);

        addButton = new JButton();
        addButton.setText("add");
        addButton.setBounds(17, 230, 150, 30);
        this.panel.add(addButton);
        editButton = new JButton();
        editButton.setText("edit");
        editButton.setBounds(200, 230, 150, 30);
        this.panel.add(editButton);

        panel.revalidate();
        panel.repaint();
    }
}
