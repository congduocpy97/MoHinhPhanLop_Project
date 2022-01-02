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
        ArrayList<Khach_DoanDTO> dskhach_doan = Khach_DoanDAO.load(ma);
        uploadTable(tbl, dskhach_doan, ma);
    }

    public static void uploadTable(JTable tbl, ArrayList<Khach_DoanDTO> list, int ma) {
        String[] columnNames = {"Đoàn", "Khách"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        gui.CHITIETDOAN.arrTenKhach = new ArrayList<>();
        for (Khach_DoanDTO khach_doan : list) {
            data[i][0] = khach_doan.getMadoan();
            data[i][1] = Convert.getTenkhach(khach_doan.getMakhachhang());
            gui.CHITIETDOAN.arrTenKhach.add(khach_doan.getMakhachhang());
            i++;
        }
        
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl, int ma) throws Exception {
        Khach_DoanDAO khach_doanDAO = new Khach_DoanDAO();
        ArrayList<Khach_DoanDTO> dskhach_doan = khach_doanDAO.load(ma);
        uploadTable(tbl, dskhach_doan, ma);
    }

    public void add(int madoan, int makhachhang) {
        Khach_DoanDAO khach_doanDAO = new Khach_DoanDAO();
        Khach_DoanDTO khach_doan = new Khach_DoanDTO();
        khach_doan.setMadoan(madoan);
        khach_doan.setMakhachhang(makhachhang);
        khach_doanDAO.add(khach_doan);
    }

    public static void delete(int macp, int madoan) {
        Khach_DoanDAO khach_doanDAO = new Khach_DoanDAO();
        khach_doanDAO.delete(macp, madoan);
    }

    public void edit(int doan, int khach,int khach2) {
        Khach_DoanDAO khach_doanDAO = new Khach_DoanDAO();
        Khach_DoanDTO khach_doan = new Khach_DoanDTO(khach);
        khach_doan.setMadoan(doan);
        khach_doan.setMakhachhang(khach);
        khach_doanDAO.edit(khach_doan,khach2);
    }
}
