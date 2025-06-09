/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USER
 */
public class TabelModelKlasemen extends AbstractTableModel{
    List<Klasemen> listKlasemen;
    
    public TabelModelKlasemen(List<Klasemen> listKlasemen){
        this.listKlasemen = listKlasemen;
    }

    @Override
    public int getRowCount() {
        return listKlasemen.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public String getColumnName (int column){
        switch (column){
            case 0:
                return "Posisi";
            case 1:
                return "Nama Team";
            case 2:
                return "Match W-L";
            case 3:
                return "Game W-L";
            case 4:
                return "Net Game";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
            case 0:
                return row + 1;
            case 1:
                return listKlasemen.get(row).getNamaTeam();
            case 2:
                return listKlasemen.get(row).getMatchWL();
            case 3:
                return listKlasemen.get(row).getGameWL();
            case 4:
                return listKlasemen.get(row).getNetGame();
            default:
                return null;
        }
    }
    
    
}
