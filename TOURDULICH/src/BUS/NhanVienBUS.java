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

import dao.NhanVienDAO;
import DTO.NhanVienDTO;
/**
 *
 * @author ChiThien
 */
public class NhanVienBUS {
    public static NhanVienDTO getNhanVien(int manv) {
        return NhanVienDAO.getNhanVien(manv);
    }

    public static void init(JTable tbl) {
        ArrayList<NhanVienDTO> dssp = NhanVienDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<NhanVienDTO> find(String ten) {
        NhanVienDAO spDAO = new NhanVienDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<NhanVienDTO> list) {
        String[] columnNames = {"Mã nhân viên","Tên nhân viên"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (NhanVienDTO sp : list) {
            data[i][0] = sp.getManhanvien();
            data[i][1] = sp.getTennhanvien();
                        
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        NhanVienDAO spDAO = new NhanVienDAO();
        ArrayList<NhanVienDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String tennhanvien) {
        NhanVienDAO spDAO = new NhanVienDAO();
        NhanVienDTO sp = new NhanVienDTO(spDAO.getNewID());
        sp.setTennhanvien(tennhanvien);
        spDAO.add(sp);
    }

    public static void delete(int manv) {
        NhanVienDAO spDAO = new NhanVienDAO();
        spDAO.delete(manv);
    }

    public void edit(int manhanvien,String tennhanvien) {
        NhanVienDAO spDAO = new NhanVienDAO();
        NhanVienDTO sp = new NhanVienDTO(manhanvien);
        sp.setTennhanvien(tennhanvien);
        spDAO.edit(sp);
    }
}
