/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.GiaTourDTO;
import Tools.Convert;
import dao.GiaTourDAO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ChiThien
 */
public class GiaTourBUS {

    public static GiaTourDTO getGia(int masach) {
        return GiaTourDAO.getGia(masach);
    }

    public static void init(JTable tbl) {
        ArrayList<GiaTourDTO> dssp = GiaTourDAO.load();
        uploadTable(tbl, dssp);
    }

//    public ArrayList<GiaTourDTO> find(String ten) {
//        GiaTourDAO spDAO = new GiaTourDAO();
//        return spDAO.find(ten);
//    }
    public static void uploadTable(JTable tbl, ArrayList<GiaTourDTO> list) {
        String[] columnNames = {"Mã giá", "Tên Tour", "Ngày bắt đầu", "Ngày kết thúc", "Thành tiền"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (GiaTourDTO sp : list) {
            data[i][0] = sp.getMagia();
            data[i][1] = Convert.getTentour(sp.getMatour());
            data[i][2] = sp.getThoiGianbatdau();
            data[i][3] = sp.getThoiGianketthuc();
            data[i][4] = sp.getThanhtien();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        GiaTourDAO spDAO = new GiaTourDAO();
        ArrayList<GiaTourDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(int matour, String ngaybd, String ngaykt, int thanhtien) {
        GiaTourDAO spDAO = new GiaTourDAO();

        GiaTourDTO sp = new GiaTourDTO(spDAO.getNewID());
        sp.setMatour(matour);
        sp.setThoiGianbatdau(ngaybd);
        sp.setThoiGianketthuc(ngaykt);
        sp.setThanhtien(thanhtien);
        spDAO.add(sp);
    }

    public static void delete(int matl) {
        GiaTourDAO nccDAO = new GiaTourDAO();
        nccDAO.delete(matl);
    }

    public void edit(int magia, int matour, String ngaybd, String ngaykt, int thanhtien) {
        GiaTourDTO sp = new GiaTourDTO(magia);
        sp.setMatour(matour);
        sp.setThoiGianbatdau(ngaybd);
        sp.setThoiGianketthuc(ngaykt);
        sp.setThanhtien(thanhtien);
        GiaTourDAO spDAO = new GiaTourDAO();
        spDAO.edit(sp);
    }
    
   
}
