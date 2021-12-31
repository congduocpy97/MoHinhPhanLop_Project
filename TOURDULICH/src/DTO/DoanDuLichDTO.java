package DTO;

import java.util.Date;

public class DoanDuLichDTO{
    private int madoan;
    private int matour;
    private String ngaykhoihanh;
    private String ngayketthuc;
    private int doanhthu;
    private int maloaichiphi;

    public DoanDuLichDTO() {
    }

    public DoanDuLichDTO(int madoan) {
        this.madoan = madoan;
    }

    public int getMadoan() {
        return madoan;
    }

    public void setMadoan(int madoan) {
        this.madoan = madoan;
    }

    public int getMatour() {
        return matour;
    }

    public void setMatour(int matour) {
        this.matour = matour;
    }

    public String getNgaykhoihanh() {
        return ngaykhoihanh;
    }

    public void setNgaykhoihanh(String ngaykhoihanh) {
        this.ngaykhoihanh = ngaykhoihanh;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public int getMaloaichiphi() {
        return maloaichiphi;
    }

    public void setMaloaichiphi(int maloaichiphi) {
        this.maloaichiphi = maloaichiphi;
    }
    
}
