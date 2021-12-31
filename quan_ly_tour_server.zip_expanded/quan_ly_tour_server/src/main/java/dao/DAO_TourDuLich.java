package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.TourDuLich;

public class DAO_TourDuLich {

	static final String TABLE_NAME = "tourdulich";

	public static ArrayList<TourDuLich> search(TourDuLich tourdulich){
		ArrayList<TourDuLich> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (tourdulich.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(tourdulich.getMaTour()) + " and ") : " 1 and ")
			+ (tourdulich.getTenGoi() != null && ! tourdulich.getTenGoi().contentEquals("") ? ("`TenGoi` like N\'%" + tourdulich.getTenGoi() + "%\'" + " and ") : " 1 and ")
			+ (tourdulich.getDacDiem() != null && ! tourdulich.getDacDiem().contentEquals("") ? ("`DacDiem` like N\'%" + tourdulich.getDacDiem() + "%\'" + " and ") : " 1 and ")
			+ (tourdulich.getMaLoaiHinh() != -1 ? ("`MaLoaiHinh` = " + String.valueOf(tourdulich.getMaLoaiHinh()) + " and ") : " 1 and ")
			+ (tourdulich.getTrangThai() != null && ! tourdulich.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + tourdulich.getTrangThai() + "%\'" + "") : " 1 ");
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
					arr.add(new TourDuLich(rs.getInt("MaTour"), rs.getString("TenGoi"), rs.getString("DacDiem"), rs.getInt("MaLoaiHinh"), rs.getString("TrangThai")));
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

	public static TourDuLich findById(int matour) {
		TourDuLich result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (matour != -1 ? ("`MaTour` = " + String.valueOf(matour) + "") : "")
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
					result = new TourDuLich(rs.getInt("MaTour"), rs.getString("TenGoi"), rs.getString("DacDiem"), rs.getInt("MaLoaiHinh"), rs.getString("TrangThai"));
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

	public static int create(TourDuLich tourdulich) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (tourdulich.getTenGoi() != null && ! tourdulich.getTenGoi().contentEquals("") ? "`TenGoi`" : "")
			+ (tourdulich.getDacDiem() != null && ! tourdulich.getDacDiem().contentEquals("") ? ", " + "`DacDiem`" : "")
			+ (tourdulich.getMaLoaiHinh() != -1 ? ", " + "`MaLoaiHinh`" : "")
			+ (tourdulich.getTrangThai() != null && ! tourdulich.getTrangThai().contentEquals("") ? ", " + "`TrangThai`" : "")
			+ ") VALUES ("
			+ (tourdulich.getTenGoi() != null && ! tourdulich.getTenGoi().contentEquals("") ? "N\'" + tourdulich.getTenGoi() + "\'" : "")
			+ (tourdulich.getDacDiem() != null && ! tourdulich.getDacDiem().contentEquals("") ? ", N\'" + tourdulich.getDacDiem() + "\'": "")
			+ (tourdulich.getMaLoaiHinh() != -1 ? ", " + String.valueOf(tourdulich.getMaLoaiHinh()): "")
			+ (tourdulich.getTrangThai() != null && ! tourdulich.getTrangThai().contentEquals("") ? ", N\'" + tourdulich.getTrangThai() + "\'" + "" : "")
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

	public static int update(TourDuLich tourdulichnew, TourDuLich tourdulichold) {
		int code = 0;
		if(!(tourdulichold.getMaTour() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (tourdulichnew.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(tourdulichnew.getMaTour())) : "")
			+ (tourdulichnew.getTenGoi() != null && ! tourdulichnew.getTenGoi().contentEquals("") ? (", " + "`TenGoi` = N\'" + tourdulichnew.getTenGoi() + "\'") : "")
			+ (tourdulichnew.getDacDiem() != null && ! tourdulichnew.getDacDiem().contentEquals("") ? (", " + "`DacDiem` = N\'" + tourdulichnew.getDacDiem() + "\'") : "")
			+ (tourdulichnew.getMaLoaiHinh() != -1 ? (", " + "`MaLoaiHinh` = " + String.valueOf(tourdulichnew.getMaLoaiHinh())) : "")
			+ (tourdulichnew.getTrangThai() != null && ! tourdulichnew.getTrangThai().contentEquals("") ? (", " + "`TrangThai` = N\'" + tourdulichnew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (tourdulichold.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(tourdulichold.getMaTour()) + "") : "")
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

	public static int delete(TourDuLich tourdulich) {
		int code = 0;
		if(!(tourdulich.getMaTour() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (tourdulich.getMaTour() != -1 ? ("`MaTour` = " + String.valueOf(tourdulich.getMaTour()) + "") : "")
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
				ResultSet rs = stat.executeQuery("SELECT max(matour) FROM `tourdulich`; ");
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