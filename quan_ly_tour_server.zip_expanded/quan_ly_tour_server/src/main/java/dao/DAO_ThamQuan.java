package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ThamQuan;

public class DAO_ThamQuan {

	static final String TABLE_NAME = "thamquan";

	public static ArrayList<ThamQuan> search(ThamQuan thamquan){
		ArrayList<ThamQuan> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (thamquan.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(thamquan.getMaTour()) + " and ") : " 1 and ")
			+ (thamquan.getMaDiaDiem() != -1  ? ("`MaDiaDiem` = " + thamquan.getMaDiaDiem() + " and ") : " 1 and ")
			+ (thamquan.getThuTu() != -1 ? ("`ThuTu` = " + String.valueOf(thamquan.getThuTu()) + " and ") : " 1 and ")
			+ (thamquan.getTrangThai() != null && ! thamquan.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + thamquan.getTrangThai() + "%\'" + "") : " 1 ");
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
					arr.add(new ThamQuan(rs.getInt("MaTour"), rs.getInt("MaDiaDiem"), rs.getInt("ThuTu"), rs.getString("TrangThai")));
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

	public static ThamQuan findById(int matour, int madiadiem, int thutu) {
		ThamQuan result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (matour != -1 ? ("`MaTour` = " + String.valueOf(matour) + " and ") : " 1 and")
			+ (madiadiem != -1 ? ("`MaDiaDiem` = " + madiadiem + " and ") : " 1 and")
			+ (thutu != -1 ? ("`ThuTu` = " + String.valueOf(thutu) + "") : " 1 ")
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
					result = new ThamQuan(rs.getInt("MaTour"), rs.getInt("MaDiaDiem"), rs.getInt("ThuTu"), rs.getString("TrangThai"));
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

	public static int create(ThamQuan thamquan) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (thamquan.getMaTour() != -1 ? "`MaTour`" : "")
			+ (thamquan.getMaDiaDiem() != -1 ? ", " + "`MaDiaDiem`" : "")
			+ (thamquan.getThuTu() != -1 ? ", " + "`ThuTu`" : "")
			+ (thamquan.getTrangThai() != null && ! thamquan.getTrangThai().contentEquals("") ? ", " + "`TrangThai`" : "")
			+ ") VALUES ("
			+ (thamquan.getMaTour() != -1 ? String.valueOf(thamquan.getMaTour()) : "")
			+ (thamquan.getMaDiaDiem() != -1 ? ", "  + thamquan.getMaDiaDiem() : "")
			+ (thamquan.getThuTu() != -1 ? ", " + String.valueOf(thamquan.getThuTu()) : "")
			+ (thamquan.getTrangThai() != null && ! thamquan.getTrangThai().contentEquals("") ? ", " + "N\'" + thamquan.getTrangThai() + "\'" + "" : "")
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

	public static int update(ThamQuan thamquannew, ThamQuan thamquanold) {
		int code = 0;
		if(!(thamquanold.getMaTour() != -1 && (thamquanold.getMaDiaDiem() != -1 ) && thamquanold.getThuTu() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (thamquannew.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(thamquannew.getMaTour())) : "")
			+ (thamquannew.getMaDiaDiem() != -1 ? (", " + "`MaDiaDiem` = " + thamquannew.getMaDiaDiem()) : "")
			+ (thamquannew.getThuTu() != -1 ? (", " + "`ThuTu` = " + String.valueOf(thamquannew.getThuTu())) : "")
			+ (thamquannew.getTrangThai() != null && ! thamquannew.getTrangThai().contentEquals("") ? (", " + "`TrangThai` = N\'" + thamquannew.getTrangThai() + "\'") : "")
			+ " WHERE ";
		String where = ""
			+ (thamquanold.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(thamquanold.getMaTour()) + " and ") : " 1 and ")
			+ (thamquanold.getMaDiaDiem() != -1 ? ("`MaDiaDiem` = " + thamquanold.getMaDiaDiem() + " and ") : " 1 and ")
			+ (thamquanold.getThuTu() != -1 ? ("`ThuTu` = " + String.valueOf(thamquanold.getThuTu()) + "") : "1")
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

	public static int delete(ThamQuan thamquan) {
		int code = 0;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (thamquan.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(thamquan.getMaTour()) + " and ") : " 1 and ")
			+ (thamquan.getMaDiaDiem() != -1 ? ("`MaDiaDiem` = " + thamquan.getMaDiaDiem() + " and ") : " 1 and ")
			+ (thamquan.getThuTu() != -1 ? ("`ThuTu` = " + String.valueOf(thamquan.getThuTu()) + "") : "1")
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