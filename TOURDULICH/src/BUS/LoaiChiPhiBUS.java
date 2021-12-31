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
        ArrayList<LoaiChiPhiDTO> dssp = LoaiChiPhiDAO.load();
        uploadTable(tbl, dssp);
    }

//    public ArrayList<LoaiChiPhiDTO> find(String ten) {
//        LoaiChiPhiDAO spDAO = new LoaiChiPhiDAO();
//        return spDAO.find(ten);
//    }

    public static void uploadTable(JTable tbl, ArrayList<LoaiChiPhiDTO> list) {
        String[] columnNames = {"Mã loại chi phí","Tên chi phí"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (LoaiChiPhiDTO sp : list) {
            data[i][0] = sp.getMaloaichiphi();
            data[i][1] = sp.getTenloaichiphi();
                        
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        LoaiChiPhiDAO spDAO = new LoaiChiPhiDAO();
        ArrayList<LoaiChiPhiDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String tenloaichiphi) {
        LoaiChiPhiDAO spDAO = new LoaiChiPhiDAO();
        LoaiChiPhiDTO sp = new LoaiChiPhiDTO(spDAO.getNewID());
        sp.setTenloaichiphi(tenloaichiphi);
        
        spDAO.add(sp);
    }

    public static void delete(int macp) {
        LoaiChiPhiDAO spDAO = new LoaiChiPhiDAO();
        spDAO.delete(macp);
    }

    public void edit(int maloaichiphi,String tenloaichiphi) {
        LoaiChiPhiDAO spDAO = new LoaiChiPhiDAO();
        LoaiChiPhiDTO sp = new LoaiChiPhiDTO(maloaichiphi);
        sp.setMaloaichiphi(maloaichiphi);
        sp.setTenloaichiphi(tenloaichiphi);
        spDAO.edit(sp);
    }

}
