/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhanVienDTO;
import UTILS.Database;
import Tools.DateUtil;
import Tools.ThongBao;

/**
 *
 * @author Massan
 */
public class NhanVienDAO {

    public static ArrayList<NhanVienDTO> load() {
        ArrayList<NhanVienDTO> l_nhanvien = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM nhanvien");

        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));
                nv.setTennhanvien(rs.getString(2));
                l_nhanvien.add(nv);
            }
        } catch (SQLException e) {
            System.out.println("[NhanVienDAO:load] " + e);
        }

        DB.disconnect();

        return l_nhanvien;
    }

    public static NhanVienDTO getNhanVien(int manv) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM nhanvien WHERE MaNhanVien=" + manv);

        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));

                nv.setTennhanvien(rs.getString(2));

                DB.disconnect();

                return nv;
            }
        } catch (SQLException e) {
            System.out.println("[NhanvienDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(NhanVienDTO nv) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO nhanvien (TenNhanVien) VALUES ('";
        sql += nv.getTennhanvien()+ "');";;
        

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int manv) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM nhanvien WHERE MaNhanVien=" + manv);
        DB.disconnect();
    }

    public void edit(NhanVienDTO nv) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE nhanvien SET ";
        sql += "TenNhanVien='" + nv.getTennhanvien();
        sql += "' WHERE nhanvien.MaNhanVien = "+nv.getManhanvien()+";";
        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(MaNhanVien) FROM nhanvien");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            System.out.println("[NhanVienDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<NhanVienDTO> find(String tennv) {
        ArrayList<NhanVienDTO> l_nhanvien = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM nhanvien WHERE ";

        if (!tennv.isEmpty()) {
            sql += "TenNhanVien'" + tennv + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));

                nv.setTennhanvien(rs.getString(2));
                l_nhanvien.add(nv);
            }
        } catch (SQLException e) {
            System.out.println("[NhanVienDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_nhanvien;
    }
    /*
	public NhanVien find_sdt(int sdt)
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM nhanvien WHERE sdt="+sdt);
		
		try
		{
			while(rs.next())
			{
				NhanVien nv = new NhanVien(rs.getInt(1));

				nv.setHo(rs.getString(2));
				nv.setTen(rs.getString(3));
				nv.setGioiTinh(rs.getInt(4));
				nv.setNgaySinh(rs.getString(5));
				nv.setSDT(rs.getInt(6));
				nv.setEmail(rs.getString(7));
				nv.setNgayVao(rs.getString(8));
				nv.setChucVu(rs.getString(9));
				nv.setLuong(rs.getInt(10));
				
				DB.disconnect();
				
				return nv;
			}
		}
		catch(SQLException e)
		{
			ThongBao.error("[NhanVienDAO:find] error sql: "+e);
		}
		
		DB.disconnect();
		
		return null;
	}

	public NhanVien find_email(String email)
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM nhanvien WHERE email="+email);
		
		try
		{
			while(rs.next())
			{
				NhanVien nv = new NhanVien(rs.getInt(1));

				nv.setHo(rs.getString(2));
				nv.setTen(rs.getString(3));
				nv.setGioiTinh(rs.getInt(4));
				nv.setNgaySinh(rs.getString(5));
				nv.setSDT(rs.getInt(6));
				nv.setEmail(rs.getString(7));
				nv.setNgayVao(rs.getString(8));
				nv.setChucVu(rs.getString(9));
				nv.setLuong(rs.getInt(10));
				
				DB.disconnect();
				
				return nv;
			}
		}
		catch(SQLException e)
		{
			ThongBao.error("[NhanVienDAO:find] error sql: "+e);
		}
		
		DB.disconnect();
		
		return null;
	}
     */
}
