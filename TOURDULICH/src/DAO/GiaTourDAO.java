/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.GiaTourDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ChiThien
 */
public class GiaTourDAO {

    public static ArrayList<GiaTourDTO> load() {
        ArrayList<GiaTourDTO> l_gia = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM giatour");

        try {
            while (rs.next()) {
                GiaTourDTO lgia = new GiaTourDTO(rs.getInt(1));
                lgia.setMatour(rs.getInt(2));
                lgia.setThoiGianbatdau(rs.getString(3));
                lgia.setThoiGianketthuc(rs.getString(4));
                lgia.setThanhtien(rs.getInt(5));
                l_gia.add(lgia);
            }
        } catch (SQLException e) {
            System.out.println("[GiaTourDAO:load] " + e);
        }

        DB.disconnect();

        return l_gia;
    }

    public static GiaTourDTO getGia(int malgia) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM giatour WHERE MaGia='" + malgia + "'");

        try {
            while (rs.next()) {
                GiaTourDTO lgia = new GiaTourDTO(rs.getInt(1));
                lgia.setMatour(rs.getInt(2));
                lgia.setThoiGianbatdau(rs.getString(3));
                lgia.setThoiGianketthuc(rs.getString(4));
                lgia.setThanhtien(rs.getInt(3));
                DB.disconnect();
                return lgia;
            }
        } catch (SQLException e) {
            System.out.println("[GiaTourDAO:getgia] " + e);
        }

        DB.disconnect();

        return null;
    }

    public static void add(GiaTourDTO lgia) {
        Database DB = new Database();
        DB.connect();

        String sql = "INSERT INTO giatour (MaTour,ThoiGianBatDau,ThoiGianKetThuc,ThanhTien) VALUES ('";
        sql += lgia.getMatour() + "', '";
        sql += lgia.getThoiGianbatdau() +  "', '";
        sql += lgia.getThoiGianketthuc() + "', '";
        sql += lgia.getThanhtien() + "');";
        DB.update(sql);
        DB.disconnect();
    }

    public static void delete(int magia) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM giatour WHERE MaGia=" + magia);

        DB.disconnect();
    }
    public static int getGiaDoan(int madoan){
        int result = 0;
        try {
            Database DB = new Database();
            DB.connect();
            String sql = "SELECT G.ThanhTien\n" +
                    "FROM tourdulich T, doandulich D, giatour G\n" +
                    "WHERE D.MaDoan="+madoan+ " AND T.MaTour = D.MaTour AND T.MaTour = G.MaTour AND D.NgayKhoiHanh BETWEEN G.ThoiGianBatDau AND G.ThoiGianKetThuc";
            ResultSet rs = DB.execution(sql);
           
            while (rs.next()){
                result = rs.getInt(1);
            }
            DB.disconnect();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    public static void edit(GiaTourDTO lgia) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE giatour SET ";
        sql += " MaTour = '" + lgia.getMatour();
        sql += "',ThoiGianBatDau='" + lgia.getThoiGianbatdau();
        sql += "',ThoiGianKetThuc='" + lgia.getThoiGianketthuc();
        sql += "',ThanhTien='" + lgia.getThanhtien();
        sql += "' WHERE giatour.MaGia = " + lgia.getMagia() + ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaGia) FROM giatour");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            System.out.println("[GiaTourDAO:getNewID] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }
    public static void main(String[] args) {
        System.out.println(getGiaDoan(9));
    }
}
