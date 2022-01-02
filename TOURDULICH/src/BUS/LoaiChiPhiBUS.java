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

import dao.LoaiChiPhiDAO;
import DTO.LoaiChiPhiDTO;
/**
 *
 * @author ChiThien
 */
public class LoaiChiPhiBUS {
    public static LoaiChiPhiDTO getLoaiChiPhi(int maloaichiphi) {
        return LoaiChiPhiDAO.getLoaiChiPhi(maloaichiphi);
    }

    public static void init(JTable tbl) {
        ArrayList<LoaiChiPhiDTO> dscp = LoaiChiPhiDAO.load();
        uploadTable(tbl, dscp);
    }

//    public ArrayList<LoaiChiPhiDTO> find(String ten) {
//        LoaiChiPhiDAO cpDAO = new LoaiChiPhiDAO();
//        return cpDAO.find(ten);
//    }

    public static void uploadTable(JTable tbl, ArrayList<LoaiChiPhiDTO> list) {
        String[] columnNames = {"Mã loại chi phí","Tên chi phí"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (LoaiChiPhiDTO cp : list) {
            data[i][0] = cp.getMaloaichiphi();
            data[i][1] = cp.getTenloaichiphi();
                        
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        LoaiChiPhiDAO cpDAO = new LoaiChiPhiDAO();
        ArrayList<LoaiChiPhiDTO> dscp = cpDAO.load();
        uploadTable(tbl, dscp);
    }

    public void add(String tenloaichiphi) {
        LoaiChiPhiDAO cpDAO = new LoaiChiPhiDAO();
        LoaiChiPhiDTO cp = new LoaiChiPhiDTO(cpDAO.getNewID());
        cp.setTenloaichiphi(tenloaichiphi);
        
        cpDAO.add(cp);
    }

    public static void delete(int macp) {
        LoaiChiPhiDAO cpDAO = new LoaiChiPhiDAO();
        cpDAO.delete(macp);
    }

    public void edit(int maloaichiphi,String tenloaichiphi) {
        LoaiChiPhiDAO cpDAO = new LoaiChiPhiDAO();
        LoaiChiPhiDTO cp = new LoaiChiPhiDTO(maloaichiphi);
        cp.setMaloaichiphi(maloaichiphi);
        cp.setTenloaichiphi(tenloaichiphi);
        cpDAO.edit(cp);
    }

}
