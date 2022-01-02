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

    public static Nv_DoanDTO getNv_Doan(int manvdoan) {
        return Nv_DoanDAO.getNv_Doan(manvdoan);
    }

    public static void init(JTable tbl, int ma) {
        ArrayList<Nv_DoanDTO> dsnv = Nv_DoanDAO.load(ma);
        uploadTable(tbl, dsnv, ma);
    }
    public static void uploadTable(JTable tbl, ArrayList<Nv_DoanDTO> list, int ma) {
        String[] columnNames = {"Đoàn", "Nhân viên","Nhiệm vụ"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
       gui.CHITIETDOAN.arrTenNhanvien= new ArrayList<>();
        for (Nv_DoanDTO nv : list) {
            data[i][0] = nv.getMadoan();
            data[i][1] = Convert.getTennv(nv.getManhanvien());
            data[i][2] = nv.getNhiemvu();
            gui.CHITIETDOAN.arrTenNhanvien.add(nv.getManhanvien());

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl, int ma) throws Exception {
        Nv_DoanDAO nvDAO = new Nv_DoanDAO();
        ArrayList<Nv_DoanDTO> dsnv = nvDAO.load(ma);
        uploadTable(tbl, dsnv, ma);
    }

    public void add(int madoan, int makhachhang,String nhiemvu) {
        Nv_DoanDAO nvDAO = new Nv_DoanDAO();
        Nv_DoanDTO nv = new Nv_DoanDTO();
        nv.setMadoan(madoan);
        nv.setManhanvien(makhachhang);
        nv.setNhiemvu(nhiemvu);
        nvDAO.add(nv);
    }

    public static void delete(int macp,int madoan) {
        Nv_DoanDAO nvDAO = new Nv_DoanDAO();
        nvDAO.delete(macp,madoan);
    }

    public void edit(int madoan, int makhachhang,String nhiemvu,int manv) {
        Nv_DoanDAO nvDAO = new Nv_DoanDAO();
        Nv_DoanDTO nv = new Nv_DoanDTO(makhachhang);
        nv.setMadoan(madoan);
        nv.setManhanvien(makhachhang);
        nv.setNhiemvu(nhiemvu);
        nvDAO.edit(nv,manv);
    }
}
