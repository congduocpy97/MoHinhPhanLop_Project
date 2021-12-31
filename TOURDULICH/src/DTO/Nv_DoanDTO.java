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
public class Nv_DoanDTO {

    private int manhanvien;
    private int madoan;
    private String nhiemvu;

    public Nv_DoanDTO() {
    }

    public Nv_DoanDTO(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public int getMadoan() {
        return madoan;
    }

    public void setMadoan(int madoan) {
        this.madoan = madoan;
    }

    public String getNhiemvu() {
        return nhiemvu;
    }

    public void setNhiemvu(String nhiemvu) {
        this.nhiemvu = nhiemvu;
    }

}
