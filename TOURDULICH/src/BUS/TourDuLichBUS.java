/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import DAO.TourDuLichDAO;
import DTO.TourDuLichDTO;
import Tools.Convert;
import Tools.TableUtil;

/**
 *
 * @author PHAN GIA PHAT
 */
public class TourDuLichBUS {

    public static TourDuLichDTO getTour(int matour) {
        return TourDuLichDAO.getTour(matour);
    }

    public static void init(JTable tbl) {
        ArrayList<TourDuLichDTO> dssp = TourDuLichDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<TourDuLichDTO> find(String ten) {
        TourDuLichDAO spDAO = new TourDuLichDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<TourDuLichDTO> list) {
        String[] columnNames = {"Mã Tour", "Tên gọi", "Loại hình", "Địa điểm"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (TourDuLichDTO sp : list) {
            data[i][0] = sp.getMatour();
            data[i][1] = sp.getTengoi();
            data[i][2] = Convert.getTenlh(sp.getMaloaihinh());
            data[i][3] =Convert.getTendd(sp.getMadiadiem());
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        TourDuLichDAO  spDAO = new TourDuLichDAO();
        ArrayList<TourDuLichDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String tengoi, int malh, int madd) {
        TourDuLichDAO spDAO = new TourDuLichDAO();
        TourDuLichDTO sp = new TourDuLichDTO(spDAO.getNewID());
        sp.setTengoi(tengoi);
        sp.setMaloaihinh(malh);
        sp.setMadiadiem(madd);
        spDAO.add(sp);
    }

    public static void delete(int mat) {
        TourDuLichDAO nccDAO = new TourDuLichDAO();
        nccDAO.delete(mat);
    }

    public void edit(int matour, String tengoi,int malh, int madd) {
        TourDuLichDAO spDAO = new TourDuLichDAO();
        TourDuLichDTO sp = new TourDuLichDTO(matour);
        sp.setTengoi(tengoi);
        sp.setMaloaihinh(malh);
        sp.setMadiadiem(madd);
        spDAO.edit(sp);
    }

}
