package com.example.ptitonline.models;

public class Monhoc {
    private int ID;
    private String tenmon;

    @Override
    public String toString() {
        return "Monhoc{" +
                "ID=" + ID +
                ", tenmon='" + tenmon + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

}
