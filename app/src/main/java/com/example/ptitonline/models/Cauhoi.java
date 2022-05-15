package com.example.ptitonline.models;

public class Cauhoi {
    private int ID;
    private String cauhoi;
    private String cautraloidung;
    private String cautraloisai1;
    private String cautraloisai2;
    private String cautraloisai3;
    private String hinhanh;
    private int tblmonhocid;
    private Monhoc monhoc;

    @Override
    public String toString() {
        return "Cauhoi{" +
                "ID=" + ID +
                ", cauhoi='" + cauhoi + '\'' +
                ", cautraloidung='" + cautraloidung + '\'' +
                ", cautraloisai1='" + cautraloisai1 + '\'' +
                ", cautraloisai2='" + cautraloisai2 + '\'' +
                ", cautraloisai3='" + cautraloisai3 + '\'' +
                ", hinhanh='" + hinhanh + '\'' +
                ", tblmonhocid=" + tblmonhocid +
                ", monhoc=" + monhoc +
                '}';
    }

    public int getID() {
        return ID;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getCautraloidung() {
        return cautraloidung;
    }

    public void setCautraloidung(String cautraloidung) {
        this.cautraloidung = cautraloidung;
    }

    public String getCautraloisai1() {
        return cautraloisai1;
    }

    public void setCautraloisai1(String cautraloisai1) {
        this.cautraloisai1 = cautraloisai1;
    }

    public String getCautraloisai2() {
        return cautraloisai2;
    }

    public void setCautraloisai2(String cautraloisai2) {
        this.cautraloisai2 = cautraloisai2;
    }

    public String getCautraloisai3() {
        return cautraloisai3;
    }

    public void setCautraloisai3(String cautraloisai3) {
        this.cautraloisai3 = cautraloisai3;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getTblmonhocid() {
        return tblmonhocid;
    }

    public void setTblmonhocid(int tblmonhocid) {
        this.tblmonhocid = tblmonhocid;
    }

    public Monhoc getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(Monhoc monhoc) {
        this.monhoc = monhoc;
    }


}
