package DTO;

public class LoaiHinhDuLichDTO {
    private int maloaihinh;
    private String tenloaihinh;

    public LoaiHinhDuLichDTO(int maloaihinh) {
        this.maloaihinh = maloaihinh;
    }

    public LoaiHinhDuLichDTO() {
    }

    public int getMaloaihinh() {
        return maloaihinh;
    }

    public void setMaloaihinh(int maloaihinh) {
        this.maloaihinh = maloaihinh;
    }

    public String getTenloaihinh() {
        return tenloaihinh;
    }

    public void setTenloaihinh(String tenloaihinh) {
        this.tenloaihinh = tenloaihinh;
    }


}
