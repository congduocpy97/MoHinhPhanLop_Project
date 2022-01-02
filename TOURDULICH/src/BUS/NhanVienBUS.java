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
        ArrayList<NhanVienDTO> dsnv = NhanVienDAO.load();
        uploadTable(tbl, dsnv);
    }

    public ArrayList<NhanVienDTO> find(String ten) {
        NhanVienDAO nvDAO = new NhanVienDAO();
        return nvDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<NhanVienDTO> list) {
        String[] columnNames = {"Mã nhân viên","Tên nhân viên"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (NhanVienDTO nv : list) {
            data[i][0] = nv.getManhanvien();
            data[i][1] = nv.getTennhanvien();
                        
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        NhanVienDAO nvDAO = new NhanVienDAO();
        ArrayList<NhanVienDTO> dsnv = nvDAO.load();
        uploadTable(tbl, dsnv);
    }

    public void add(String tennhanvien) {
        NhanVienDAO nvDAO = new NhanVienDAO();
        NhanVienDTO nv = new NhanVienDTO(nvDAO.getNewID());
        nv.setTennhanvien(tennhanvien);
        nvDAO.add(nv);
    }

    public static void delete(int manv) {
        NhanVienDAO nvDAO = new NhanVienDAO();
        nvDAO.delete(manv);
    }

    public void edit(int manhanvien,String tennhanvien) {
        NhanVienDAO nvDAO = new NhanVienDAO();
        NhanVienDTO nv = new NhanVienDTO(manhanvien);
        nv.setTennhanvien(tennhanvien);
        nvDAO.edit(nv);
    }
}
