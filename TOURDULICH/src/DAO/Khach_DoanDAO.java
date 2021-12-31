/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Khach_DoanDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;

/**
 *
 * @author
 */
public class Khach_DoanDAO {

    public static ArrayList<Khach_DoanDTO> load(int ma) {
        ArrayList<Khach_DoanDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM chitietdoan WHERE MaDoan ="+ma);

        try {
            while (rs.next()) {
                Khach_DoanDTO list_ = new Khach_DoanDTO();
                list_.setMadoan(rs.getInt(1));
                list_.setMakhachhang(rs.getInt(2));
                list.add(list_);
            }
        } catch (SQLException e) {
            System.out.println("[Khach_DoanDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static Khach_DoanDTO getKhach_Doan(int oj) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM chitietdoan WHERE MaDoan" + oj);

        try {
            while (rs.next()) {
                Khach_DoanDTO list_ = new Khach_DoanDTO();
                list_.setMadoan(rs.getInt(1));
                list_.setMakhachhang(rs.getInt(2));

                DB.disconnect();

                return list_;
            }
        } catch (SQLException e) {
            System.out.println("[Khach_DoanDAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }
    public static int countKhachDoan(int madoan) {
        int result=0;
        Database DB = new Database();
        DB.connect();
        String sql = "SELECT COUNT(ct.MaKhachHang)\n" +
"FROM chitietdoan ct, doandulich d\n" +
"WHERE d.MaDoan="+madoan+" AND ct.MaDoan = d.MaDoan\n" +
"GROUP BY d.MaDoan";
        ResultSet rs = DB.execution(sql);
        
        try {
            while (rs.next()) {
                result=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[Khach_DoanDAO:load] " + e);
        }

        DB.disconnect();

        return result;
    }

    public void add(Khach_DoanDTO key) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO  chitietdoan (MaDoan,MaKhachHang) VALUES ('";
        sql += key.getMadoan() + "', '";
        sql += key.getMakhachhang() + "');";
        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int  oj,int madoan) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM chitietdoan WHERE chitietdoan.MaKhachHang=" + oj +" AND chitietdoan.MaDoan="+madoan);
        DB.disconnect();
    }

    public void edit(Khach_DoanDTO oj,int makh) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE chitietdoan SET ";
        sql += "MaKhachHang='" + oj.getMakhachhang();
        sql += "' WHERE chitietdoan.MaKhachHang="+makh+" AND chitietdoan.MaDoan = " + oj.getMadoan() + ";";
        DB.update(sql);
        DB.disconnect();
    }
    public static void main(String[] args) {
        System.out.println(countKhachDoan(9));
    }

}
