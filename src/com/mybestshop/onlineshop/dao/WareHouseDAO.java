package com.mybestshop.onlineshop.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mybestshop.onlineshop.entities.WareHouse;

import java.sql.SQLException;

/**
 * Created by Vlad on 6/15/2015.
 */
public class WareHouseDAO implements Constants{
    public static Dao<WareHouse, Integer> getDao(){
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(CONNECTION);
            TableUtils.createTableIfNotExists(connectionSource, WareHouse.class);
            return DaoManager.createDao(connectionSource, WareHouse.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
