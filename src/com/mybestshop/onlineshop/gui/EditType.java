package com.mybestshop.onlineshop.gui;

import com.mybestshop.onlineshop.dao.TypeDAO;
import com.mybestshop.onlineshop.entities.TypeOfGoods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Vlad on 6/4/2015.
 */
public class EditType extends JFrame{
    private JTextField textField1;
    private JButton saveChagesButton;
    private JPanel myPanel;

    public EditType(final TypeOfGoods typeOfGoods, final ListOfTypes context){
        setTitle("Editing type of goods");
        setContentPane(myPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        textField1.setText(typeOfGoods.getCategory());
           saveChagesButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   TypeOfGoods type = typeOfGoods;
                   type.setCategory(textField1.getText());
                   try {
                       TypeDAO.getDao().createOrUpdate(type);
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                   }
                   context.displayList();
                   dispose();
               }
           });
    }
}
