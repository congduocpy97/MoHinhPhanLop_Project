package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.KhachHang;

public class DAO_KhachHang {

	static final String TABLE_NAME = "khachhang";

	public static ArrayList<KhachHang> search(KhachHang khachhang){
		ArrayList<KhachHang> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (khachhang.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(khachhang.getMaKhachHang()) + " and ") : " 1 and ")
			+ (khachhang.getHoTen() != null && ! khachhang.getHoTen().contentEquals("") ? ("`HoTen` like N\'%" + khachhang.getHoTen() + "%\'" + " and ") : " 1 and ")
			+ (khachhang.getSoCMND() != null && ! khachhang.getSoCMND().contentEquals("") ? ("`SoCMND` like N\'%" + khachhang.getSoCMND() + "%\'" + " and ") : " 1 and ")
			+ (khachhang.getDiaChi() != null && ! khachhang.getDiaChi().contentEquals("") ? ("`DiaChi` like N\'%" + khachhang.getDiaChi() + "%\'" + " and ") : " 1 and ")
			+ (khachhang.getGioiTinh() != null && ! khachhang.getGioiTinh().contentEquals("") ? ("`GioiTinh` like N\'%" + khachhang.getGioiTinh() + "%\'" + " and ") : " 1 and ")
			+ (khachhang.getSDT() != null && ! khachhang.getSDT().contentEquals("") ? ("`SDT` like N\'%" + khachhang.getSDT() + "%\'" + " and ") : " 1 and ")
			+ (khachhang.getQuocTich() != null && ! khachhang.getQuocTich().contentEquals("") ? ("`QuocTich` like N\'%" + khachhang.getQuocTich() + "%\'" + " and ") : " 1 and ")
			+ (khachhang.getTrangThai() != null && ! khachhang.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + khachhang.getTrangThai() + "%\'" + "") : "1");
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
					arr.add(new KhachHang(rs.getInt("MaKhachHang"), rs.getString("HoTen"), rs.getString("SoCMND"), rs.getString("DiaChi"), rs.getString("GioiTinh"), rs.getString("SDT"), rs.getString("QuocTich"), rs.getString("TrangThai")));
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

	public static KhachHang findById(int makhachhang) {
		KhachHang result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
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
					result = new KhachHang(rs.getInt("MaKhachHang"), rs.getString("HoTen"), rs.getString("SoCMND"), rs.getString("DiaChi"), rs.getString("GioiTinh"), rs.getString("SDT"), rs.getString("QuocTich"), rs.getString("TrangThai"));
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

	public static int create(KhachHang khachhang) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (khachhang.getHoTen() != null && ! khachhang.getHoTen().contentEquals("") ? "`HoTen`, " : "")
			+ (khachhang.getSoCMND() != null && ! khachhang.getSoCMND().contentEquals("") ? "`SoCMND`, " : "")
			+ (khachhang.getDiaChi() != null && ! khachhang.getDiaChi().contentEquals("") ? "`DiaChi`, " : "")
			+ (khachhang.getGioiTinh() != null && ! khachhang.getGioiTinh().contentEquals("") ? "`GioiTinh`, " : "")
			+ (khachhang.getSDT() != null && ! khachhang.getSDT().contentEquals("") ? "`SDT`, " : "")
			+ (khachhang.getQuocTich() != null && ! khachhang.getQuocTich().contentEquals("") ? "`QuocTich`, " : "")
			+ (khachhang.getTrangThai() != null && ! khachhang.getTrangThai().contentEquals("") ? "`TrangThai`" : "")
			+ ") VALUES ("
			+ (khachhang.getHoTen() != null && ! khachhang.getHoTen().contentEquals("") ? "N\'" + khachhang.getHoTen() + "\'" + ", " : "")
			+ (khachhang.getSoCMND() != null && ! khachhang.getSoCMND().contentEquals("") ? "N\'" + khachhang.getSoCMND() + "\'" + ", " : "")
			+ (khachhang.getDiaChi() != null && ! khachhang.getDiaChi().contentEquals("") ? "N\'" + khachhang.getDiaChi() + "\'" + ", " : "")
			+ (khachhang.getGioiTinh() != null && ! khachhang.getGioiTinh().contentEquals("") ? "N\'" + khachhang.getGioiTinh() + "\'" + ", " : "")
			+ (khachhang.getSDT() != null && ! khachhang.getSDT().contentEquals("") ? "N\'" + khachhang.getSDT() + "\'" + ", " : "")
			+ (khachhang.getQuocTich() != null && ! khachhang.getQuocTich().contentEquals("") ? "N\'" + khachhang.getQuocTich() + "\'" + ", " : "")
			+ (khachhang.getTrangThai() != null && ! khachhang.getTrangThai().contentEquals("") ? "N\'" + khachhang.getTrangThai() + "\'" + "" : "")
			+ ")";
		System.out.println(sql);
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

	public static int update(KhachHang khachhangnew, KhachHang khachhangold) {
		int code = 0;
		if(!(khachhangold.getMaKhachHang() != -1))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (khachhangnew.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(khachhangnew.getMaKhachHang()) + ", ") : "")
			+ (khachhangnew.getHoTen() != null && ! khachhangnew.getHoTen().contentEquals("") ? ("`HoTen` = N\'" + khachhangnew.getHoTen() + "\'" + ", ") : "")
			+ (khachhangnew.getSoCMND() != null && ! khachhangnew.getSoCMND().contentEquals("") ? ("`SoCMND` = N\'" + khachhangnew.getSoCMND() + "\'" + ", ") : "")
			+ (khachhangnew.getDiaChi() != null && ! khachhangnew.getDiaChi().contentEquals("") ? ("`DiaChi` = N\'" + khachhangnew.getDiaChi() + "\'" + ", ") : "")
			+ (khachhangnew.getGioiTinh() != null && ! khachhangnew.getGioiTinh().contentEquals("") ? ("`GioiTinh` = N\'" + khachhangnew.getGioiTinh() + "\'" + ", ") : "")
			+ (khachhangnew.getSDT() != null && ! khachhangnew.getSDT().contentEquals("") ? ("`SDT` = N\'" + khachhangnew.getSDT() + "\'" + ", ") : "")
			+ (khachhangnew.getQuocTich() != null && ! khachhangnew.getQuocTich().contentEquals("") ? ("`QuocTich` = N\'" + khachhangnew.getQuocTich() + "\'" + ", ") : "")
			+ (khachhangnew.getTrangThai() != null && ! khachhangnew.getTrangThai().contentEquals("") ? ("`TrangThai` = N\'" + khachhangnew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (khachhangold.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(khachhangold.getMaKhachHang()) + "") : "")
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

	public static int delete(KhachHang khachhang) {
		int code = 0;
		if(!(khachhang.getMaKhachHang() != -1))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (khachhang.getMaKhachHang() != -1 ? ("`MaKhachHang` = " + String.valueOf(khachhang.getMaKhachHang()) + "") : "")
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