/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Acer
 */
public class Khach_DoanDTO {

    private int makhachhang;
    private int madoan;

    public Khach_DoanDTO() {
    }

    public Khach_DoanDTO(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public int getMadoan() {
        return madoan;
    }

    public void setMadoan(int madoan) {
        this.madoan = madoan;
    }
    
}
