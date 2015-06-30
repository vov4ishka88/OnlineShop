package com.mybestshop.onlineshop.gui;

import com.mybestshop.onlineshop.dao.GoodDAO;
import com.mybestshop.onlineshop.dao.WareHouseDAO;
import com.mybestshop.onlineshop.entities.Good;
import com.mybestshop.onlineshop.entities.WareHouse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vlad on 6/25/2015.
 */
public class BuyAndSell extends JFrame{
    private JComboBox<Good> comboBox1;
    private JTextField textAmount;
    private JTextField textPrice;
    private JButton addGoodButton;
    private JButton buyButton;
    private JButton sellButton;
    private JPanel myPanel;
    private DefaultComboBoxModel<Good> model = new DefaultComboBoxModel<Good>();

    public BuyAndSell(){
        setContentPane(myPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        comboBox1.setModel(model);
        displayComboBox();
        addGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListOfGoods(BuyAndSell.this);
            }
        });
        ActionListener buyAndSellListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WareHouse transaction = new WareHouse();
                transaction.setGood((Good) comboBox1.getSelectedItem());
                if (((JButton)e.getSource()) == buyButton)transaction.setAmount(Integer.parseInt(textAmount.getText()));
                else transaction.setAmount(-1 * Integer.parseInt(textAmount.getText()));
                transaction.setPrice(Integer.parseInt(textPrice.getText()));
                try {
                    WareHouseDAO.getDao().createOrUpdate(transaction);
                    JOptionPane.showMessageDialog(BuyAndSell.this,
                            "Transaction completed successfully",
                            "Successful operation",
                            JOptionPane.INFORMATION_MESSAGE);
                    textAmount.setText("");
                    textPrice.setText("");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        };

        buyButton.addActionListener(buyAndSellListener);
        sellButton.addActionListener(buyAndSellListener);
    }

    public void displayComboBox(){
        model.removeAllElements();
        try {
            List<Good> listOfGoods = GoodDAO.getDAO().queryForAll();
            for (Good good: listOfGoods){
                model.addElement(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
