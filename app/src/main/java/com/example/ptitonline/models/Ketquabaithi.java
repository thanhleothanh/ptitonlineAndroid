package com.example.ptitonline.models;

public class Ketquabaithi {
    private int diem;
    private String thoigianlambai;
    private int tblbaithiid;
    private int tblsinhvienid;
    private Baithi baithi;

    public Ketquabaithi(int diem, String thoigianlambai, int tblbaithiid, int tblsinhvienid, Baithi baithi) {
        this.diem = diem;
        this.thoigianlambai = thoigianlambai;
        this.tblbaithiid = tblbaithiid;
        this.tblsinhvienid = tblsinhvienid;
        this.baithi = baithi;
    }

    public Ketquabaithi() {
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getThoigianlambai() {
        return thoigianlambai;
    }

    public void setThoigianlambai(String thoigianlambai) {
        this.thoigianlambai = thoigianlambai;
    }

    public int getTblbaithiid() {
        return tblbaithiid;
    }

    public void setTblbaithiid(int tblbaithiid) {
        this.tblbaithiid = tblbaithiid;
    }

    public int getTblsinhvienid() {
        return tblsinhvienid;
    }

    public void setTblsinhvienid(int tblsinhvienid) {
        this.tblsinhvienid = tblsinhvienid;
    }

    public Baithi getBaithi() {
        return baithi;
    }

    public void setBaithi(Baithi baithi) {
        this.baithi = baithi;
    }

    @Override
    public String toString() {
        return "Ketquabaithi{" +
                "diem=" + diem +
                ", thoigianlambai='" + thoigianlambai + '\'' +
                ", tblbaithiid=" + tblbaithiid +
                ", tblsinhvienid=" + tblsinhvienid +
                ", baithi=" + baithi +
                '}';
    }
}
