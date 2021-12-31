/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import DTO.LoaiHinhDuLichDTO;
import Tools.ThongBao;
import UTILS.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ChiThien
 */
public class LoaiHinhDuLichDAO {
      public static ArrayList<LoaiHinhDuLichDTO> load() {
        ArrayList<LoaiHinhDuLichDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM loaihinhdulich");

        try {
            while (rs.next()) {
                LoaiHinhDuLichDTO dd = new LoaiHinhDuLichDTO(rs.getInt(1));
                dd.setTenloaihinh(rs.getString(2));
                list.add(dd);
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiHinhDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static LoaiHinhDuLichDTO getLoaiHinh(int malh) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM loaihinhdulich WHERE maloaihinh=" + malh);

        try {
            while (rs.next()) {
                LoaiHinhDuLichDTO dd = new LoaiHinhDuLichDTO(rs.getInt(1));
                dd.setTenloaihinh(rs.getString(2));

                DB.disconnect();

                return dd;
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiHinhDAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(LoaiHinhDuLichDTO lh) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO loaihinhdulich (tenloaihinh) VALUES ('";
        sql += lh.getTenloaihinh()+ "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int malh) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM loaihinhdulich WHERE maloaihinh=" + malh);
        DB.disconnect();
    }

    public void edit(LoaiHinhDuLichDTO dd) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE loaihinhdulich SET ";
        sql += "tenloaihinh='" + dd.getTenloaihinh();
        sql += "' WHERE loaihinhdulich.maloaihinh = " + dd.getMaloaihinh()+ ";";
        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(maloaihinh) FROM loaihinhdulich");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiHinhDAO:load] " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<LoaiHinhDuLichDTO> find(String oj) {
        ArrayList<LoaiHinhDuLichDTO> arr = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM loaihinhdulich WHERE ";

        if (!oj.isEmpty()) {
            sql += "tenloaihinh='" + oj + "' AND ";
        }
        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                LoaiHinhDuLichDTO dd = new LoaiHinhDuLichDTO(rs.getInt(1));
                dd.setTenloaihinh(rs.getString(2));

                arr.add(dd);
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiHinhDAO:load] " + e);
        }

        DB.disconnect();

        return arr;
    }
}
