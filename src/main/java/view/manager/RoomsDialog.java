package view.manager;

import model.Room;
import repo.Repository;
import service.HotelService;
import service.RoomService;
import view.Base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RoomsDialog extends Base {

    private JTextField bedCountTxt;
    private JTextField priceTxt;
    private JCheckBox allowedCheck;
    private JButton saveButton;
    private Room current;

    public RoomsDialog(long id, boolean edit){
        super("rooms");
        this.setName("RoomsDialog");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bedCountTxt = new JTextField();
        JLabel bedCountLabel = new JLabel("bedCount:");
        priceTxt = new JTextField();
        JLabel priceLabel = new JLabel("price:");
        allowedCheck = new JCheckBox();
        JLabel allowedLabel = new JLabel("allowed:");
        saveButton = new JButton("save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        panel.add(bedCountLabel);
        panel.add(bedCountTxt);
        panel.add(priceLabel);
        panel.add(priceTxt);
        panel.add(allowedLabel);
        panel.add(allowedCheck);
        panel.add(saveButton);

        bedCountLabel.setBounds(20, 20, 70, 30);
        bedCountTxt.setBounds(90, 20, 100, 30);
        priceLabel.setBounds(300, 20, 50, 30);
        priceTxt.setBounds(350, 20, 100, 30);
        allowedLabel.setBounds(200, 60, 50, 30);
        allowedCheck.setBounds(250, 60, 50, 30);
        saveButton.setBounds(200, 110, 100, 30);


        panel.revalidate();
        panel.repaint();

        if (edit){
            current = RoomService.getInstance().get(id);
            bedCountTxt.setText(current.getBedCount().toString());
            priceTxt.setText(current.getPrice().toString());
            allowedCheck.setSelected(current.getAllowed());
        }else {
            current = new Room();
            current.setId(0L);
        }
    }

    private void save(){
        current.setBedCount(Integer.valueOf(bedCountTxt.getText()));
        current.setPrice(Double.valueOf(priceTxt.getText()));
        current.setHotel(HotelService.getInstance().find());
        current.setAllowed(allowedCheck.isSelected());

        RoomService.getInstance().save(current);
        this.dispose();
        Frame current = Repository.getFrames().get("RoomsGrid");
        if (!Objects.isNull(current))
            current.dispose();
        new RoomsGrid();
    }
}
