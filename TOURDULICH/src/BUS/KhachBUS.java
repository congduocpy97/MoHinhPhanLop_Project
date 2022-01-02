/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import Tools.TableUtil;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.KhachDAO;
import DTO.KhachDTO;
/**
 *
 * @author ChiThien
 */
public class KhachBUS {
    public static KhachDTO getKhach(int madoan) {
        return KhachDAO.getKhach(madoan);
    }

    public static void init(JTable tbl) {
        ArrayList<KhachDTO> dskhach = KhachDAO.load();
        uploadTable(tbl, dskhach);
    }

    public ArrayList<KhachDTO> find(String ten) {
        KhachDAO khachDAO = new KhachDAO();
        return khachDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<KhachDTO> list) {
        String[] columnNames = {"Mã kháhc hàng","Họ tên","CMND/CCCD","Địa chỉ","Giới tính","SDT"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (KhachDTO khach : list) {
            data[i][0] = khach.getMakhachhang();
            data[i][1] = khach.getHoten();
            data[i][2] = khach.getSocmnd();
            data[i][3] = khach.getDiachi();
            data[i][4] = khach.getGioitinh();
            data[i][5] = khach.getSdt();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        KhachDAO khachDAO = new KhachDAO();
        ArrayList<KhachDTO> dskhach = khachDAO.load();
        uploadTable(tbl, dskhach);
    }

    public void add(String hoten,String socmnd,String diachi,String gioitinh,String sdt) {
        KhachDAO khachDAO = new KhachDAO();
        KhachDTO khach = new KhachDTO(khachDAO.getNewID());
        khach.setHoten(hoten);
        khach.setSocmnd(socmnd);
        khach.setDiachi(diachi);
        khach.setGioitinh(gioitinh);
        khach.setSdt(sdt);
        khachDAO.add(khach);
    }

    public static void delete(int makh) {
        KhachDAO khachDAO = new KhachDAO();
        khachDAO.delete(makh);
    }

    public void edit(int makhachhang,String hoten,String socmnd,String diachi,String gioitinh,String sdt) {
        KhachDAO khachDAO = new KhachDAO();
        KhachDTO khach = new KhachDTO(makhachhang);
        khach.setHoten(hoten);
        khach.setSocmnd(socmnd);
        khach.setDiachi(diachi);
        khach.setGioitinh(gioitinh);
        khach.setSdt(sdt);
        khachDAO.edit(khach);
    }

    public static void loadInfo(JTable tbl, JTextField formHoten,JTextField formCMND,JTextField formDiachi,JTextField formGioitinh,JTextField formSdt) {
        KhachDTO khach = KhachDAO.getKhach(TableUtil.getMaFromTable(tbl));
        formHoten.setText(khach.getHoten());
        formCMND.setText(khach.getSocmnd());
        formDiachi.setText(khach.getDiachi());
        formGioitinh.setText(khach.getGioitinh());
        formSdt.setText(String.valueOf(khach.getSdt()));
       
    }
}