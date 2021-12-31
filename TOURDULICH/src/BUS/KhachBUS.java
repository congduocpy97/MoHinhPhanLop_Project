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
        ArrayList<KhachDTO> dssp = KhachDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<KhachDTO> find(String ten) {
        KhachDAO spDAO = new KhachDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<KhachDTO> list) {
        String[] columnNames = {"Mã kháhc hàng","Họ tên","CMND/CCCD","Địa chỉ","Giới tính","SDT"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (KhachDTO sp : list) {
            data[i][0] = sp.getMakhachhang();
            data[i][1] = sp.getHoten();
            data[i][2] = sp.getSocmnd();
            data[i][3] = sp.getDiachi();
            data[i][4] = sp.getGioitinh();
            data[i][5] = sp.getSdt();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        KhachDAO spDAO = new KhachDAO();
        ArrayList<KhachDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String hoten,String socmnd,String diachi,String gioitinh,String sdt) {
        KhachDAO spDAO = new KhachDAO();
        KhachDTO sp = new KhachDTO(spDAO.getNewID());
        sp.setHoten(hoten);
        sp.setSocmnd(socmnd);
        sp.setDiachi(diachi);
        sp.setGioitinh(gioitinh);
        sp.setSdt(sdt);
        spDAO.add(sp);
    }

    public static void delete(int makh) {
        KhachDAO spDAO = new KhachDAO();
        spDAO.delete(makh);
    }

    public void edit(int makhachhang,String hoten,String socmnd,String diachi,String gioitinh,String sdt) {
        KhachDAO spDAO = new KhachDAO();
        KhachDTO sp = new KhachDTO(makhachhang);
        sp.setHoten(hoten);
        sp.setSocmnd(socmnd);
        sp.setDiachi(diachi);
        sp.setGioitinh(gioitinh);
        sp.setSdt(sdt);
        spDAO.edit(sp);
    }

    public static void loadInfo(JTable tbl, JTextField formHoten,JTextField formCMND,JTextField formDiachi,JTextField formGioitinh,JTextField formSdt) {
        KhachDTO sp = KhachDAO.getKhach(TableUtil.getMaFromTable(tbl));
        formHoten.setText(sp.getHoten());
        formCMND.setText(sp.getSocmnd());
        formDiachi.setText(sp.getDiachi());
        formGioitinh.setText(sp.getGioitinh());
        formSdt.setText(String.valueOf(sp.getSdt()));
       
    }
}