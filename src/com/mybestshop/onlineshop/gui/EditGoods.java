package com.mybestshop.onlineshop.gui;

import com.mybestshop.onlineshop.dao.GoodDAO;
import com.mybestshop.onlineshop.dao.TypeDAO;
import com.mybestshop.onlineshop.entities.Good;
import com.mybestshop.onlineshop.entities.TypeOfGoods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vlad on 6/11/2015.
 */
public class EditGoods extends JFrame{
    private JTextField nameField;
    private JComboBox <TypeOfGoods> comboBox1;
    private JTextField priceField;
    private JButton submitButton;
    private JButton addTypeButton;
    private JButton cancelButton;
    private JPanel myPanel;
    DefaultComboBoxModel<TypeOfGoods> model = new DefaultComboBoxModel<TypeOfGoods>();

    public EditGoods(final ListOfGoods parentForm, final Good good){
        setContentPane(myPanel);
        setVisible(true);
        setLocationRelativeTo(null);
        comboBox1.setModel(model);
        nameField.setText(good.getName());
        priceField.setText(String.valueOf(good.getPrice()));
        displayComboBox();
        pack();
        this.setTitle("EditGoods - window");
        addTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListOfTypes( EditGoods.this );
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                good.setName(nameField.getText());
                // comboBox1 object is the same way as ITEM in ListOfGoods holds the whole object (object in this case is whole list
                // of TypeOfGoods not ONLY one type of Good) rather then only NAME
                good.setType((TypeOfGoods) comboBox1.getSelectedItem());
                good.setPrice(Integer.parseInt(priceField.getText()));
                try {
                    GoodDAO.getDAO().createOrUpdate(good);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                // we transfered context to this class to be able to execute line below
                // it can be executed only if class have link to the parent window.
                parentForm.displayTable();
                dispose();
            }
        });
    }

    public void displayComboBox(){
        model.removeAllElements();
        try {
            List<TypeOfGoods> list = TypeDAO.getDao().queryForAll();
            for ( TypeOfGoods item: list){
                model.addElement(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
