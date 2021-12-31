package DTO;

import java.util.Date;

public class TourDuLichDTO {
    private int matour;
    private String tengoi;
    private int maloaihinh;
    private int madiadiem;

    public TourDuLichDTO() {
    }

    public TourDuLichDTO(int matour) {
        this.matour = matour;
    }

    public int getMatour() {
        return matour;
    }

    public void setMatour(int matour) {
        this.matour = matour;
    }

    public String getTengoi() {
        return tengoi;
    }

    public void setTengoi(String tengoi) {
        this.tengoi = tengoi;
    }

    public int getMaloaihinh() {
        return maloaihinh;
    }

    public void setMaloaihinh(int maloaihinh) {
        this.maloaihinh = maloaihinh;
    }

    public int getMadiadiem() {
        return madiadiem;
    }

    public void setMadiadiem(int madiadiem) {
        this.madiadiem = madiadiem;
    }

    
}
