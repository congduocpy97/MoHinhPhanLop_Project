package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.PhanBo;

public class DAO_PhanBo {

	static final String TABLE_NAME = "phanbo";

	public static ArrayList<PhanBo> search(PhanBo phanbo){
		ArrayList<PhanBo> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (phanbo.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(phanbo.getMaNhanVien()) + " and ") : " 1 and ")
			+ (phanbo.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(phanbo.getMaDoan()) + " and ") : " 1 and ")
			+ (phanbo.getNhiemVu() != null && ! phanbo.getNhiemVu().contentEquals("") ? ("`NhiemVu` like N\'%" + phanbo.getNhiemVu() + "%\'" + "") : "1");
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
					arr.add(new PhanBo(rs.getInt("MaNhanVien"), rs.getInt("MaDoan"), rs.getString("NhiemVu")));
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

	public static PhanBo findById(int manhanvien, int madoan) {
		PhanBo result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (manhanvien != -1 ? ("`MaNhanVien` = " + String.valueOf(manhanvien) + " and ") : " 1 and ")
			+ (madoan != -1 ? ("`MaDoan` = " + String.valueOf(madoan) + "") : "1")
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
					result = new PhanBo(rs.getInt("MaNhanVien"), rs.getInt("MaDoan"), rs.getString("NhiemVu"));
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

	public static int create(PhanBo phanbo) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (phanbo.getMaNhanVien() != -1 ? "`MaNhanVien`" : "")
			+ (phanbo.getMaDoan() != -1 ? ", " + "`MaDoan`" : "")
			+ (phanbo.getNhiemVu() != null && ! phanbo.getNhiemVu().contentEquals("") ? ", " + "`NhiemVu`" : "")
			+ ") VALUES ("
			+ (phanbo.getMaNhanVien() != -1 ? String.valueOf(phanbo.getMaNhanVien()) : "")
			+ (phanbo.getMaDoan() != -1 ? ", " + String.valueOf(phanbo.getMaDoan()) : "")
			+ (phanbo.getNhiemVu() != null && ! phanbo.getNhiemVu().contentEquals("") ? ", " + "N\'" + phanbo.getNhiemVu() + "\'" + "" : " ")
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

	public static int update(PhanBo phanbonew, PhanBo phanboold) {
		int code = 0;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (phanbonew.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(phanbonew.getMaNhanVien())) : "")
			+ (phanbonew.getMaDoan() != -1 ? (", " + "`MaDoan` = " + String.valueOf(phanbonew.getMaDoan())) : "")
			+ (phanbonew.getNhiemVu() != null && ! phanbonew.getNhiemVu().contentEquals("") ? (", " + "`NhiemVu` = N\'" + phanbonew.getNhiemVu() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (phanboold.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(phanboold.getMaNhanVien()) + " and ") : " 1 and ")
			+ (phanboold.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(phanboold.getMaDoan()) + "") : "1")
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

	public static int delete(PhanBo phanbo) {
		int code = 0;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (phanbo.getMaNhanVien() != -1 ? ("`MaNhanVien` = " + String.valueOf(phanbo.getMaNhanVien()) + " and ") : " 1 and ")
			+ (phanbo.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(phanbo.getMaDoan()) + "") : "1")
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