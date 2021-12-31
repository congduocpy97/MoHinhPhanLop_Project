package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.LoaiHinhDuLich;

public class DAO_LoaiHinhDuLich {

	static final String TABLE_NAME = "loaihinhdulich";

	public static ArrayList<LoaiHinhDuLich> search(LoaiHinhDuLich loaihinhdulich){
		ArrayList<LoaiHinhDuLich> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (loaihinhdulich.getMaLoaiHinh() != -1 ? ("`MaLoaiHinh` = " + String.valueOf(loaihinhdulich.getMaLoaiHinh()) + " and ") : " 1 and ")
			+ (loaihinhdulich.getTenLoaiHinh() != null && ! loaihinhdulich.getTenLoaiHinh().contentEquals("") ? ("`TenLoaiHinh` like N\'%" + loaihinhdulich.getTenLoaiHinh() + "%\'" + " and ") : " 1 and ")
			+ (loaihinhdulich.getTrangThai() != null && ! loaihinhdulich.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + loaihinhdulich.getTrangThai() + "%\'" + "") : "1");
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
					arr.add(new LoaiHinhDuLich(rs.getInt("MaLoaiHinh"), rs.getString("TenLoaiHinh"), rs.getString("TrangThai")));
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

	public static LoaiHinhDuLich findById(int maloaihinh) {
		LoaiHinhDuLich result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (maloaihinh != -1 ? ("`MaLoaiHinh` = " + String.valueOf(maloaihinh) + "") : "")
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
					result = new LoaiHinhDuLich(rs.getInt("MaLoaiHinh"), rs.getString("TenLoaiHinh"), rs.getString("TrangThai"));
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

	public static int create(LoaiHinhDuLich loaihinhdulich) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (loaihinhdulich.getTenLoaiHinh() != null && ! loaihinhdulich.getTenLoaiHinh().contentEquals("") ? "`TenLoaiHinh`" : "")
			+ (loaihinhdulich.getTrangThai() != null && ! loaihinhdulich.getTrangThai().contentEquals("") ? ", " + "`TrangThai`" : "")
			+ ") VALUES ("
			+ (loaihinhdulich.getTenLoaiHinh() != null && ! loaihinhdulich.getTenLoaiHinh().contentEquals("") ? "N\'" + loaihinhdulich.getTenLoaiHinh() + "\'": "")
			+ (loaihinhdulich.getTrangThai() != null && ! loaihinhdulich.getTrangThai().contentEquals("") ? ", " + "N\'" + loaihinhdulich.getTrangThai() + "\'" + "" : "")
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

	public static int update(LoaiHinhDuLich loaihinhdulichnew, LoaiHinhDuLich loaihinhdulichold) {
		int code = 0;
		if(!(loaihinhdulichold.getMaLoaiHinh() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (loaihinhdulichnew.getMaLoaiHinh() != -1 ? ("`MaLoaiHinh` = " + String.valueOf(loaihinhdulichnew.getMaLoaiHinh())) : "")
			+ (loaihinhdulichnew.getTenLoaiHinh() != null && ! loaihinhdulichnew.getTenLoaiHinh().contentEquals("") ? (", " + "`TenLoaiHinh` = N\'" + loaihinhdulichnew.getTenLoaiHinh() + "\'") : "")
			+ (loaihinhdulichnew.getTrangThai() != null && ! loaihinhdulichnew.getTrangThai().contentEquals("") ? (", " + "`TrangThai` = N\'" + loaihinhdulichnew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (loaihinhdulichold.getMaLoaiHinh() != -1 ? ("`MaLoaiHinh` = " + String.valueOf(loaihinhdulichold.getMaLoaiHinh()) + "") : "")
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

	public static int delete(LoaiHinhDuLich loaihinhdulich) {
		int code = 0;
		if(!(loaihinhdulich.getMaLoaiHinh() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (loaihinhdulich.getMaLoaiHinh() != -1 ? ("`MaLoaiHinh` = " + String.valueOf(loaihinhdulich.getMaLoaiHinh()) + "") : "")
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