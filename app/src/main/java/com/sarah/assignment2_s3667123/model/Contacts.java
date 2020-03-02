package com.sarah.assignment2_s3667123.model;

import java.io.Serializable;

public class Contacts implements Serializable {
// class to generate Contacts
    private String name;
    private String phone;
    private boolean isSelected;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;

    }
}
