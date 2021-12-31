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

import dao.LoaiHinhDuLichDAO;
import DTO.LoaiHinhDuLichDTO;

/**
 *
 * @author ChiThien
 */
public class LoaiHinhDuLichBUS {

    public static LoaiHinhDuLichDTO getLoaihinh(int malh) {
        return LoaiHinhDuLichDAO.getLoaiHinh(malh);
    }

    public static void init(JTable tbl) {
        ArrayList<LoaiHinhDuLichDTO> dssp = LoaiHinhDuLichDAO.load();
        uploadTable(tbl, dssp);
    }

    public static void uploadTable(JTable tbl, ArrayList<LoaiHinhDuLichDTO> list) {
        String[] columnNames = {"Mã loại hình", "Tên loại hình"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (LoaiHinhDuLichDTO sp : list) {
            data[i][0] = sp.getMaloaihinh();
            data[i][1] = sp.getTenloaihinh();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        LoaiHinhDuLichDAO spDAO = new LoaiHinhDuLichDAO();
        ArrayList<LoaiHinhDuLichDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String tenloaihinh) {
        LoaiHinhDuLichDAO spDAO = new LoaiHinhDuLichDAO();
        LoaiHinhDuLichDTO sp = new LoaiHinhDuLichDTO(spDAO.getNewID());
        sp.setTenloaihinh(tenloaihinh);
        spDAO.add(sp);
    }

    public static void delete(int malh) {
        LoaiHinhDuLichDAO nccDAO = new LoaiHinhDuLichDAO();
        nccDAO.delete(malh);
    }

    public void edit(int maloaihinh, String tenloaihinh) {
        LoaiHinhDuLichDAO spDAO = new LoaiHinhDuLichDAO();
        LoaiHinhDuLichDTO sp = new LoaiHinhDuLichDTO(maloaihinh);
        sp.setTenloaihinh(tenloaihinh);
        spDAO.edit(sp);
    }
}
