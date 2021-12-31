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
import dao.DiaDiemDAO;
import DTO.DiaDiemDTO;

public class DiaDiemBUS {

    public static DiaDiemDTO getDiaDiem(int madiadiem) {
        return DiaDiemDAO.getDiaDiem(madiadiem);
    }

    public static void init(JTable tbl) {
        ArrayList<DiaDiemDTO> dssp = DiaDiemDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<DiaDiemDTO> find(String ten) {
        DiaDiemDAO spDAO = new DiaDiemDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<DiaDiemDTO> list) {
        String[] columnNames = {"Mã địa điểm", "Tên địa điểm"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (DiaDiemDTO sp : list) {
            data[i][0] = sp.getMadiadiem();
            data[i][1] = sp.getTendiadiem();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        DiaDiemDAO spDAO = new DiaDiemDAO();
        ArrayList<DiaDiemDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String tendiadiem) {
        DiaDiemDAO spDAO = new DiaDiemDAO();
        DiaDiemDTO sp = new DiaDiemDTO(spDAO.getNewID());
        sp.setTendiadiem(tendiadiem);
        spDAO.add(sp);
    }

    public static void delete(int madd) {
        DiaDiemDAO nccDAO = new DiaDiemDAO();
        nccDAO.delete(madd);
    }

    public void edit(int madiadiem, String tendiadiem) {
        DiaDiemDAO spDAO = new DiaDiemDAO();
        DiaDiemDTO sp = new DiaDiemDTO(madiadiem);
        sp.setTendiadiem(tendiadiem);
        spDAO.edit(sp);
    }
}
