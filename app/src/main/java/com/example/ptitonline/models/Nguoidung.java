package com.example.ptitonline.models;

public class Nguoidung {
    private int ID;
    private String username;
    private String password;
    private String hoten;
    private String diachi;
    private String ngaysinh;
    private String email;
    private String dienthoai;
    private String vaitro;
    private int diemluyentap;

    @Override
    public String toString() {
        return "Nguoidung{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hoten='" + hoten + '\'' +
                ", diachi='" + diachi + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", email='" + email + '\'' +
                ", dienthoai='" + dienthoai + '\'' +
                ", vaitro='" + vaitro + '\'' +
                ", diemluyentap=" + diemluyentap +
                '}';
    }

    public Nguoidung() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public int getDiemluyentap() {
        return diemluyentap;
    }

    public void setDiemluyentap(int diemluyentap) {
        this.diemluyentap = diemluyentap;
    }


}

