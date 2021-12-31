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

import dao.DoanDuLichDAO;
import DTO.DoanDuLichDTO;
import Tools.Convert;
import dao.GiaTourDAO;
import dao.Khach_DoanDAO;

/**
 *
 * @author ChiThien
 */
public class DoanDuLichBUS {

    public static DoanDuLichDTO getDoanDuLich(int madoan) {
        return DoanDuLichDAO.getDoanDuLich(madoan);
    }

    public static void init(JTable tbl) {
        ArrayList<DoanDuLichDTO> dssp = DoanDuLichDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<DoanDuLichDTO> find(String ten) {
        DoanDuLichDAO spDAO = new DoanDuLichDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<DoanDuLichDTO> list) {
        String[] columnNames = {"Mã đoàn", "Tên tour", "Ngày khởi hành", "Ngày kết thúc", "Tên Chi phí", "Doanh Thu"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (DoanDuLichDTO sp : list) {
            data[i][0] = sp.getMadoan();
            data[i][1] = Convert.getTentour(sp.getMatour());
            data[i][2] = sp.getNgaykhoihanh();
            data[i][3] = sp.getNgayketthuc();
            data[i][4] = Convert.getTencp(sp.getMaloaichiphi());
            data[i][5] = sp.getDoanhthu();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        DoanDuLichDAO spDAO = new DoanDuLichDAO();
        ArrayList<DoanDuLichDTO> dssp = spDAO.load();
        for(DoanDuLichDTO d :dssp){
            d.setDoanhthu(updateDoanhthu(d.getMadoan()));
            
        }
        uploadTable(tbl, dssp);
    }

    public void add(int matour, String ngaykhoihanh, String ngayketthuc, int maloaichiphi, int doanhthu) {
        DoanDuLichDAO spDAO = new DoanDuLichDAO();
        DoanDuLichDTO sp = new DoanDuLichDTO(spDAO.getNewID());
        sp.setMatour(matour);
        sp.setNgaykhoihanh(ngaykhoihanh);
        sp.setNgayketthuc(ngayketthuc);
        sp.setMaloaichiphi(maloaichiphi);
        sp.setDoanhthu(doanhthu);
        spDAO.add(sp);
    }

    public static void delete(int madoan) {
        DoanDuLichDAO ddlDAO = new DoanDuLichDAO();
        ddlDAO.delete(madoan);
    }

    public void edit(int madoan, int matour, String ngaykhoihanh, String ngayketthuc, int maloaichiphi, int doanhthu) {
        DoanDuLichDAO spDAO = new DoanDuLichDAO();
        DoanDuLichDTO sp = new DoanDuLichDTO(madoan);
        sp.setMatour(matour);
        sp.setNgaykhoihanh(ngaykhoihanh);
        sp.setNgayketthuc(ngayketthuc);
        sp.setMaloaichiphi(maloaichiphi);
        sp.setDoanhthu(doanhthu);
        spDAO.edit(sp);
    }

    public void editDoanhthu(int madoan, int doanhthu) {
        DoanDuLichDAO spDAO = new DoanDuLichDAO();
        DoanDuLichDTO sp = new DoanDuLichDTO(madoan);
        sp.setDoanhthu(doanhthu);
        spDAO.edit(sp);
    }

    public int updateDoanhthu(int madoan) {
        int gia = GiaTourDAO.getGiaDoan(madoan);
        int sl = Khach_DoanDAO.countKhachDoan(madoan);
        int doanhthu = gia * sl;
        editDoanhthu(madoan, doanhthu);
        return doanhthu;
    }
    public void updateDoanhthu() {
        ArrayList<DoanDuLichDTO> arr= DoanDuLichDAO.load();
        for (DoanDuLichDTO i : arr){
            int gia = GiaTourDAO.getGiaDoan(i.getMadoan());
        int sl = Khach_DoanDAO.countKhachDoan(i.getMadoan());
        int doanhthu = gia * sl;
        editDoanhthu(i.getMadoan(), doanhthu);
        }
        
    }
    public void updateDoanhthuTour(int matour){
        ArrayList<Integer> arr = DoanDuLichDAO.getMadoantour(matour);
        for(int i: arr){
            updateDoanhthu(i);
        }
    }
}
