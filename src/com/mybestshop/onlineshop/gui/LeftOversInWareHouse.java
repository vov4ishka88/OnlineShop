package com.mybestshop.onlineshop.gui;

import com.mybestshop.onlineshop.dao.GoodDAO;
import com.mybestshop.onlineshop.dao.WareHouseDAO;
import com.mybestshop.onlineshop.entities.Good;
import com.mybestshop.onlineshop.entities.WareHouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vlad on 8/10/2015.
 */
public class LeftOversInWareHouse extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton buyButton;
    DefaultTableModel tableModel = new DefaultTableModel();

    public LeftOversInWareHouse(){
        setContentPane(panel1);
        setSize(800, 600);
        setLocationRelativeTo(null);

        this.setTitle("LeftOversInWareHouse - window");
        table1.setModel(tableModel);
        tableModel.addColumn("Id");
        tableModel.addColumn("Good");
        tableModel.addColumn("Type");
        tableModel.addColumn("LeftOvers");
        tableModel.addColumn("Price");
        TableColumn column = table1.getColumn("Id");
        column.setPreferredWidth(20);
        column.setMaxWidth(20);
        column.setMinWidth(20);
        displayTable();
        setVisible(true);

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyAndSell(LeftOversInWareHouse.this);
            }
        });
    }

    public void displayTable(){
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        try {
            List<Good> listOfGood = GoodDAO.getDAO().queryForAll();
            List<WareHouse> listOfWareHouse = WareHouseDAO.getDao().queryForAll();
            int amount;
            for(Good item: listOfGood){
                amount = 0;
                for (WareHouse record: listOfWareHouse){
                    if (record.getGood().getId() == item.getId()){
                        amount += record.getAmount();
                    }
                }
                Object [] row = new Object[] {
                        item.getId(),
                        item.getName(),
                        item.getType().getCategory(),
                        amount,
                        item.getPrice()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
