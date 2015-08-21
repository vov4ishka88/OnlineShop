package com.mybestshop.onlineshop.dao;

/**
 * Created by Vlad on 6/4/2015.
 */
public interface Constants {
    // jdbc - java database connection (driver of the DB for java)
    // jdbc responsible for handling flow of adding and editing information in DB, by converting SQL query
    // and placing it into exact place in DB in bytes format.
    public static final String CONNECTION = "jdbc:sqlite:shop.db3";
}
