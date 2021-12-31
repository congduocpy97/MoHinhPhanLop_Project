package DTO;

import java.util.Date;

public class GiaTourDTO {

    private int magia;
    private int matour;

    public int getMatour() {
        return matour;
    }

    public void setMatour(int matour) {
        this.matour = matour;
    }
    private int thanhtien;
    private String thoiGianbatdau;
    private String thoiGianketthuc;

    public GiaTourDTO() {
    }

    public GiaTourDTO(int magia) {
        this.magia = magia;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getThoiGianbatdau() {
        return thoiGianbatdau;
    }

    public void setThoiGianbatdau(String thoiGianbatdau) {
        this.thoiGianbatdau = thoiGianbatdau;
    }

    public String getThoiGianketthuc() {
        return thoiGianketthuc;
    }

    public void setThoiGianketthuc(String thoiGianketthuc) {
        this.thoiGianketthuc = thoiGianketthuc;
    }

    
    
}
