package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ChiTietDoan;

public class DAO_ChiTietDoan {

	static final String TABLE_NAME = "chitietdoan";

	public static ArrayList<ChiTietDoan> search(ChiTietDoan chitietdoan){
		ArrayList<ChiTietDoan> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (chitietdoan.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(chitietdoan.getMaDoan()) + " and ") : " 1 and ")
			+ (chitietdoan.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(chitietdoan.getMaKhachHang()) + "") : "1");
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
					arr.add(new ChiTietDoan(rs.getInt("MaDoan"), rs.getInt("MaKhachHang")));
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

	public static ChiTietDoan findById(int madoan, int makhachhang) {
		ChiTietDoan result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (madoan != -1 ? ("`MaDoan` = " + String.valueOf(madoan) + " and ") : "")
			+ (makhachhang != -1 ? ("`MaKhachHang` = " + String.valueOf(makhachhang) + "") : "")
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
					result = new ChiTietDoan(rs.getInt("MaDoan"), rs.getInt("MaKhachHang"));
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

	public static int create(ChiTietDoan chitietdoan) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (chitietdoan.getMaDoan() != -1 ? "`MaDoan`, " : "")
			+ (chitietdoan.getMaKhachHang() != -1 ? "`MaKhachHang`" : "")
			+ ") VALUES ("
			+ (chitietdoan.getMaDoan() != -1 ? String.valueOf(chitietdoan.getMaDoan()) + ", " : "")
			+ (chitietdoan.getMaKhachHang() != -1 ? String.valueOf(chitietdoan.getMaKhachHang()) + "" : "")
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

	public static int update(ChiTietDoan chitietdoannew, ChiTietDoan chitietdoanold) {
		int code = 0;
		if(!(chitietdoanold.getMaDoan() != -1 && chitietdoanold.getMaKhachHang() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (chitietdoannew.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(chitietdoannew.getMaDoan()) + ", ") : "")
			+ (chitietdoannew.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(chitietdoannew.getMaKhachHang()) + "") : "")
			+ " WHERE ";
		String where = ""
			+ (chitietdoanold.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(chitietdoanold.getMaDoan()) + " and ") : " 1 and ")
			+ (chitietdoanold.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(chitietdoanold.getMaKhachHang()) + "") : "1")
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

	public static int delete(ChiTietDoan chitietdoan) {
		int code = 0;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (chitietdoan.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(chitietdoan.getMaDoan()) + " and ") : " 1 and ")
			+ (chitietdoan.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(chitietdoan.getMaKhachHang()) + "") : "1")
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