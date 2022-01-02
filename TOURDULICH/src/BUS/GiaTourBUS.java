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
        ArrayList<GiaTourDTO> dsgia = GiaTourDAO.load();
        uploadTable(tbl, dsgia);
    }

//    public ArrayList<GiaTourDTO> find(String ten) {
//        GiaTourDAO giaDAO = new GiaTourDAO();
//        return giaDAO.find(ten);
//    }
    public static void uploadTable(JTable tbl, ArrayList<GiaTourDTO> list) {
        String[] columnNames = {"Mã giá", "Tên Tour", "Ngày bắt đầu", "Ngày kết thúc", "Thành tiền"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (GiaTourDTO gia : list) {
            data[i][0] = gia.getMagia();
            data[i][1] = Convert.getTentour(gia.getMatour());
            data[i][2] = gia.getThoiGianbatdau();
            data[i][3] = gia.getThoiGianketthuc();
            data[i][4] = gia.getThanhtien();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        GiaTourDAO giaDAO = new GiaTourDAO();
        ArrayList<GiaTourDTO> dsgia = giaDAO.load();
        uploadTable(tbl, dsgia);
    }

    public void add(int matour, String ngaybd, String ngaykt, int thanhtien) {
        GiaTourDAO giaDAO = new GiaTourDAO();

        GiaTourDTO gia = new GiaTourDTO(giaDAO.getNewID());
        gia.setMatour(matour);
        gia.setThoiGianbatdau(ngaybd);
        gia.setThoiGianketthuc(ngaykt);
        gia.setThanhtien(thanhtien);
        giaDAO.add(gia);
    }

    public static void delete(int matl) {
        GiaTourDAO nccDAO = new GiaTourDAO();
        nccDAO.delete(matl);
    }

    public void edit(int magia, int matour, String ngaybd, String ngaykt, int thanhtien) {
        GiaTourDTO gia = new GiaTourDTO(magia);
        gia.setMatour(matour);
        gia.setThoiGianbatdau(ngaybd);
        gia.setThoiGianketthuc(ngaykt);
        gia.setThanhtien(thanhtien);
        GiaTourDAO giaDAO = new GiaTourDAO();
        giaDAO.edit(gia);
    }
    
   
}
