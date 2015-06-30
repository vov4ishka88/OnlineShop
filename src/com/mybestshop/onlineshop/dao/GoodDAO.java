package com.mybestshop.onlineshop.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mybestshop.onlineshop.entities.Good;

import java.sql.SQLException;

/**
 * Created by Vlad on 6/11/2015.
 */
public class GoodDAO implements Constants{

    public static Dao<Good, Integer> getDAO(){
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(CONNECTION);
            TableUtils.createTableIfNotExists(connectionSource, Good.class);
            return DaoManager.createDao(connectionSource, Good.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
