package com.mybestshop.onlineshop.gui;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.mybestshop.onlineshop.dao.UserDAO;
import com.mybestshop.onlineshop.entities.Permissions;
import com.mybestshop.onlineshop.entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vlad on 7/27/2015.
 */
public class LoginPage extends JFrame {
    private JTextField nameField;
    private JTextField passwordField;
    private JButton LoginButton;
    private JButton signUpButton;
    private JPanel myPanel;

    public LoginPage() {
        setContentPane(myPanel);
        setSize(300, 200);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
        this.setTitle("Login - window");
        /*
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<User> listOfUser = UserDAO.getDao().queryForAll();
                    String name = nameField.getText();
                    String password = passwordField.getText();
                    User user = null;
                    for ( User usr: listOfUser){
                        if (name.equals(usr.getName()) && password.equals(usr.getPassword())){
                            user = usr;
                        }
                    }
                    if (user == null){
                        JOptionPane.showMessageDialog(LoginPage.this, "User wasn't found", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        if (user.gerEnumPermissions() == Permissions.Administrator)new BuyAndSell();
                        if (user.gerEnumPermissions() == Permissions.Employee)new BuyAndSell();
                        if (user.gerEnumPermissions() == Permissions.Customer)new BuyAndSell();

                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        }); */

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = passwordField.getText();
                try {
                    QueryBuilder<User, Integer> queryBuilder = UserDAO.getDao().queryBuilder();
                    queryBuilder.where().eq(User.COLUMN_NAME, name).and().eq(User.COLUMN_PASSWORD, password);
                    PreparedQuery<User> preparedQuery = queryBuilder.prepare();
                    List<User> listOfUsers = UserDAO.getDao().query(preparedQuery);
                    if (listOfUsers.size() == 0){
                        JOptionPane.showMessageDialog(LoginPage.this, "User wasn't found", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        User user = listOfUsers.get(0);
                        if (user.gerEnumPermissions() == Permissions.Administrator)new LeftOversInWareHouse();
                        if (user.gerEnumPermissions() == Permissions.Employee)new LeftOversInWareHouse();
                        if (user.gerEnumPermissions() == Permissions.Customer)new LeftOversInWareHouse();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
