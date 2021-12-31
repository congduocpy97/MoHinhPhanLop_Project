package DTO;

public class LoaiChiPhiDTO {

    private int maloaichiphi;
    private String tenloaichiphi;

    public LoaiChiPhiDTO() {
    }

    public LoaiChiPhiDTO(int maloaichiphi) {
        this.maloaichiphi = maloaichiphi;
    }

    public int getMaloaichiphi() {
        return maloaichiphi;
    }

    public void setMaloaichiphi(int maloaichiphi) {
        this.maloaichiphi = maloaichiphi;
    }

    public String getTenloaichiphi() {
        return tenloaichiphi;
    }

    public void setTenloaichiphi(String tenloaichiphi) {
        this.tenloaichiphi = tenloaichiphi;
    }

}
