package com.mybestshop.onlineshop.gui;

import com.mybestshop.onlineshop.dao.TypeDAO;
import com.mybestshop.onlineshop.entities.TypeOfGoods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vlad on 6/4/2015.
 */
public class ListOfTypes extends JFrame {
    private JList list1;
    private JButton addTypeButton;
    private JButton removeButton;
    private JButton editButton;
    private JPanel myPanel;
    private DefaultListModel<TypeOfGoods> model = new DefaultListModel<TypeOfGoods>();
    private EditGoods parentForm;

    public ListOfTypes( EditGoods parentForm) {
        this.parentForm = parentForm;
        setContentPane(myPanel);
        pack();
        setLocationRelativeTo(null);
 //     setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        list1.setModel(model);
        displayList();

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TypeOfGoods type = (TypeOfGoods) list1.getSelectedValue();
                try {
                    TypeDAO.getDao().delete(type);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                displayList();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() >= 0) {
                    TypeOfGoods typeOfGoods = model.getElementAt(list1.getSelectedIndex());
                    new EditType(typeOfGoods, ListOfTypes.this);
                }
                else JOptionPane.showMessageDialog(ListOfTypes.this, "Please select item", "No item selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        addTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditType(new TypeOfGoods(), ListOfTypes.this);
            }
        });
    }

    public void displayList() {
     model.removeAllElements();
        List <TypeOfGoods> list = null;
        try {
            list = TypeDAO.getDao().queryForAll();
            for ( TypeOfGoods type: list){
                model.addElement(type);
            }
            parentForm.displayComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
