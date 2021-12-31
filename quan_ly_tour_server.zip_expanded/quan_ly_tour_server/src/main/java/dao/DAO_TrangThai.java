package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.TrangThai;

public class DAO_TrangThai {

	static final String TABLE_NAME = "trangthai";

	public static ArrayList<TrangThai> search(TrangThai trangthai){
		ArrayList<TrangThai> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (trangthai.getTrangThai() != null && ! trangthai.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + trangthai.getTrangThai() + "%\'" + " and ") : "1 and ")
			+ (trangthai.getTen() != null && ! trangthai.getTen().contentEquals("") ? ("`Ten` like N\'%" + trangthai.getTen() + "%\'" + "") : "1");
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
					arr.add(new TrangThai(rs.getString("TrangThai"), rs.getString("Ten")));
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

	public static TrangThai findById(String trangthai) {
		TrangThai result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (trangthai != null && ! trangthai.contentEquals("") ? ("`TrangThai` = \'" + trangthai + "\'" + "") : "")
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
					result = new TrangThai(rs.getString("TrangThai"), rs.getString("Ten"));
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

	public static int create(TrangThai trangthai) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (trangthai.getTrangThai() != null && ! trangthai.getTrangThai().contentEquals("") ? "`TrangThai`" : "")
			+ (trangthai.getTen() != null && ! trangthai.getTen().contentEquals("") ? ", `Ten`" : "")
			+ ") VALUES ("
			+ (trangthai.getTrangThai() != null && ! trangthai.getTrangThai().contentEquals("") ? "N\'" + trangthai.getTrangThai() + "\'" : "")
			+ (trangthai.getTen() != null && ! trangthai.getTen().contentEquals("") ? ", N\'" + trangthai.getTen() + "\'" + "" : "")
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

	public static int update(TrangThai trangthainew, TrangThai trangthaiold) {
		int code = 0;
		if(!((trangthaiold.getTrangThai() != null && ! trangthaiold.getTrangThai().contentEquals(""))))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (trangthainew.getTrangThai() != null && ! trangthainew.getTrangThai().contentEquals("") ? ("`TrangThai` = N\'" + trangthainew.getTrangThai() + "\'") : "")
			+ (trangthainew.getTen() != null && ! trangthainew.getTen().contentEquals("") ? (", `Ten` = N\'" + trangthainew.getTen() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (trangthaiold.getTrangThai() != null && ! trangthaiold.getTrangThai().contentEquals("") ? ("`TrangThai` = \'" + trangthaiold.getTrangThai() + "\'" + "") : "")
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

	public static int delete(TrangThai trangthai) {
		int code = 0;
		if(!((trangthai.getTrangThai() != null && ! trangthai.getTrangThai().contentEquals(""))))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (trangthai.getTrangThai() != null && ! trangthai.getTrangThai().contentEquals("") ? ("`TrangThai` = \'" + trangthai.getTrangThai() + "\'" + "") : "")
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