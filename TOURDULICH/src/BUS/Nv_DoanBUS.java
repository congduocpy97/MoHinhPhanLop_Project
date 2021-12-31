/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import Tools.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import dao.Nv_DoanDAO;
import DTO.Nv_DoanDTO;

/**
 *
 * @author ChiThien
 */
public class Nv_DoanBUS {

    public static Nv_DoanDTO getKhach_Doan(int makhachdoan) {
        return Nv_DoanDAO.getKhach_Doan(makhachdoan);
    }

    public static void init(JTable tbl, int ma) {
        ArrayList<Nv_DoanDTO> dssp = Nv_DoanDAO.load(ma);
        uploadTable(tbl, dssp, ma);
    }
    public static void uploadTable(JTable tbl, ArrayList<Nv_DoanDTO> list, int ma) {
        String[] columnNames = {"Đoàn", "Nhân viên","Nhiệm vụ"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
       gui.CHITIETDOAN.arrTenNhanvien= new ArrayList<>();
        for (Nv_DoanDTO sp : list) {
            data[i][0] = sp.getMadoan();
            data[i][1] = Convert.getTennv(sp.getManhanvien());
            data[i][2] = sp.getNhiemvu();
            gui.CHITIETDOAN.arrTenNhanvien.add(sp.getManhanvien());

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl, int ma) throws Exception {
        Nv_DoanDAO spDAO = new Nv_DoanDAO();
        ArrayList<Nv_DoanDTO> dssp = spDAO.load(ma);
        uploadTable(tbl, dssp, ma);
    }

    public void add(int madoan, int makhachhang,String nhiemvu) {
        Nv_DoanDAO spDAO = new Nv_DoanDAO();
        Nv_DoanDTO sp = new Nv_DoanDTO();
        sp.setMadoan(madoan);
        sp.setManhanvien(makhachhang);
        sp.setNhiemvu(nhiemvu);
        spDAO.add(sp);
    }

    public static void delete(int macp,int madoan) {
        Nv_DoanDAO spDAO = new Nv_DoanDAO();
        spDAO.delete(macp,madoan);
    }

    public void edit(int madoan, int makhachhang,String nhiemvu,int manv) {
        Nv_DoanDAO spDAO = new Nv_DoanDAO();
        Nv_DoanDTO sp = new Nv_DoanDTO(makhachhang);
        sp.setMadoan(madoan);
        sp.setManhanvien(makhachhang);
        sp.setNhiemvu(nhiemvu);
        spDAO.edit(sp,manv);
    }
}
