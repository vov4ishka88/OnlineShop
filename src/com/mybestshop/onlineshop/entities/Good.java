package com.mybestshop.onlineshop.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vlad on 6/1/2015.
 */
@DatabaseTable
public class Good {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String name;
    @DatabaseField
    int price;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    TypeOfGoods type;

    public Good() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TypeOfGoods getType() {
        return type;
    }

    public void setType(TypeOfGoods type) {
        this.type = type;
    }

    // we overwrote this method so when we display ITEM in ListOfGoods it can be shown as name and not as
    // default value of toString which isn't informative.
    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
