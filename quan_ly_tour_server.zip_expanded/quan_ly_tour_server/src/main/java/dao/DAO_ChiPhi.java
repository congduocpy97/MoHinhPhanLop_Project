package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ChiPhi;

public class DAO_ChiPhi {

	static final String TABLE_NAME = "chiphi";

	public static ArrayList<ChiPhi> search(ChiPhi chiphi){
		ArrayList<ChiPhi> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (chiphi.getMaChiPhi() != -1 ? ("`MaChiPhi` = " + String.valueOf(chiphi.getMaChiPhi()) + " and ") : " 1 and ")
			+ (chiphi.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(chiphi.getMaDoan()) + " and ") : " 1 and ")
			+ (chiphi.getSoTien() != -1 ? ("`SoTien` = " + String.valueOf(chiphi.getSoTien()) + " and ") : " 1 and ")
			+ (chiphi.getMaLoaiChiPhi() != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(chiphi.getMaLoaiChiPhi()) + "") : "1");
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
					arr.add(new ChiPhi(rs.getInt("MaChiPhi"), rs.getInt("MaDoan"), rs.getDouble("SoTien"), rs.getInt("MaLoaiChiPhi")));
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

	public static ChiPhi findById(int machiphi) {
		ChiPhi result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (machiphi != -1 ? ("`MaChiPhi` = " + String.valueOf(machiphi) + "") : "")
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
					result = new ChiPhi(rs.getInt("MaChiPhi"), rs.getInt("MaDoan"), rs.getDouble("SoTien"), rs.getInt("MaLoaiChiPhi"));
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

	public static int create(ChiPhi chiphi) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (chiphi.getMaDoan() != -1 ? "`MaDoan`, " : "")
			+ (chiphi.getSoTien() != -1 ? "`SoTien`, " : "")
			+ (chiphi.getMaLoaiChiPhi() != -1 ? "`MaLoaiChiPhi`" : "")
			+ ") VALUES ("
			+ (chiphi.getMaDoan() != -1 ? String.valueOf(chiphi.getMaDoan()) + ", " : "")
			+ (chiphi.getSoTien() != -1 ? String.valueOf(chiphi.getSoTien()) + ", " : "")
			+ (chiphi.getMaLoaiChiPhi() != -1 ? String.valueOf(chiphi.getMaLoaiChiPhi()) + "" : "")
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

	public static int update(ChiPhi chiphinew, ChiPhi chiphiold) {
		int code = 0;
		if(!(chiphiold.getMaChiPhi() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (chiphinew.getMaChiPhi() != -1 ? ("`MaChiPhi` = " + String.valueOf(chiphinew.getMaChiPhi()) + ", ") : "")
			+ (chiphinew.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(chiphinew.getMaDoan()) + ", ") : "")
			+ (chiphinew.getSoTien() != -1 ? ("`SoTien` = " + String.valueOf(chiphinew.getSoTien()) + ", ") : "")
			+ (chiphinew.getMaLoaiChiPhi() != -1 ? ("`MaLoaiChiPhi` = " + String.valueOf(chiphinew.getMaLoaiChiPhi()) + "") : "")
			+ " WHERE ";
		String where = ""
			+ (chiphiold.getMaChiPhi() != -1 ? ("`MaChiPhi` = " + String.valueOf(chiphiold.getMaChiPhi()) + "") : "")
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

	public static int delete(ChiPhi chiphi) {
		int code = 0;
		if(!(chiphi.getMaChiPhi() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (chiphi.getMaChiPhi() != -1 ? ("`MaChiPhi` = " + String.valueOf(chiphi.getMaChiPhi()) + "") : "")
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