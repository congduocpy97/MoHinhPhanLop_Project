/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Massan
 */
public class Database {

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    /**
     *
     */
    public static Connection connect() {
        String databaseName = "ql_tour_dulich";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useUnicode=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            System.out.println("Kết nối csdl dữ liệu thành công");
        } catch (ClassNotFoundException e) {
//			ThongBao.error("[Database:connect] Thiếu thư viện tích hợp: "+e);
            System.exit(1);
        } catch (SQLException e) {
//			ThongBao.error("[Database:connect] Lỗi kết nối database:\n"+e);
            System.exit(1);
        }
        return conn;
    }

    public static ResultSet execution(String sql) {
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
//			ThongBao.warning("[Database:execution] "+e);
        }
        return rs;
    }

    public static void update(String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
//			ThongBao.warning("[Database:update] "+e);
            //JOptionPane.showMessageDialog(null, "Loi");
        }
    }

    public static void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
//			ThongBao.warning("[Database:disconnect] "+e);
        }
    }

    public static void main(String[] args) {
        Database db = new Database();
        db.connect();
    }

}
