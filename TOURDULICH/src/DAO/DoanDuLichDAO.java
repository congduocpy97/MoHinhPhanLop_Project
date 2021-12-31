/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DoanDuLichDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ChiThien
 */
public class DoanDuLichDAO {

    public static ArrayList<DoanDuLichDTO> load() {
        ArrayList<DoanDuLichDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM doandulich");

        try {
            while (rs.next()) {
                DoanDuLichDTO oj = new DoanDuLichDTO(rs.getInt(1));

                oj.setMatour(rs.getInt(2));
                oj.setNgaykhoihanh(rs.getString(3));
                oj.setNgayketthuc(rs.getString(4));
                oj.setMaloaichiphi(rs.getInt(5));
                oj.setDoanhthu(rs.getInt(6));

                list.add(oj);
            }
        } catch (SQLException e) {
            System.out.println("DoanDuLichDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static DoanDuLichDTO getDoanDuLich(int oj) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM doandulich WHERE MaDoan=" + oj);

        try {
            while (rs.next()) {
                DoanDuLichDTO list_ = new DoanDuLichDTO(rs.getInt(1));

                list_.setMatour(rs.getInt(2));
                list_.setNgaykhoihanh(rs.getString(3));
                list_.setNgayketthuc(rs.getString(4));
                list_.setMaloaichiphi(rs.getInt(5));
                list_.setDoanhthu(rs.getInt(6));
                DB.disconnect();

                return list_;
            }
        } catch (SQLException e) {
            System.out.println("[DoanDuLichDAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(DoanDuLichDTO key) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO  doandulich (MaTour,NgayKhoiHanh,NgayKetThuc,MaLoaiChiPhi,DoanhThu) VALUES ('";
        sql += key.getMatour() + "', '";
        sql += key.getNgaykhoihanh() + "', '";
        sql += key.getNgayketthuc() + "', '";
        sql += key.getMaloaichiphi() + "' ,'";
        sql += key.getDoanhthu() + "');";
        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int oj) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM doandulich WHERE MaDoan=" + oj);
        DB.disconnect();
    }

    public void edit(DoanDuLichDTO oj) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE doandulich SET ";
        sql += "MaTour='" + oj.getMatour();
        sql += "' ,NgayKhoiHanh='" + oj.getNgaykhoihanh();
        sql += "' ,NgayKetThuc='" + oj.getNgayketthuc();
        sql += "' ,MaLoaiChiPhi='" + oj.getMaloaichiphi();
        sql += "' ,DoanhThu='" + oj.getDoanhthu();
        sql += "' WHERE doandulich.MaDoan = " + oj.getMadoan() + ";";

        DB.update(sql);
        DB.disconnect();
    }
    public void editDoanhThu(int doanhthu,int madoan) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE doandulich SET ";
        sql += "DoanhThu='" + doanhthu;
        sql += "' WHERE doandulich.MaDoan = " + madoan + ";";

        DB.update(sql);
        DB.disconnect();
    }
    public static ArrayList<Integer> getMadoantour(int matour){
        ArrayList<Integer> arr = new ArrayList<>();
        try {
            
            Database DB = new Database();
            DB.connect();
            
            String sql = "SELECT madoan\n" +
                    "FROM doandulich\n" +
                    "WHERE MaTour="+matour;
            ResultSet rs = DB.execution(sql);
            while(rs.next()){
                arr.add(rs.getInt(1));
            }
           DB.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DoanDuLichDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaDoan) FROM doandulich");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            System.out.println("[DoanDuLichDAO:load] " + e);;
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<DoanDuLichDTO> find(String oj) {
        ArrayList<DoanDuLichDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM doandulich WHERE ";
        if (!oj.isEmpty()) {
            sql += "MaDoan='" + oj + "' AND ";
        }
        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                DoanDuLichDTO key = new DoanDuLichDTO(rs.getInt(1));

                key.setMatour(rs.getInt(2));
                key.setNgaykhoihanh(rs.getString(3));
                key.setNgayketthuc(rs.getString(4));
                key.setDoanhthu(rs.getInt(5));
                key.setMaloaichiphi(rs.getInt(6));
                list.add(key);
            }
        } catch (SQLException e) {
            System.out.println("[DoanrdulichDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return list;
    }
//    public Tourdulich find(String oj) {
//        Database DB = new Database();
//        DB.connect();
//
//        ResultSet rs = DB.execution("SELECT * FROM tourdulich WHERE TenGoi=" + oj);
//
//        try {
//            while (rs.next()) {
//                Tourdulich kh = new Tourdulich(rs.getString(1));
//
//                kh.setTengoi(rs.getString(1));
//
//                DB.disconnect();
//
//                return kh;
//            }
//        } catch (SQLException e) {
//            System.out.println("[TourdulichDAO:find] error sql: " + e);
//        }
//
//        DB.disconnect();
//
//        return null;
//    }
//    public static ArrayList find(String oj) throws SQLException {
//        ArrayList<Tourdulich> list = new ArrayList();
//
//        String sql = "select * from tourdulich where TenGoi like '%" + oj + "%'";
//        Database DB = new Database();
//        DB.connect();
//
//        ResultSet rs = DB.execution(sql);
//
//        try {
//            while (rs.next()) {
//                Tourdulich key = new Tourdulich(rs.getString(1));
//
//                key.setTengoi(rs.getString(1));
//
//                //DB.disconnect();
//                list.add(key);
//            }
//            //return findkh;
//        } catch (SQLException e) {
//            System.out.println("[TourdulichDAO:find] error sql: " + e);
//        }
//
//        DB.disconnect();
//
//        return list;
//    }
}
