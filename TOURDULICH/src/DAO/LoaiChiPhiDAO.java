/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.LoaiChiPhiDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;

/**
 *
 * @author ChiThien
 */
public class LoaiChiPhiDAO {

    public static ArrayList<LoaiChiPhiDTO> load() {
        ArrayList<LoaiChiPhiDTO> l_loaichiphi = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM loaichiphi");

        try {
            while (rs.next()) {
                LoaiChiPhiDTO lchiphi = new LoaiChiPhiDTO(rs.getInt(1));
                lchiphi.setTenloaichiphi(rs.getString(2));
                l_loaichiphi.add(lchiphi);
            }
        } catch (SQLException e) {
            System.out.println("[LoaichphiDAO:load] " + e);
        }

        DB.disconnect();

        return l_loaichiphi;
    }

    public static LoaiChiPhiDTO getLoaiChiPhi(int malchiphi) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM loaichiphi WHERE MaLoaiChiPhi='" + malchiphi + "'");

        try {
            while (rs.next()) {
                LoaiChiPhiDTO lchiphi = new LoaiChiPhiDTO(rs.getInt(1));
                lchiphi.setTenloaichiphi(rs.getString(2));
                

                DB.disconnect();
                return lchiphi;
            }
        } catch (SQLException e) {
            System.out.println("[LoaiChiPhiDAO:getLoaiChiPhi] " + e);
        }

        DB.disconnect();

        return null;
    }

    public static void add(LoaiChiPhiDTO lchiphi) {
        Database DB = new Database();
        DB.connect();

        String sql = "INSERT INTO loaichiphi (TenLoaiChiPhi) VALUES ('";
        sql += lchiphi.getTenloaichiphi()+ "');";
        

        DB.update(sql);
        DB.disconnect();
    }

    public static void delete(int maloaichiphi) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM loaichiphi WHERE maloaichiphi=" + maloaichiphi);
        DB.disconnect();
    }

    public static void edit(LoaiChiPhiDTO lchiphi) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE loaichiphi SET ";
        sql += "TenLoaiChiPhi='" + lchiphi.getTenloaichiphi();
        sql += "' WHERE loaichiphi.MaLoaiChiPhi = " + lchiphi.getMaloaichiphi()+ ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaLoaiChiPhi) FROM loaichiphi");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            System.out.println("[LoaiChiPhiDAO:getNewID] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }
}
