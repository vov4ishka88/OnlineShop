package com.mybestshop.onlineshop.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vlad on 6/1/2015.
 */
@DatabaseTable
public class TypeOfGoods {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String category;

    public TypeOfGoods() {
    }

    public TypeOfGoods(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
