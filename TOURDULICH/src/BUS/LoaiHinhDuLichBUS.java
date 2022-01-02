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
        ArrayList<LoaiHinhDuLichDTO> dslh = LoaiHinhDuLichDAO.load();
        uploadTable(tbl, dslh);
    }

    public static void uploadTable(JTable tbl, ArrayList<LoaiHinhDuLichDTO> list) {
        String[] columnNames = {"Mã loại hình", "Tên loại hình"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (LoaiHinhDuLichDTO lh : list) {
            data[i][0] = lh.getMaloaihinh();
            data[i][1] = lh.getTenloaihinh();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        LoaiHinhDuLichDAO lhDAO = new LoaiHinhDuLichDAO();
        ArrayList<LoaiHinhDuLichDTO> dslh = lhDAO.load();
        uploadTable(tbl, dslh);
    }

    public void add(String tenloaihinh) {
        LoaiHinhDuLichDAO lhDAO = new LoaiHinhDuLichDAO();
        LoaiHinhDuLichDTO lh = new LoaiHinhDuLichDTO(lhDAO.getNewID());
        lh.setTenloaihinh(tenloaihinh);
        lhDAO.add(lh);
    }

    public static void delete(int malh) {
        LoaiHinhDuLichDAO nccDAO = new LoaiHinhDuLichDAO();
        nccDAO.delete(malh);
    }

    public void edit(int maloaihinh, String tenloaihinh) {
        LoaiHinhDuLichDAO lhDAO = new LoaiHinhDuLichDAO();
        LoaiHinhDuLichDTO lh = new LoaiHinhDuLichDTO(maloaihinh);
        lh.setTenloaihinh(tenloaihinh);
        lhDAO.edit(lh);
    }
}
