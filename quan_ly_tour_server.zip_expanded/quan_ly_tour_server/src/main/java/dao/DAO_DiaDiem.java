package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.DiaDiem;

public class DAO_DiaDiem {

	static final String TABLE_NAME = "diadiem";

	public static ArrayList<DiaDiem> search(DiaDiem diadiem){
		ArrayList<DiaDiem> arr = new ArrayList<>();
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (diadiem.getMaDiaDiem() != -1  ? ("`MaDiaDiem` = " + String.valueOf(diadiem.getMaDiaDiem()) + " and ") : " 1 and ")
			+ (diadiem.getTen() != null && ! diadiem.getTen().contentEquals("") ? ("`Ten` like N\'%" + diadiem.getTen() + "%\'" + " and ") : " 1 and ")
			+ (diadiem.getTrangThai() != null && ! diadiem.getTrangThai().contentEquals("") ? ("`TrangThai` like N\'%" + diadiem.getTrangThai() + "%\'" + "") : "1");
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
					arr.add(new DiaDiem(rs.getInt("MaDiaDiem"), rs.getString("Ten"), rs.getString("TrangThai")));
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

	public static DiaDiem findById(int madiadiem) {
		DiaDiem result = null;
		String sql = "select * from " + TABLE_NAME + " where ";
		String where = ""
			+ (madiadiem != -1 ? ("`MaDiaDiem` = " + String.valueOf(madiadiem)  + "") : "")
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
					result = new DiaDiem(rs.getInt("MaDiaDiem"), rs.getString("Ten"), rs.getString("TrangThai"));
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

	public static int create(DiaDiem diadiem) {
		int code = 0;
		String sql = ""
			+ "INSERT INTO `" + TABLE_NAME + "` (" 
			+ (diadiem.getTen() != null && ! diadiem.getTen().contentEquals("") ? "`Ten`, " : "")
			+ (diadiem.getTrangThai() != null && ! diadiem.getTrangThai().contentEquals("") ? "`TrangThai`" : "")
			+ ") VALUES ("
			+ (diadiem.getTen() != null && ! diadiem.getTen().contentEquals("") ? "N\'" + diadiem.getTen() + "\'" + ", " : "")
			+ (diadiem.getTrangThai() != null && ! diadiem.getTrangThai().contentEquals("") ? "N\'" + diadiem.getTrangThai() + "\'" + "" : "")
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

	public static int update(DiaDiem diadiemnew, DiaDiem diadiemold) {
		int code = 0;
		if(!((diadiemold.getMaDiaDiem() != -1)))
			return code;
		String sql = ""
			+ "UPDATE `" + TABLE_NAME + "` SET "
			+ (diadiemnew.getMaDiaDiem() != -1  ? ("`MaDiaDiem` = " + diadiemnew.getMaDiaDiem() + ", ") : "")
			+ (diadiemnew.getTen() != null && ! diadiemnew.getTen().contentEquals("") ? ("`Ten` = N\'" + diadiemnew.getTen() + "\'" + ", ") : "")
			+ (diadiemnew.getTrangThai() != null && ! diadiemnew.getTrangThai().contentEquals("") ? ("`TrangThai` = N\'" + diadiemnew.getTrangThai() + "\'" + "") : "")
			+ " WHERE ";
		String where = ""
			+ (diadiemold.getMaDiaDiem() != -1l  ? ("`MaDiaDiem` = " + diadiemold.getMaDiaDiem()  + "") : "")
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

	public static int delete(DiaDiem diadiem) {
		int code = 0;
		if(!((diadiem.getMaDiaDiem() != -1)))
			return code;
		String sql = ""
			+ "DELETE FROM `" + TABLE_NAME + "` WHERE ";
		String where = ""
			+ (diadiem.getMaDiaDiem() != -1 ? ("`MaDiaDiem` = " + diadiem.getMaDiaDiem() + "") : "")
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