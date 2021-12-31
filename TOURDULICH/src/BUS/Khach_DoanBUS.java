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
import dao.Khach_DoanDAO;
import DTO.Khach_DoanDTO;
/**
 *
 * @author ChiThien
 */
public class Khach_DoanBUS {

    public static Khach_DoanDTO getKhach_Doan(int makhachdoan) {
        return Khach_DoanDAO.getKhach_Doan(makhachdoan);
    }

    public static void init(JTable tbl, int ma) {
        ArrayList<Khach_DoanDTO> dssp = Khach_DoanDAO.load(ma);
        uploadTable(tbl, dssp, ma);
    }

    public static void uploadTable(JTable tbl, ArrayList<Khach_DoanDTO> list, int ma) {
        String[] columnNames = {"Đoàn", "Khách"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        gui.CHITIETDOAN.arrTenKhach = new ArrayList<>();
        for (Khach_DoanDTO sp : list) {
            data[i][0] = sp.getMadoan();
            data[i][1] = Convert.getTenkhach(sp.getMakhachhang());
            gui.CHITIETDOAN.arrTenKhach.add(sp.getMakhachhang());
            i++;
        }
        
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl, int ma) throws Exception {
        Khach_DoanDAO spDAO = new Khach_DoanDAO();
        ArrayList<Khach_DoanDTO> dssp = spDAO.load(ma);
        uploadTable(tbl, dssp, ma);
    }

    public void add(int madoan, int makhachhang) {
        Khach_DoanDAO spDAO = new Khach_DoanDAO();
        Khach_DoanDTO sp = new Khach_DoanDTO();
        sp.setMadoan(madoan);
        sp.setMakhachhang(makhachhang);
        spDAO.add(sp);
    }

    public static void delete(int macp, int madoan) {
        Khach_DoanDAO spDAO = new Khach_DoanDAO();
        spDAO.delete(macp, madoan);
    }

    public void edit(int doan, int khach,int khach2) {
        Khach_DoanDAO spDAO = new Khach_DoanDAO();
        Khach_DoanDTO sp = new Khach_DoanDTO(khach);
        sp.setMadoan(doan);
        sp.setMakhachhang(khach);
        spDAO.edit(sp,khach2);
    }
}
