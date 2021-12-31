package DTO;

public class NhanVienDTO{

    private int manhanvien;
    private String tennhanvien;

    public NhanVienDTO(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public NhanVienDTO() {
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

}
