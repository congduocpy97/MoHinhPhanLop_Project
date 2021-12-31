/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Convert {

    public static String getTendd(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenDiaDiem from diadiem where MaDiaDiem=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stat.close();

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getTenlh(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenLoaiHinh  from loaihinhdulich where MaLoaiHinh=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stat.close();

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getTentour(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenGoi  from tourdulich where MaTour=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stat.close();

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getTencp(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenLoaiChiPhi  from loaichiphi where MaLoaiChiPhi=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stat.close();

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static String getTenkhach(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT HoTen  from khach where MaKhachHang=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stat.close();

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static String getTennv(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenNhanVien  from nhanvien where MaNhanVien=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stat.close();

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
