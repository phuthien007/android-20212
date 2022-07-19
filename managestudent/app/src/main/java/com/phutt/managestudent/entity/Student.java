package com.phutt.managestudent.entity;

import java.util.Date;

public class Student {
    private String mssv;
    private String hoten;
    private String email;
    private String ngaysinh;

    public Student() {
    }

    public Student(String mssv, String hoten, String email, String ngaysinh) {
        super();
        this.mssv = mssv;
        this.hoten = hoten;
        this.email = email;
        this.ngaysinh = ngaysinh;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
