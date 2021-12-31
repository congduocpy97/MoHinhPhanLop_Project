package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.NhanVien;

public class DAO_NhanVien {

	static final String TABLE_NAME = "nhanvien";

	public static ArrayList<NhanVien> search(NhanVien nhanvien){
		ArrayList<NhanVien> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (nhanvien.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(nhanvien.getMaNhanVien()) + " and ") : " 1 and ")
			+ (nhanvien.getTenNhanVien() != null && ! nhanvien.getTenNhanVien().contentEquals("") ? ("`TenNhanVien` like N\'%" + nhanvien.getTenNhanVien() + "%\'" + " and ") : " 1 and ")
			+ (nhanvien.getTrangThai() != null && ! nhanvien.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + nhanvien.getTrangThai() + "%\'" + "") : "1");
		if(where.contentEquals(""))
			sql = sql.substring(0, sql.length() - 7);
		else
			sql += where;
		Connection conn = Connector.connect();
		if( conn != null) {
			try {
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				while(rs.next()) {
					arr.add(new NhanVien(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("TrangThai")));
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
					System.out.println(e.getMessage());
			}		}
		return arr;
	}

	public static NhanVien findById(int manhanvien) {
		NhanVien result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (manhanvien != -1 ? ("`MaNhanVien` = " + String.valueOf(manhanvien) + "") : "")
			+"";
		if(where.contentEquals(""))
			sql = sql.substring(0, sql.length() - 7);
		else
			sql += where;
		Connection conn = Connector.connect();
		if( conn != null) {
			try {
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				while(rs.next()) {
					result = new NhanVien(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("TrangThai"));
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}		}
		return result;
	}

	public static int create(NhanVien nhanvien) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (nhanvien.getTenNhanVien() != null && ! nhanvien.getTenNhanVien().contentEquals("") ? "`TenNhanVien`" : "")
			+ (nhanvien.getTrangThai() != null && ! nhanvien.getTrangThai().contentEquals("") ? ", " + "`TrangThai`" : "")
			+ ") VALUES ("
			+ (nhanvien.getTenNhanVien() != null && ! nhanvien.getTenNhanVien().contentEquals("") ? "N\'" + nhanvien.getTenNhanVien() + "\'": "")
			+ (nhanvien.getTrangThai() != null && ! nhanvien.getTrangThai().contentEquals("") ? ", " + "N\'" + nhanvien.getTrangThai() + "\'" + "" : "")
			+ ")";
		Connection conn = Connector.connect();
		if( conn != null) {
			try {
				Statement stat = conn.createStatement();
				code = stat.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return code;
	}

	public static int update(NhanVien nhanviennew, NhanVien nhanvienold) {
		int code = 0;
		if(!(nhanvienold.getMaNhanVien() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (nhanviennew.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(nhanviennew.getMaNhanVien())) : "")
			+ (nhanviennew.getTenNhanVien() != null && ! nhanviennew.getTenNhanVien().contentEquals("") ? (", " + "`TenNhanVien` = N\'" + nhanviennew.getTenNhanVien() + "\'") : "")
			+ (nhanviennew.getTrangThai() != null && ! nhanviennew.getTrangThai().contentEquals("") ? (", " + "`TrangThai` = N\'" + nhanviennew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (nhanvienold.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(nhanvienold.getMaNhanVien()) + "") : "")
			+"";
		if(where.contentEquals(""))
			sql = sql.substring(0, sql.length() - 7);
		else
			sql += where;
		Connection conn = Connector.connect();
		if( conn != null) {
			try {
				Statement stat = conn.createStatement();
				code = stat.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return code;
	}

	public static int delete(NhanVien nhanvien) {
		int code = 0;
		if(!(nhanvien.getMaNhanVien() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (nhanvien.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(nhanvien.getMaNhanVien()) + "") : "")
			+"";
		if(where.contentEquals(""))
			sql = sql.substring(0, sql.length() - 7);
		else
			sql += where;
		Connection conn = Connector.connect();
		if( conn != null) {
			try {
				Statement stat = conn.createStatement();
				code = stat.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return code;
	}

}