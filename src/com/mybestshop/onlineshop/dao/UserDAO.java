package com.mybestshop.onlineshop.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mybestshop.onlineshop.entities.User;

import java.sql.SQLException;

/**
 * Created by Vlad on 7/27/2015.
 */
public class UserDAO implements Constants{
    public static Dao<User, Integer> getDao(){
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(CONNECTION);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            return DaoManager.createDao(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
