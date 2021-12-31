package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.DoanDuLich;

public class DAO_DoanDuLich {

	static final String TABLE_NAME = "doandulich";

	public static ArrayList<DoanDuLich> search(DoanDuLich doandulich){
		ArrayList<DoanDuLich> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (doandulich.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(doandulich.getMaDoan()) + " and ") : " 1 and ")
			+ (doandulich.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(doandulich.getMaTour()) + " and ") : " 1 and ")
			+ (doandulich.getNgayKhoiHanh() != null ? ("`NgayKhoiHanh` = \'" + doandulich.getNgayKhoiHanh().toString() + "\' and ") : " 1 and ")
			+ (doandulich.getNgayKetThuc() != null ? ("`NgayKetThuc` = \'" + doandulich.getNgayKetThuc().toString() + "\' and ") : " 1 and ")
			+ (doandulich.getDoanhThu() != -1 ? ("`DoanhThu` = " + String.valueOf(doandulich.getDoanhThu()) + " and ") : " 1 and ")
			+ (doandulich.getTrangThai() != null && ! doandulich.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + doandulich.getTrangThai() + "%\'" + "") : "1");
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
					arr.add(new DoanDuLich(rs.getInt("MaDoan"), rs.getInt("MaTour"), rs.getDate("NgayKhoiHanh"), rs.getDate("NgayKetThuc"), rs.getDouble("DoanhThu"), rs.getString("TrangThai")));
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

	public static DoanDuLich findById(int madoan) {
		DoanDuLich result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (madoan != -1 ? ("`MaDoan` = " + String.valueOf(madoan) + "") : "")
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
					result = new DoanDuLich(rs.getInt("MaDoan"), rs.getInt("MaTour"), rs.getDate("NgayKhoiHanh"), rs.getDate("NgayKetThuc"), rs.getDouble("DoanhThu"), rs.getString("TrangThai"));
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

	public static int create(DoanDuLich doandulich) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (doandulich.getMaTour() != -1 ? "`MaTour`, " : "")
			+ (doandulich.getNgayKhoiHanh() != null ? "`NgayKhoiHanh`, " : "")
			+ (doandulich.getNgayKetThuc() != null ? "`NgayKetThuc`, " : "")
			+ (doandulich.getDoanhThu() != -1 ? "`DoanhThu`, " : "")
			+ (doandulich.getTrangThai() != null && ! doandulich.getTrangThai().contentEquals("") ? "`TrangThai`" : "")
			+ ") VALUES ("
			+ (doandulich.getMaTour() != -1 ? String.valueOf(doandulich.getMaTour()) + ", " : "")
			+ (doandulich.getNgayKhoiHanh() != null ? "\'" + doandulich.getNgayKhoiHanh().toString() + "\', " : "")
			+ (doandulich.getNgayKetThuc() != null ? "\'" + doandulich.getNgayKetThuc().toString() + "\', " : "")
			+ (doandulich.getDoanhThu() != -1 ? String.valueOf(doandulich.getDoanhThu()) + ", " : "")
			+ (doandulich.getTrangThai() != null && ! doandulich.getTrangThai().contentEquals("") ? "N\'" + doandulich.getTrangThai() + "\'" + "" : "")
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

	public static int update(DoanDuLich doandulichnew, DoanDuLich doandulichold) {
		int code = 0;
		if(!(doandulichold.getMaDoan() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (doandulichnew.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(doandulichnew.getMaDoan()) + ", ") : "")
			+ (doandulichnew.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(doandulichnew.getMaTour()) + ", ") : "")
			+ (doandulichnew.getNgayKhoiHanh() != null ? ("`NgayKhoiHanh` = \'" + doandulichnew.getNgayKhoiHanh().toString() + "\', ") : "")
			+ (doandulichnew.getNgayKetThuc() != null ? ("`NgayKetThuc` = \'" + doandulichnew.getNgayKetThuc().toString() + "\', ") : "")
			+ (doandulichnew.getDoanhThu() != -1 ? ("`DoanhThu` = " + String.valueOf(doandulichnew.getDoanhThu()) + ", ") : "")
			+ (doandulichnew.getTrangThai() != null && ! doandulichnew.getTrangThai().contentEquals("") ? ("`TrangThai` = N\'" + doandulichnew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (doandulichold.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(doandulichold.getMaDoan()) + "") : "")
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

	public static int delete(DoanDuLich doandulich) {
		int code = 0;
		if(!(doandulich.getMaDoan() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (doandulich.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(doandulich.getMaDoan()) + "") : "")
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
	
	public static int getLastInsertID() {
		Connection conn = Connector.connect();
		int id = 0;
		if( conn != null) {
			try {
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT max(madoan) FROM `doandulich`; ");
				while(rs.next()) {
					id = rs.getInt(1);
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
		return id;
	}
}