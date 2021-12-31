/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Nv_DoanDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;

/**
 *
 * @author
 */
public class Nv_DoanDAO {

    public static ArrayList<Nv_DoanDTO> load(int ma) {
        ArrayList<Nv_DoanDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM phanbonhanvien_doan WHERE MaDoan ="+ma);

        try {
            while (rs.next()) {
                Nv_DoanDTO list_ = new Nv_DoanDTO();
                list_.setMadoan(rs.getInt(1));
                list_.setManhanvien(rs.getInt(2));
                list_.setNhiemvu(rs.getString(3));
                list.add(list_);
            }
        } catch (SQLException e) {
            System.out.println("[NV_DoanDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static Nv_DoanDTO getKhach_Doan(int oj) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM phanbonhanvien_doan WHERE MaDoan" + oj);

        try {
            while (rs.next()) {
                Nv_DoanDTO list_ = new Nv_DoanDTO();
                list_.setMadoan(rs.getInt(1));
                list_.setManhanvien(rs.getInt(2));
                list_.setNhiemvu(rs.getString(3));

                DB.disconnect();

                return list_;
            }
        } catch (SQLException e) {
            System.out.println("[NV_DoanDAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(Nv_DoanDTO key) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO  phanbonhanvien_doan (MaDoan,MaNhanVien,NhiemVu) VALUES ('";
        sql += key.getMadoan() + "', '";
         sql += key.getManhanvien()+ "', '";
        sql += key.getNhiemvu()+ "');";
        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int oj,int madoan) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM phanbonhanvien_doan WHERE MaNhanVien=" + oj+" AND MaDoan="+madoan);
        DB.disconnect();
    }

    public void edit(Nv_DoanDTO oj,int manv) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE phanbonhanvien_doan SET ";
        sql += "MaNhanVien='" + oj.getManhanvien();
        sql+="' ,NhiemVu='" +oj.getNhiemvu();
        sql += "' WHERE phanbonhanvien_doan.MaDoan="+oj.getMadoan()+" AND phanbonhanvien_doan.MaNhanvien = " + manv+ ";";
        DB.update(sql);
        DB.disconnect();
    }

   

}
