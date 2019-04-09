package com.example.nguyenthanh.appcontacts.Model;

import com.google.gson.annotations.SerializedName;

public class DanhBa implements Comparable<DanhBa>{
    private int id;
    @SerializedName("ten")
    private String ten="";
    @SerializedName("sdt")
    private String sdt="";

    private String nhom="";
    private boolean yeuthich=false;

    public DanhBa() {
    }

    public DanhBa(String ten, String sdt, String nhom, boolean yeuthich) {
        this.ten = ten;
        this.sdt = sdt;
        this.nhom = nhom;
        this.yeuthich = yeuthich;
    }

    public DanhBa(int id, String ten, String sdt, String nhom, boolean yeuthich) {

        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.nhom = nhom;
        this.yeuthich = yeuthich;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public boolean isYeuthich() {
        return yeuthich;
    }

    public void setYeuthich(boolean yeuthich) {
        this.yeuthich = yeuthich;
    }

    @Override
    public int compareTo(DanhBa o) {
        return this.getTen().compareToIgnoreCase(o.getTen());
    }
}
