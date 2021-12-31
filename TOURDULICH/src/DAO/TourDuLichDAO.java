/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.TourDuLichDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;

/**
 *
 * @author ChiThien
 */
public class TourDuLichDAO {

    public static ArrayList<TourDuLichDTO> load() {
        ArrayList<TourDuLichDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM tourdulich");

        try {
            while (rs.next()) {
                TourDuLichDTO dd = new TourDuLichDTO(rs.getInt(1));
                dd.setTengoi(rs.getString(2));
                dd.setMaloaihinh(rs.getInt(3));
                dd.setMadiadiem(rs.getInt(4));
                list.add(dd);
            }
        } catch (SQLException e) {
            ThongBao.error("[TourDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static TourDuLichDTO getTour(int madd) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM tourdulich WHERE MaTour=" + madd);

        try {
            while (rs.next()) {
                TourDuLichDTO dd = new TourDuLichDTO(rs.getInt(1));
                dd.setTengoi(rs.getString(2));
                dd.setMaloaihinh(rs.getInt(3));
                dd.setMadiadiem(rs.getInt(4));

                DB.disconnect();

                return dd;
            }
        } catch (SQLException e) {
            ThongBao.error("[TourdulichAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(TourDuLichDTO dd) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO tourdulich (TenGoi,MaLoaiHinh,MaDiaDiem) VALUES ('";
        sql += dd.getTengoi() + "', '";
        sql += dd.getMaloaihinh() + "', '";
        sql += dd.getMadiadiem() + "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int madd) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM tourdulich WHERE MaTour=" + madd);
        DB.disconnect();
    }

    public void edit(TourDuLichDTO dd) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE tourdulich SET ";
        sql += "TenGoi='" + dd.getTengoi();
        sql += "',MaLoaiHinh='" + dd.getMaloaihinh();
        sql += "',MaDiaDiem='" + dd.getMadiadiem();
        sql += "'WHERE tourdulich.MaTour=" + dd.getMatour() + ";";
        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaTour) FROM tourdulich");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.error("[TourDAO:load] " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<TourDuLichDTO> find(String oj) {
        ArrayList<TourDuLichDTO> arr = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM tourdulich WHERE ";

        if (!oj.isEmpty()) {
            sql += "TenGoi='" + oj + "' AND ";
        }
        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                TourDuLichDTO dd = new TourDuLichDTO(rs.getInt(1));
                dd.setTengoi(rs.getString(2));
                dd.setMaloaihinh(rs.getInt(3));
                dd.setMadiadiem(rs.getInt(4));

                arr.add(dd);
            }
        } catch (SQLException e) {
            ThongBao.error("[TourDAO:load] " + e);
        }

        DB.disconnect();

        return arr;
    }
}
