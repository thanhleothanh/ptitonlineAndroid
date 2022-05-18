package com.example.ptitonline.models;

public class GiaovienBaithi {
    private int ID;
    private String hoten;

    public GiaovienBaithi() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public GiaovienBaithi(int ID, String hoten) {
        this.ID = ID;
        this.hoten = hoten;
    }

    @Override
    public String toString() {
        return "GiaovienBaithi{" +
                "ID=" + ID +
                ", hoten='" + hoten + '\'' +
                '}';
    }
}
