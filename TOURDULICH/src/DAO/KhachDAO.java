/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.KhachDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;

/**
 *
 * @author ChiThien
 */
public class KhachDAO {

    public static ArrayList<KhachDTO> load() {
        ArrayList<KhachDTO> l_khach = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM khach");

        try {
            while (rs.next()) {
                KhachDTO lkhach = new KhachDTO(rs.getInt(1));
                 lkhach.setHoten(rs.getString(2));
                lkhach.setSocmnd(rs.getString(3));
                lkhach.setDiachi(rs.getString(4));
                lkhach.setGioitinh(rs.getString(5));
                lkhach.setSdt(rs.getString(6));
                l_khach.add(lkhach);
            }
        } catch (SQLException e) {
            System.out.println("[KhachDAO:load] " + e);
        }

        DB.disconnect();

        return l_khach;
    }

    public static KhachDTO getKhach(int makhachhang) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM khach WHERE MaKhachHang='" + makhachhang + "'");

        try {
            while (rs.next()) {
                KhachDTO lkhach = new KhachDTO(rs.getInt(1));
                lkhach.setHoten(rs.getString(2));
                lkhach.setSocmnd(rs.getString(3));
                lkhach.setDiachi(rs.getString(4));
                lkhach.setGioitinh(rs.getString(5));
                lkhach.setSdt(rs.getString(6));
                DB.disconnect();
                return lkhach;
            }
        } catch (SQLException e) {
            System.out.println("[KhachDAO:getgia] " + e);
        }

        DB.disconnect();

        return null;
    }

    public static void add(KhachDTO lkhach) {
        Database DB = new Database();
        DB.connect();

        String sql = "INSERT INTO khach (HoTen,SoCMND,DiaChi,GioiTinh,SDT) VALUES ('";
        sql += lkhach.getHoten() + "', '";
        sql += lkhach.getSocmnd() + "', '";
        sql += lkhach.getDiachi() + "', '";
        sql += lkhach.getGioitinh() + "', '";
        sql += lkhach.getSdt() + "');";
        DB.update(sql);
        DB.disconnect();
    }

    public static void delete(int makhachhang) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM khach WHERE MaKhachHang=" + makhachhang);

        DB.disconnect();
    }

    public static void edit(KhachDTO lkhach) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE khach SET ";
        sql += "HoTen='" + lkhach.getHoten();
        sql += " ', SoCMND='" + lkhach.getSocmnd();
        sql += " ', DiaChi='" + lkhach.getDiachi();
        sql += " ', GioiTinh='" + lkhach.getGioitinh();
        sql += " ', SDT='" + lkhach.getSdt();
        sql += " ' WHERE khach.MaKhachHang = " + lkhach.getMakhachhang() + ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaKhachHang) FROM khach");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            System.out.println("[KhachDAO:getNewID] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public static ArrayList find(String oj) {
        ArrayList<KhachDTO> list = new ArrayList();

        String sql = "select * from khach where HoTen like '%" + oj + "%'";
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                KhachDTO lkhach = new KhachDTO(rs.getInt(1));
                lkhach.setHoten(rs.getString(2));
                lkhach.setSocmnd(rs.getString(3));
                lkhach.setGioitinh(rs.getString(4));
                lkhach.setDiachi(rs.getString(5));
                lkhach.setSdt(rs.getString(6));

                //DB.disconnect();
                list.add(lkhach);
            }
            //return findkh;
        } catch (SQLException e) {
            System.out.println("[KhachDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return list;
    }

}
