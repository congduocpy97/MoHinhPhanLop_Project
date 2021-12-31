package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.GiaTour;

public class DAO_GiaTour {

	static final String TABLE_NAME = "giatour";

	public static ArrayList<GiaTour> search(GiaTour giatour){
		ArrayList<GiaTour> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (giatour.getMaGia() != -1 ? ("`MaGia` = " + String.valueOf(giatour.getMaGia()) + " and ") : " 1 and ")
			+ (giatour.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(giatour.getMaTour()) + " and ") : " 1 and ")
			+ (giatour.getThanhTien() != -1 ? ("`ThanhTien` = " + String.valueOf(giatour.getThanhTien()) + " and ") : " 1 and ")
			+ (giatour.getThoiGianBatDau() != null ? ("`ThoiGianBatDau` = \'" + giatour.getThoiGianBatDau().toString() + "\' and ") : " 1 and ")
			+ (giatour.getThoiGianKetThuc() != null ? ("`ThoiGianKetThuc` = \'" + giatour.getThoiGianKetThuc().toString() + "\' and ") : " 1 and ")
			+ (giatour.getTrangThai() != null && ! giatour.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + giatour.getTrangThai() + "%\'" + "") : "1");
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
					arr.add(new GiaTour(rs.getInt("MaGia"), rs.getInt("MaTour"), rs.getDouble("ThanhTien"), rs.getDate("ThoiGianBatDau"), rs.getDate("ThoiGianKetThuc"), rs.getString("TrangThai")));
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

	public static GiaTour findById(int magia) {
		GiaTour result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (magia != -1 ? ("`MaGia` = " + String.valueOf(magia) + "") : "")
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
					result = new GiaTour(rs.getInt("MaGia"), rs.getInt("MaTour"), rs.getDouble("ThanhTien"), rs.getDate("ThoiGianBatDau"), rs.getDate("ThoiGianKetThuc"), rs.getString("TrangThai"));
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

	public static int create(GiaTour giatour) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (giatour.getMaTour() != -1 ? "`MaTour`, " : "")
			+ (giatour.getThanhTien() != -1 ? "`ThanhTien`, " : "")
			+ (giatour.getThoiGianBatDau() != null ? "`ThoiGianBatDau`, " : "")
			+ (giatour.getThoiGianKetThuc() != null ? "`ThoiGianKetThuc`, " : "")
			+ (giatour.getTrangThai() != null && ! giatour.getTrangThai().contentEquals("") ? "`TrangThai`" : "")
			+ ") VALUES ("
			+ (giatour.getMaTour() != -1 ? String.valueOf(giatour.getMaTour()) + ", " : "")
			+ (giatour.getThanhTien() != -1 ? String.valueOf(giatour.getThanhTien()) + ", " : "")
			+ (giatour.getThoiGianBatDau() != null ? "\'" + giatour.getThoiGianBatDau().toString() + "\', " : "")
			+ (giatour.getThoiGianKetThuc() != null ? "\'" + giatour.getThoiGianKetThuc().toString() + "\', " : "")
			+ (giatour.getTrangThai() != null && ! giatour.getTrangThai().contentEquals("") ? "N\'" + giatour.getTrangThai() + "\'" + "" : "")
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

	public static int update(GiaTour giatournew, GiaTour giatourold) {
		int code = 0;
		if(!(giatourold.getMaGia() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (giatournew.getMaGia() != -1 ? ("`MaGia` = " + String.valueOf(giatournew.getMaGia()) + ", ") : "")
			+ (giatournew.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(giatournew.getMaTour()) + ", ") : "")
			+ (giatournew.getThanhTien() != -1 ? ("`ThanhTien` = " + String.valueOf(giatournew.getThanhTien()) + ", ") : "")
			+ (giatournew.getThoiGianBatDau() != null ? ("`ThoiGianBatDau` = \'" + giatournew.getThoiGianBatDau().toString() + "\', ") : "")
			+ (giatournew.getThoiGianKetThuc() != null ? ("`ThoiGianKetThuc` = \'" + giatournew.getThoiGianKetThuc().toString() + "\', ") : "")
			+ (giatournew.getTrangThai() != null && ! giatournew.getTrangThai().contentEquals("") ? ("`TrangThai` = N\'" + giatournew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (giatourold.getMaGia() != -1 ? ("`MaGia` = " + String.valueOf(giatourold.getMaGia()) + "") : "")
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

	public static int delete(GiaTour giatour) {
		int code = 0;
		if(!(giatour.getMaGia() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (giatour.getMaGia() != -1 ? ("`MaGia` = " + String.valueOf(giatour.getMaGia()) + "") : "")
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