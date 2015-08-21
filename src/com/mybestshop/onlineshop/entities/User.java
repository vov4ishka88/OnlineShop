package com.mybestshop.onlineshop.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vlad on 7/27/2015.
 */
@DatabaseTable
public class User {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField (columnName = COLUMN_NAME)
    String name;
    @DatabaseField (columnName = COLUMN_PASSWORD)
    String password;
    @DatabaseField
    int permissions;
    // use of the Constant prevent as from errors that can arise in case when we change column/veriable name
    // but in QueryBuilder this colunm/veriable wasn't changeda/updated. Thus we remove connection between
    // them.
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public void setEnumPermissions(Permissions permissions){
        if(permissions == Permissions.Administrator) this.permissions = 0;
        if (permissions == Permissions.Employee) this.permissions = 50;
        if (permissions == Permissions.Customer) this.permissions = 100;
    }

    public Permissions gerEnumPermissions(){
        if (permissions == 0) return Permissions.Administrator;
        if (permissions == 50) return Permissions.Employee;
        if (permissions == 100) return Permissions.Customer;
        return null;
    }
}
