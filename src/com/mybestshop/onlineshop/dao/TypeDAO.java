package com.mybestshop.onlineshop.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mybestshop.onlineshop.entities.TypeOfGoods;

import java.sql.SQLException;

/**
 * Created by Vlad on 6/4/2015.
 */
public class TypeDAO implements Constants{
    public static Dao<TypeOfGoods, Integer> getDao(){
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(CONNECTION);
            TableUtils.createTableIfNotExists(connectionSource, TypeOfGoods.class);
            return DaoManager.createDao(connectionSource, TypeOfGoods.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
