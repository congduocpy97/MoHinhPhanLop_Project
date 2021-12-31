package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.LoaiChiPhi;

public class DAO_LoaiChiPhi {

	static final String TABLE_NAME = "loaichiphi";

	public static ArrayList<LoaiChiPhi> search(LoaiChiPhi loaichiphi){
		ArrayList<LoaiChiPhi> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (loaichiphi.getMaLoaiChiPhi() != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(loaichiphi.getMaLoaiChiPhi()) + " and ") : " 1 and ")
			+ (loaichiphi.getTenLoaiChiPhi() != null && ! loaichiphi.getTenLoaiChiPhi().contentEquals("") ? ("`TenLoaiChiPhi` like N\'%" + loaichiphi.getTenLoaiChiPhi() + "%\'" + " and ") : " 1 and ")
			+ (loaichiphi.getTrangThai() != null && ! loaichiphi.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + loaichiphi.getTrangThai() + "%\'" + "") : "1");
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
					arr.add(new LoaiChiPhi(rs.getInt("MaLoaiChiPhi"), rs.getString("TenLoaiChiPhi"), rs.getString("TrangThai")));
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

	public static LoaiChiPhi findById(int maloaichiphi) {
		LoaiChiPhi result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (maloaichiphi != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(maloaichiphi) + "") : "")
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
					result = new LoaiChiPhi(rs.getInt("MaLoaiChiPhi"), rs.getString("TenLoaiChiPhi"), rs.getString("TrangThai"));
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

	public static int create(LoaiChiPhi loaichiphi) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (loaichiphi.getTenLoaiChiPhi() != null && ! loaichiphi.getTenLoaiChiPhi().contentEquals("") ? "`TenLoaiChiPhi`, " : "")
			+ (loaichiphi.getTrangThai() != null && ! loaichiphi.getTrangThai().contentEquals("") ? "`TrangThai`" : "")
			+ ") VALUES ("
			+ (loaichiphi.getTenLoaiChiPhi() != null && ! loaichiphi.getTenLoaiChiPhi().contentEquals("") ? "N\'" + loaichiphi.getTenLoaiChiPhi() + "\'" + ", " : "")
			+ (loaichiphi.getTrangThai() != null && ! loaichiphi.getTrangThai().contentEquals("") ? "N\'" + loaichiphi.getTrangThai() + "\'" + "" : "")
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

	public static int update(LoaiChiPhi loaichiphinew, LoaiChiPhi loaichiphiold) {
		int code = 0;
		if(!(loaichiphiold.getMaLoaiChiPhi() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (loaichiphinew.getMaLoaiChiPhi() != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(loaichiphinew.getMaLoaiChiPhi()) + ", ") : "")
			+ (loaichiphinew.getTenLoaiChiPhi() != null && ! loaichiphinew.getTenLoaiChiPhi().contentEquals("") ? ("`TenLoaiChiPhi` = N\'" + loaichiphinew.getTenLoaiChiPhi() + "\'" + ", ") : "")
			+ (loaichiphinew.getTrangThai() != null && ! loaichiphinew.getTrangThai().contentEquals("") ? ("`TrangThai` = N\'" + loaichiphinew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (loaichiphiold.getMaLoaiChiPhi() != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(loaichiphiold.getMaLoaiChiPhi()) + "") : "")
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

	public static int delete(LoaiChiPhi loaichiphi) {
		int code = 0;
		if(!(loaichiphi.getMaLoaiChiPhi() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (loaichiphi.getMaLoaiChiPhi() != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(loaichiphi.getMaLoaiChiPhi()) + "") : "")
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