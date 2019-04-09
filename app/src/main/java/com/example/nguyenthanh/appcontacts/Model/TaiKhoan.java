package com.example.nguyenthanh.appcontacts.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaiKhoan {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("sdt")
    private String sdt;
    @SerializedName("hoten")
    private String hoten;
    @SerializedName("data")
    private List<DanhBa> danhBas;

    public List<DanhBa> getDanhBas() {
        return danhBas;
    }

    public void setDanhBas(List<DanhBa> danhBas) {
        this.danhBas = danhBas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
