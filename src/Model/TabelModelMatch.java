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
public class TabelModelMatch extends AbstractTableModel{
    List<Match> listMatch;
    
    public TabelModelMatch(List<Match> listMatch){
        this.listMatch = listMatch;
    }
    
    @Override
    public int getColumnCount(){
        return 5;
    }
    
    public int getRowCount(){
        return listMatch.size();
    }
    
    public String getColumnName (int column){
        switch (column){
            case 0:
                return "Nama Team A";
            case 1:
                return "Game A";
            case 2:
                return "Tanggal";
            case 3:
                return "Game B";
            case 4:
                return "Nama Team B";
            default:
                return null;
        }
    }
    
    public Object getValueAt (int row, int column){
        switch (column){
            case 0:
                return listMatch.get(row).getTeam1Id();
            case 1:
                return listMatch.get(row).getTeam1Game();
            case 2:
                return listMatch.get(row).getMatchDate();
            case 3:
                return listMatch.get(row).getTeam2Game();
            case 4:
                return listMatch.get(row).getTeam2Id();
            default:
                return null;
        }
    }
}
