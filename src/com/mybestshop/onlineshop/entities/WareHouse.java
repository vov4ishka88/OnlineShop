package com.mybestshop.onlineshop.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vlad on 6/15/2015.
 */
@DatabaseTable
public class WareHouse {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Good good;
    @DatabaseField
    int amount;
    @DatabaseField
    int price;

    public WareHouse(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
