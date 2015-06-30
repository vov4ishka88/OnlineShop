package com.mybestshop.onlineshop.gui;

import com.mybestshop.onlineshop.dao.GoodDAO;
import com.mybestshop.onlineshop.entities.Good;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vlad on 6/1/2015.
 */
public class ListOfGoods extends JFrame{
    private JTable table1;
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private JPanel myPanel;
    private DefaultTableModel tableModel;
    private BuyAndSell context;

    public ListOfGoods(BuyAndSell context){
        this.context = context;
        setContentPane(myPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("name");
        tableModel.addColumn("category");
        tableModel.addColumn("price");
        table1.setModel(tableModel);
        displayTable();
        setVisible(true);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    new EditGoods(ListOfGoods.this, (Good)tableModel.getValueAt(selectedRow, 1));
                } else {
                    JOptionPane.showMessageDialog(ListOfGoods.this, "Please chose some item for editing");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new EditGoods( ListOfGoods.this, new Good());

            }
        });

    }

    public void displayTable(){
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        try {
            List<Good>listOfGoods = GoodDAO.getDAO().queryForAll();
            for (Good item: listOfGoods){
                Object [] row = new Object[]{
                item.getId(),
                item,
                item.getType(),
                item.getPrice()
                };
                tableModel.addRow(row);
            }
            context.displayComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
