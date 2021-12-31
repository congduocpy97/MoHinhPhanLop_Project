/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import DTO.DiaDiemDTO;
import UTILS.Database;
import Tools.ThongBao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ChiThien
 */
public class DiaDiemDAO {

    public static ArrayList<DiaDiemDTO> load() {
        ArrayList<DiaDiemDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM diadiem");

        try {
            while (rs.next()) {
                DiaDiemDTO dd = new DiaDiemDTO(rs.getInt(1));
                dd.setTendiadiem(rs.getString(2));
                list.add(dd);
            }
        } catch (SQLException e) {
            ThongBao.error("[DiaDiemDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static DiaDiemDTO getDiaDiem(int madd) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM diadiem WHERE MaDiaDiem=" + madd);

        try {
            while (rs.next()) {
                DiaDiemDTO dd = new DiaDiemDTO(rs.getInt(1));
                dd.setTendiadiem(rs.getString(2));

                DB.disconnect();

                return dd;
            }
        } catch (SQLException e) {
            ThongBao.error("[DiaDiemDAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(DiaDiemDTO dd) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO diadiem (TenDiaDiem) VALUES ('";
        sql += dd.getTendiadiem() + "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int madd) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM diadiem WHERE MaDiaDiem=" + madd);
        DB.disconnect();
    }

    public void edit(DiaDiemDTO dd) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE diadiem SET ";
        sql += "TenDiaDiem='" + dd.getTendiadiem();
        sql += "' WHERE diadiem.MaDiaDiem = " + dd.getMadiadiem() + ";";
        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaDiaDiem) FROM diadiem");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.error("[DiaDiemDAO:load] " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<DiaDiemDTO> find(String oj) {
        ArrayList<DiaDiemDTO> arr = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM theloaisach WHERE ";

        if (!oj.isEmpty()) {
            sql += "tentl='" + oj + "' AND ";
        }
        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                DiaDiemDTO dd = new DiaDiemDTO(rs.getInt(1));
                dd.setTendiadiem(rs.getString(2));

                arr.add(dd);
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiSachDAO:load] " + e);
        }

        DB.disconnect();

        return arr;
    }
}
