package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.NoiDungTour;

public class DAO_NoiDungTour {

	static final String TABLE_NAME = "noidungtour";

	public static ArrayList<NoiDungTour> search(NoiDungTour noidungtour){
		ArrayList<NoiDungTour> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (noidungtour.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(noidungtour.getMaDoan()) + " and ") : " 1 and ")
			+ (noidungtour.getHanhTrinh() != null && ! noidungtour.getHanhTrinh().contentEquals("") ? ("`HanhTrinh` like N\'%" + noidungtour.getHanhTrinh() + "%\'" + " and ") : " 1 and ")
			+ (noidungtour.getKhachSan() != null && ! noidungtour.getKhachSan().contentEquals("") ? ("`KhachSan` like N\'%" + noidungtour.getKhachSan() + "%\'" + " and ") : " 1 and ")
			+ (noidungtour.getDiaDiemThamQuan() != null && ! noidungtour.getDiaDiemThamQuan().contentEquals("") ? ("`DiaDiemThamQuan` like N\'%" + noidungtour.getDiaDiemThamQuan() + "%\'" + "") : " 1 ");
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
					arr.add(new NoiDungTour(rs.getInt("MaDoan"), rs.getString("HanhTrinh"), rs.getString("KhachSan"), rs.getString("DiaDiemThamQuan")));
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

	public static NoiDungTour findById(int madoan) {
		NoiDungTour result = null;
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
					result = new NoiDungTour(rs.getInt("MaDoan"), rs.getString("HanhTrinh"), rs.getString("KhachSan"), rs.getString("DiaDiemThamQuan"));
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

	public static int create(NoiDungTour noidungtour) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (noidungtour.getMaDoan() != -1 ? "`MaDoan`" : "")
			+ (noidungtour.getHanhTrinh() != null && ! noidungtour.getHanhTrinh().contentEquals("") ? ", " + "`HanhTrinh`" : "")
			+ (noidungtour.getKhachSan() != null && ! noidungtour.getKhachSan().contentEquals("") ? ", " + "`KhachSan`" : "")
			+ (noidungtour.getDiaDiemThamQuan() != null && ! noidungtour.getDiaDiemThamQuan().contentEquals("") ? ", " + "`DiaDiemThamQuan`" : "")
			+ ") VALUES ("
			+ (noidungtour.getMaDoan() != -1 ? String.valueOf(noidungtour.getMaDoan()) : "")
			+ (noidungtour.getHanhTrinh() != null && ! noidungtour.getHanhTrinh().contentEquals("") ? ", " + "N\'" + noidungtour.getHanhTrinh() + "\'"  : "")
			+ (noidungtour.getKhachSan() != null && ! noidungtour.getKhachSan().contentEquals("") ? ", " + "N\'" + noidungtour.getKhachSan() + "\'" : "")
			+ (noidungtour.getDiaDiemThamQuan() != null && ! noidungtour.getDiaDiemThamQuan().contentEquals("") ? ", " + "N\'" + noidungtour.getDiaDiemThamQuan() + "\'" + "" : "")
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

	public static int update(NoiDungTour noidungtournew, NoiDungTour noidungtourold) {
		int code = 0;
		if(!(noidungtourold.getMaDoan() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (noidungtournew.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(noidungtournew.getMaDoan())) : "")
			+ (noidungtournew.getHanhTrinh() != null && ! noidungtournew.getHanhTrinh().contentEquals("") ? (", " + "`HanhTrinh` = N\'" + noidungtournew.getHanhTrinh() + "\'") : "")
			+ (noidungtournew.getKhachSan() != null && ! noidungtournew.getKhachSan().contentEquals("") ? (", " + "`KhachSan` = N\'" + noidungtournew.getKhachSan() + "\'") : "")
			+ (noidungtournew.getDiaDiemThamQuan() != null && ! noidungtournew.getDiaDiemThamQuan().contentEquals("") ? (", " + "`DiaDiemThamQuan` = N\'" + noidungtournew.getDiaDiemThamQuan() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (noidungtourold.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(noidungtourold.getMaDoan()) + "") : "")
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

	public static int delete(NoiDungTour noidungtour) {
		int code = 0;
		if(!(noidungtour.getMaDoan() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (noidungtour.getMaDoan() != -1 ? ("`MaDoan` = " + String.valueOf(noidungtour.getMaDoan()) + "") : "")
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