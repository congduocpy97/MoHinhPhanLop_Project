/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import gui.CHITIETDOAN;
import gui.MAIN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class Rename {

    public static String[] arrtendd;
    public static int[] madd;

    public static void diadiem() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenDiaDiem,MaDiaDiem from diadiem";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtendd = new String[tl.size()];
            madd = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtendd[i] = tl.get(i).getTen();
                madd[i] = tl.get(i).getMa();
                MAIN.jComboBoxdd.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public static void updatediadiem() {
        try {
            MAIN.jComboBoxdd.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenDiaDiem,MaDiaDiem from diadiem";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtendd = new String[tl.size()];
            madd = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtendd[i] = tl.get(i).getTen();
                madd[i] = tl.get(i).getMa();
                MAIN.jComboBoxdd.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }
    public static String[] arrtenlh;
    public static int[] malh;

    public static void loaihinh() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenLoaiHinh,MaLoaiHinh from loaihinhdulich";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtenlh = new String[tl.size()];
            malh = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtenlh[i] = tl.get(i).getTen();
                malh[i] = tl.get(i).getMa();
                MAIN.jComboBoxlh.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public static void updateloaihinh() {
        try {
            MAIN.jComboBoxlh.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenLoaiHinh,MaLoaiHinh from loaihinhdulich";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtenlh = new String[tl.size()];
            malh = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtenlh[i] = tl.get(i).getTen();
                malh[i] = tl.get(i).getMa();
                MAIN.jComboBoxlh.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }
    public static String[] arrtennv;
    public static int[] manv;

    public static void nhanvien() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenNhanVien,MaNhanVien from nhanvien";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtennv = new String[tl.size()];
            manv = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtennv[i] = tl.get(i).getTen();
                manv[i] = tl.get(i).getMa();
                CHITIETDOAN.jComboBoxnv2.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void updatenhanvien() {
        try {
            CHITIETDOAN.jComboBoxnv2.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenNhanVien,MaNhanVien from nhanvien";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtennv = new String[tl.size()];
            manv = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtennv[i] = tl.get(i).getTen();
                manv[i] = tl.get(i).getMa();
                CHITIETDOAN.jComboBoxnv2.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public static String[] arrtencp;
    public static int[] macp;

    public static void chiphi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenLoaiChiPhi,MaLoaiChiPhi from loaichiphi";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtencp = new String[tl.size()];
            macp = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtencp[i] = tl.get(i).getTen();
                macp[i] = tl.get(i).getMa();
                MAIN.jComboBoxcp.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public static void updatechiphi() {
        try {
            MAIN.jComboBoxcp.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenLoaiChiPhi,MaLoaiChiPhi from loaichiphi";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtencp = new String[tl.size()];
            macp = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtencp[i] = tl.get(i).getTen();
                macp[i] = tl.get(i).getMa();
                MAIN.jComboBoxcp.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }
    public static String[] arrtentour;
    public static int[] matour;

    public static void tour() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenGoi,MaTour from tourdulich";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtentour = new String[tl.size()];
            matour = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtentour[i] = tl.get(i).getTen();
                matour[i] = tl.get(i).getMa();
                MAIN.jComboBoxtour2.addItem(tl.get(i).getTen());
                MAIN.jComboBoxtour.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public static void updatetour() {
        try {
            MAIN.jComboBoxtour.removeAllItems();
            MAIN.jComboBoxtour2.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT TenGoi,MaTour from tourdulich";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtentour = new String[tl.size()];
            matour = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtentour[i] = tl.get(i).getTen();
                matour[i] = tl.get(i).getMa();
                MAIN.jComboBoxtour2.addItem(tl.get(i).getTen());
                MAIN.jComboBoxtour.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }
    public static String[] arrtenkhach;
    public static int[] makhach;

    public static void khach() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT HoTen,MaKhachHang from khach";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtenkhach = new String[tl.size()];
            makhach = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtenkhach[i] = tl.get(i).getTen();
                makhach[i] = tl.get(i).getMa();
                CHITIETDOAN.jComboBoxkhach.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {

            System.err.println(e);
        }
    }

    public static void updatekhach() {
        try {
            CHITIETDOAN.jComboBoxkhach.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_tour_dulich?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT HoTen,MaKhachHang from khach";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrtenkhach = new String[tl.size()];
            makhach = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrtenkhach[i] = tl.get(i).getTen();
                makhach[i] = tl.get(i).getMa();
                CHITIETDOAN.jComboBoxkhach.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {

            System.err.println(e);
        }
    }
}
