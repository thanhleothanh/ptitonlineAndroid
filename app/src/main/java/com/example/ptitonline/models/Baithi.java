package com.example.ptitonline.models;

public class Baithi {
    private int ID;
    private String tenbaithi, mota, trangthaiActive;
    private GiaovienBaithi giaovien;
    private Monhoc monhoc;

    public Baithi() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenbaithi() {
        return tenbaithi;
    }

    public void setTenbaithi(String tenbaithi) {
        this.tenbaithi = tenbaithi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTrangthaiActive() {
        return trangthaiActive;
    }

    public void setTrangthaiActive(String trangthaiActive) {
        this.trangthaiActive = trangthaiActive;
    }

    public GiaovienBaithi getGiaovien() {
        return giaovien;
    }

    public void setGiaovien(GiaovienBaithi giaovien) {
        this.giaovien = giaovien;
    }

    public Monhoc getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(Monhoc monhoc) {
        this.monhoc = monhoc;
    }

    public Baithi(int ID, String tenbaithi, String mota, String trangthaiActive, GiaovienBaithi giaovien, Monhoc monhoc) {
        this.ID = ID;
        this.tenbaithi = tenbaithi;
        this.mota = mota;
        this.trangthaiActive = trangthaiActive;
        this.giaovien = giaovien;
        this.monhoc = monhoc;
    }

    @Override
    public String toString() {
        return "Baithi{" +
                "ID=" + ID +
                ", tenbaithi='" + tenbaithi + '\'' +
                ", mota='" + mota + '\'' +
                ", trangthaiActive='" + trangthaiActive + '\'' +
                ", giaovien=" + giaovien.toString() +
                ", monhoc=" + monhoc.toString() +
                '}';
    }
}
