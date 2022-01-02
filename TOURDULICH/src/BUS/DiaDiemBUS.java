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
        ArrayList<DiaDiemDTO> dsdd = DiaDiemDAO.load();
        uploadTable(tbl, dsdd);
    }

    public ArrayList<DiaDiemDTO> find(String ten) {
        DiaDiemDAO ddDAO = new DiaDiemDAO();
        return ddDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<DiaDiemDTO> list) {
        String[] columnNames = {"Mã địa điểm", "Tên địa điểm"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (DiaDiemDTO dd : list) {
            data[i][0] = dd.getMadiadiem();
            data[i][1] = dd.getTendiadiem();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        DiaDiemDAO ddDAO = new DiaDiemDAO();
        ArrayList<DiaDiemDTO> dsdd = ddDAO.load();
        uploadTable(tbl, dsdd);
    }

    public void add(String tendiadiem) {
        DiaDiemDAO ddDAO = new DiaDiemDAO();
        DiaDiemDTO dd = new DiaDiemDTO(ddDAO.getNewID());
        dd.setTendiadiem(tendiadiem);
        ddDAO.add(dd);
    }

    public static void delete(int madd) {
        DiaDiemDAO nccDAO = new DiaDiemDAO();
        nccDAO.delete(madd);
    }

    public void edit(int madiadiem, String tendiadiem) {
        DiaDiemDAO ddDAO = new DiaDiemDAO();
        DiaDiemDTO dd = new DiaDiemDTO(madiadiem);
        dd.setTendiadiem(tendiadiem);
        ddDAO.edit(dd);
    }
}
